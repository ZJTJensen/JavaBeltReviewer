package com.project.login.controllers;

import com.project.login.models.Team;
import com.project.login.services.TeamService;
import com.project.login.services.UserService;
import java.security.Principal;
import java.util.Date;

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
public class TeamController{
	//Member variables go here
	private UserService _us;
	private TeamService _ts;

	public TeamController(UserService _us, TeamService _ts){
		this._us = _us;
		this._ts = _ts;
	}
	
	@RequestMapping("/makeFate")
	public String makeFate(HttpSession s, Model model, BindingResult res){
		
		return "teamCreater";
	}

	@PostMapping("/makeFate/newTeam")
	public String makeTeam(HttpSession s, Model model, BindingResult res, @ModelAttribute("team")Team team){
		if(res.hasErrors()){
			return "teamCreater";
		}else{
		_ts.makeTeam(team);
		return "redirect:/makeFate";
		}
	}


}
