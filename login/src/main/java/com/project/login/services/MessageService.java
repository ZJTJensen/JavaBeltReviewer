package com.project.login.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.login.models.User;
import com.project.login.models.Event;
import com.project.login.models.Message;
import com.project.login.repositories.MessageRepository;

@Service
public class MessageService {
	// Member variables / service initializations go here
	private MessageRepository _mr;
	private EventService _es;

	public MessageService(MessageRepository _mr, EventService _es){
		this._mr = _mr;
		this._es = _es;
	}

	public void createMessage(Long id, User commenter, Message message){
		Event currentEvent = _es.findOne(id);
		message.setPoster(commenter);
		message.setSubject(currentEvent);
		_mr.save(message);

	}
	
	// Crud methods to act on services go here.
}
