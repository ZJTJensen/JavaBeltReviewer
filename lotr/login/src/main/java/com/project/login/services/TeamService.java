package com.project.login.services;

import com.project.login.models.Team;
import com.project.login.repositories.RingRepository;
import com.project.login.repositories.TeamRepository;
import com.project.login.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
	private RingRepository _rr;
	private UserRepository _ur;
	private TeamRepository _tr;
		
	public TeamService(RingRepository _rr, UserRepository _ur, TeamRepository _tr){
		this._rr = _rr;
		this._ur = _ur;
		this._tr = _tr;
	}
	// Crud methods to act on services go here.
	public void makeTeam(Team team){
		_tr.save(team);
	}
}
