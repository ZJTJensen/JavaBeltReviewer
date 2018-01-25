package com.project.login.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.connections.internal.PooledConnections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.login.models.Pool;
import com.project.login.models.Review;
import com.project.login.models.User;
import com.project.login.services.PoolService;
import com.project.login.services.ReviewService;
import com.project.login.services.UserService;

@Controller
public class PoolController{
	//Member variables go here
	private PoolService _ps;
	private ReviewService _rs;
	private UserService _us;
	public PoolController( PoolService _ps, ReviewService _rs, UserService _us){
		this._ps = _ps;
		this._rs = _rs;
		this._us = _us;
	}
	
	// Hosts
	@RequestMapping("/dashboard")
	public String adminDashboard(@ModelAttribute("pool")Pool pool, HttpSession s, Model model){
		List<Pool>allPools = _ps.getAllPools();
		List<Pool>yourPools = new ArrayList<>();


		model.addAttribute("yourPools",yourPools);

		for(Pool iPool: allPools){
			if(iPool.getHost().equals(_us.findById((Long)s.getAttribute("id")))){
				yourPools.add(iPool);
			}
		}

		return "dashboard";
	}

	@PostMapping("/dashboard/newPool")
	public String newPool(HttpSession session, Model model, @ModelAttribute("pool") Pool pool){
		User host = _us.findById((Long)session.getAttribute("id"));
		_ps.createPool( host, pool);
		return "redirect:/dashboard";
	}

	@PostMapping("/pools/{id}/edit")
	public String updatePool(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("pool") Pool pool){
		User host = _us.findById((Long)session.getAttribute("id"));
		_ps.updatePool( host, pool);
		return "redirect:/pools/" + id;
	}


	// Guests

	@RequestMapping("/search")
	public String yourMethod(HttpSession s, @RequestParam("location") String location, Model model){
		List<Pool>allPools = _ps.getAllPools();
		List<Pool>foundPools = new ArrayList<>();
		for(Pool pool: allPools){
			if(pool.getAddress().contains(location)){
				foundPools.add(pool);
			}
		}
		model.addAttribute("id", s.getAttribute("id"));
		model.addAttribute("foundPools",foundPools);
		return "searched";
	}	
	@RequestMapping("/pools/{id}")
	public String thisPool(HttpSession session, @PathVariable("id") Long id, Model model, @ModelAttribute("review")Review review){
		Pool currentPool = _ps.findOne(id);
		User current = _us.findById((Long)session.getAttribute("id"));
		List<Review>allReviews = _rs.getAllReviews();
		int number = 0;
		double rating = 0;
		for(Review reviewNum: allReviews){
			number += reviewNum.getRating();
		}
		if(allReviews.size()> 0){
			rating = number/allReviews.size();
		}
		model.addAttribute("user", current);
		model.addAttribute("rating", rating);
		model.addAttribute("pool", currentPool);
		return "aPool";
	}

	@RequestMapping("/pools/{id}/review")
	public String review(@PathVariable("id") Long id,@ModelAttribute("review") Review review, HttpSession s, Model model){
		Pool currentPool = _ps.findOne(id);
		model.addAttribute("pool", currentPool);
		return "review";
	}
	@PostMapping("/pools/{id}/review")
	public String createReview(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("review") Review review){
		User commenter = _us.findById((Long)session.getAttribute("id"));
		review.setId(null);
		
		_rs.createReview(id, commenter, review);
		return "redirect:/pools/" + id;
	}
}
