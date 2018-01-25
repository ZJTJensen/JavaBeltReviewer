package com.project.login.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.login.models.Pool;
import com.project.login.models.User;
import com.project.login.repositories.PoolRepository;

@Service
public class PoolService {
	// Member variables / service initializations go here
	private PoolRepository _pr;
	public PoolService(PoolRepository _pr){
		this._pr = _pr;
	}
	
	public void createPool(User host, Pool pool){
		pool.setHost(host);
		_pr.save(pool);
	}

	public List<Pool> getAllPools(){
		return(List<Pool>)_pr.findAll();
	}
	public Pool findOne(Long id){
		return(Pool) _pr.findOne(id);
	}

	public void updatePool(User host, Pool pool){
		pool.setHost(host);
		_pr.save(pool);
	}
	// Crud methods to act on services go here.
}
