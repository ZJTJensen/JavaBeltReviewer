package com.project.login.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.login.models.Ring;
import com.project.login.models.User;
import com.project.login.repositories.RingRepository;
import com.project.login.repositories.TeamRepository;
import com.project.login.repositories.UserRepository;

@Service
public class RingService {
	// Member variables / service initializations go here
	private RingRepository _rr;
	private UserRepository _ur;
	private TeamRepository _tr;
		
	public RingService(RingRepository _rr, UserRepository _ur, TeamRepository _tr){
		this._rr = _rr;
		this._ur = _ur;
		this._tr = _tr;
	}

	public List<Ring> getAllRings(){
		return(List<Ring>)_rr.findAll();
	}
	public void createRing(Ring ring, User maker){
		_rr.save(ring);
		ring.setMaker(maker);
		_rr.save(ring);
	}
	public void setBearer(Ring ring, User user){
		ring.setBearer(user);
		Date now = new Date();
		user.setLastPickedUp(now);
		_rr.save(ring);
		_ur.save(user);
	}

	public void removeBearer(Ring ring){
		ring.setBearer(null);
		_rr.save(ring);
	}
	public Ring findOne(Long id){
		return(Ring) _rr.findOne(id);
	}

	public void destroy(Ring ring){
		_rr.delete(ring);
	}
	// Crud methods to act on services go here.
}
