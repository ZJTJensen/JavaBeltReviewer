package com.project.login.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.project.login.models.Pool;
import com.project.login.models.Review;
import com.project.login.models.User;
import com.project.login.repositories.PoolRepository;
import com.project.login.repositories.ReviewRepository;

@Service
public class ReviewService {
	// Member variables / service initializations go here
	private ReviewRepository _rr;
	private PoolRepository _pr;
	public ReviewService(ReviewRepository _rr, PoolRepository _pr){
		this._rr = _rr;
		this._pr = _pr;
	}
	public List<Review> getAllReviews(){
		return(List<Review>)_rr.findAll();
	}
	public void createReview(Long id, User reviewer, Review review){
		Pool currentPool = _pr.findOne(id);
		review.setUser(reviewer);
		review.setPool(currentPool);
		_rr.save(review);

	}
	
	// Crud methods to act on services go here.
}
