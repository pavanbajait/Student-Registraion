package com.pbajait.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbajait.Exception.DetailsException;
import com.pbajait.entity.Details;
import com.pbajait.repository.DetailsDao;

@Service
public class DetailsServicesImpl implements DetailsServices{

	@Autowired
	private DetailsDao dao;
	
	@Override
	public Details addDetails(Details detail) throws DetailsException {

			return dao.save(detail);
		
	}

	@Override
	public Details updateDetails(Details details) throws DetailsException {
		
		Details obj = dao.findById(details.getUserId()).orElseThrow(() -> new DetailsException("Details not found "));
	        
		 return dao.save(details);
	   
	}

	@Override
	public Details deleteDetails(Integer userId) throws DetailsException {
	
		Details existingUserDetails= dao.findById(userId).orElseThrow(() -> new DetailsException("Details does not exist with User Id "+userId));
		
		dao.delete(existingUserDetails);
				
		return existingUserDetails;
		
	}

	@Override
	public List<Details> retreiveAllDetails() throws DetailsException {
		List<Details> list= dao.findAll();
		
		if(list.size() > 0) {

			return list;
		}else
			throw new DetailsException("No Details are there in database");
		
	}

	@Override
	public Integer getUserIdByEmail(String email) throws DetailsException {
		// TODO Auto-generated method 
		Integer userId  = dao.getUserIdByEmail(email);
		if(userId == null){
			throw new DetailsException("Email does not found");
		}
		return userId;
	}
	
	

}
