package com.project.login.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.login.models.Event;
import com.project.login.models.User;
import com.project.login.repositories.EventRepository;

@Service
public class EventService {
	// Member variables / service initializations go here
		
	private EventRepository _er;
	public EventService(EventRepository _er){
		this._er = _er;
	}

	public void createEvent(User host, Event event){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			event.setDate(format.parse(event.getsDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.setHost(host);
		_er.save(event);
	}

	public void addAttendee(Long id, User user){
		Event event = _er.findOne(id);
		List<User>attendees = event.getAttendees();
		attendees.add(user);
		event.setAttendees(attendees);
		_er.save(event);
	}
	public void removeAttendee(Long id, User user){
		Event event = _er.findOne(id);
		List<User>attendees = event.getAttendees();
		attendees.remove(user);
		event.setAttendees(attendees);
		_er.save(event);
	}

	public List<Event> getAllEvents(){
		return(List<Event>)_er.findAll();
		}
		
	public void destory(Long id){
		Event event = _er.findOne(id);
		_er.delete(event);
	}

	public Event findOne(Long id){
		return(Event) _er.findOne(id);
	}
	public void updateEvent(Event event, User host){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			event.setDate(format.parse(event.getsDate()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		event.setHost(host);
		_er.save(event);
	} 
	
	// Crud methods to act on services go here.
}
