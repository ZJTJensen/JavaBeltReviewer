package com.project.login.controllers;

import com.project.login.models.Ring;
import com.project.login.models.User;
import com.project.login.services.RingService;
import com.project.login.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
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

@Controller
public class RingController{
	//Member variables go here
	private RingService _rs;
	private UserService _us;

	public RingController(RingService _rs, UserService _us){
		this._rs = _rs;
		this._us = _us;
	}
	
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession s, Model model){
		
		if(s.getAttribute("id")!= null){
			User current = _us.findById((Long)s.getAttribute("id"));
			List<Ring>allRings = _rs.getAllRings();
			List<Ring>yourRings = new ArrayList<>();
			List<Ring>notYourRings = new ArrayList<>();
			for(Ring iRing: allRings){
				if(iRing.getBearer() == current){
					yourRings.add(iRing);
				}
				if(iRing.getBearer() == null){
					notYourRings.add(iRing);
				}
			}
			model.addAttribute("user", current);
			model.addAttribute("yourRings", yourRings);
			model.addAttribute("notYourRings", notYourRings);
			return "dashboard";
		}
		return "redirect:/";
	}
	@RequestMapping("/delete/{id}")
	public String loseRing(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("ring") Ring ring){
		Ring thisRing = _rs.findOne(id);
		_rs.removeBearer(thisRing);
		return "redirect:/dashboard";
	}
	@PostMapping("/add")
	public String findRing( HttpSession s, Model model, @RequestParam("ring")Long ringid){
		Date now = new Date();
		User bearer = _us.findById((Long)s.getAttribute("id"));
		Date last = bearer.getLastPickedUp();
		System.out.println("the time diff is"); 
		if(last == null){
			Ring thisRing = _rs.findOne(ringid);
		
			_rs.setBearer(thisRing, bearer);
			return "redirect:/dashboard";

		}
		if(now.getTime() - last.getTime() >= 5*60*1000){
			Ring thisRing = _rs.findOne(ringid);
		
			_rs.setBearer(thisRing, bearer);
			return "redirect:/dashboard";
		}
		
		return "redirect:/dashboard";
		
	}

	@RequestMapping("/jewler")
	public String makeRing(HttpSession s, Model model, @ModelAttribute("ring") Ring ring){

		return "ringCreate";
	}
	@PostMapping("/jewler")
	public String createRing(HttpSession s, Model model, @ModelAttribute("ring") Ring ring){
		User maker = _us.findById((Long)s.getAttribute("id"));
		_rs.createRing(ring, maker);
		return "redirect:/dashboard";
	}
}
