package com.project.login.controllers;

import com.project.login.models.Event;
import com.project.login.models.Message;
import com.project.login.models.User;
import com.project.login.repositories.UserRepository;
import com.project.login.services.EventService;
import com.project.login.services.MessageService;
import com.project.login.services.UserService;
import com.project.login.validators.EventValidator;
import com.project.login.validators.UserValidator;

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
public class UserController{
	private UserService userService;
	private EventService _es;
	private MessageService _ms;
	private UserValidator _uv;
	private EventValidator _ev;

	public UserController(UserService userService, MessageService _ms, EventService _es, UserValidator _uv, EventValidator _ev){
		this.userService = userService;
		this._es = _es;
		this._ms = _ms;
		this._uv = _uv;
		this._ev = _ev;
	}

	@PostMapping("/events/{id}/message")
	public String createMessage(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("message") Message message){
		User commenter = userService.findById((Long)session.getAttribute("id"));
		_ms.createMessage(id, commenter, message);
		return "redirect:/events/" + id;
	}
	@RequestMapping("/events/{id}")
	public String showEvent(@PathVariable("id") Long id, HttpSession session, Model model, @ModelAttribute("message") Message message){
		Event currentEvent = _es.findOne(id);
		model.addAttribute("event", currentEvent);
		return "displayMessage";
	}

	@PostMapping("/events/{id}/edit")
	public String editEvent(@ModelAttribute("event") Event event, Model model, HttpSession s){
		User host = userService.findById((Long)s.getAttribute("id"));
	
		_es.updateEvent(event, host);		
		return "redirect:/dashboard";
	}

	@RequestMapping("/events/{id}/edit")
	public String showEditEvent(@PathVariable("id") Long id, HttpSession session, Model model){
		User currentUser = userService.findById((Long)session.getAttribute("id"));
		Event currentEvent = _es.findOne(id);
		if (!currentEvent.getHost().equals(currentUser)){
			return "redirect:/";
		}
		model.addAttribute("event", currentEvent);
		return "editEvent";
	}

	@RequestMapping("/events/{id}/cancel")
	public String cancelEvent(@PathVariable("id") Long id, HttpSession session){
		User currentUser = userService.findById((Long)session.getAttribute("id"));
		_es.removeAttendee(id, currentUser);


		return "redirect:/dashboard";
	}

	@RequestMapping("/events/{id}/join")
	public String joinEvent(@PathVariable("id") Long id, HttpSession session){
		User currentUser = userService.findById((Long)session.getAttribute("id"));
		_es.addAttendee(id, currentUser);
		return "redirect:/dashboard";
	}

	@RequestMapping("/events/{id}/delete")
	public String deleteEvent(@PathVariable("id") Long id){
		_es.destory(id);
		return "redirect:/dashboard";
	}
	@PostMapping("/events")
	public String newEvent(HttpSession s, @Valid @ModelAttribute("event") Event event, BindingResult res,Model model){
		_ev.validate(event, res);
		if(res.hasErrors()){
			if(s.getAttribute("id")!= null){
				User user = userService.findById((Long) s.getAttribute("id"));
				model.addAttribute("user", user);
				List<Event> allEvents = _es.getAllEvents();
				List<Event> inStatEvents = new ArrayList<>();
				List<Event> outOfStateEvents = new ArrayList<>();
				for(Event iEvent: allEvents){
					if(iEvent.getState().equals(user.getState())){
						inStatEvents.add(iEvent);
					}else{
						outOfStateEvents.add(iEvent);
					}
				}
				model.addAttribute("inState", inStatEvents);
				model.addAttribute("outOfState", outOfStateEvents);
				return "dashboard";
			
		}
	}
	User host = userService.findById((Long)s.getAttribute("id"));
	_es.createEvent( host, event);
	return "redirect:/dashboard";
		
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute("user") User user, HttpSession s){
		s.setAttribute("id", null);
		return "register";
	}
	@RequestMapping("/dashboard")
	public String dashboard(HttpSession s, @ModelAttribute("event") Event event, Model model){
	
		if(s.getAttribute("id")!= null){
			User user = userService.findById((Long) s.getAttribute("id"));
			model.addAttribute("user", user);
			List<Event> allEvents = _es.getAllEvents();
			List<Event> inStatEvents = new ArrayList<>();
			List<Event> outOfStateEvents = new ArrayList<>();
			for(Event iEvent: allEvents){
				if(iEvent.getState().equals(user.getState())){
					inStatEvents.add(iEvent);
				}else{
					outOfStateEvents.add(iEvent);
				}
			}
			model.addAttribute("inState", inStatEvents);
			model.addAttribute("outOfState", outOfStateEvents);
			return "dashboard";
		}
		return "redirect:/";
	}
	@RequestMapping("/logout")
	public String logout(HttpSession s){
		s.setAttribute("id", null);
		return "redirect:/register";
	}

	@PostMapping("/register")
	public String creater(@Valid @ModelAttribute("user") User user, BindingResult res, HttpSession session){
		_uv.validate(user, res);
		if(res.hasErrors()){
			return "register";
		}else{
			System.out.println("Your user is 0" + user);
			userService.create(user);
			session.setAttribute("id", user.getId());
			return "redirect:/";
		}
	}
	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password")String password, HttpSession session){
		User user = userService.findByEmail(email);
		if(user == null){
			return "redirect:/regiseter";
		}else{
			if( userService.isMatch(password, user.getPassword()) ){
				session.setAttribute("id", user.getId());
				return "redirect:/dashboard";

			}else{
				return "redirect:/regiseter";
			}
		}
 
	}
}
