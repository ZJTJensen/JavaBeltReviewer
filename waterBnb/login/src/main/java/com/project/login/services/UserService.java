package com.project.login.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.login.models.User;
import com.project.login.repositories.UserRepository;;

@Service
public class UserService {
	// Member variables / service initializations go here
	private UserRepository userRepository;
	private BCryptPasswordEncoder bcrypt;
		
	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
		this.bcrypt = new BCryptPasswordEncoder();
	}

	public boolean isMatch(String password, String dbpass){
		if( bcrypt.matches(password, dbpass) ){
			return true;
		}
		return false;
	}

	public void create(User user){
		System.out.println("Your user is 1" + user.getPassword());
		user.setPassword(bcrypt.encode(user.getPassword()));
		System.out.println("Your user is 2" + user);
		userRepository.save(user);
		
	}
	public ArrayList<User> all(){
		return (ArrayList<User>)userRepository.findAll();
	}
	public void update(User user){
		create(user);
	}
	public User findById(Long id){
		return (User)userRepository.findOne(id);
	}
	public void destroy(User user){
		userRepository.delete(user);
	}
	public User findByEmail(String email){
		return (User)userRepository.findByEmail(email);
	}
	
	// Crud methods to act on services go here.
}
