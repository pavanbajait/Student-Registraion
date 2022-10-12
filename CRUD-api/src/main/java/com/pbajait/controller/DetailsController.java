package com.pbajait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbajait.Exception.DetailsException;
import com.pbajait.entity.Details;
import com.pbajait.service.DetailsServices;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class DetailsController {

	@Autowired
	private DetailsServices services;

	@CrossOrigin(origins = "*")
	@PostMapping("/addDetails")
	public  ResponseEntity<Details> addPlant(@RequestBody Details detail) throws DetailsException{ 
		Details detailAdd=	services.addDetails(detail);

	return new ResponseEntity<Details>(detailAdd,HttpStatus.OK);
	}
	

	@PutMapping("updateDetails")
	public ResponseEntity<Details> updateDetails(@RequestBody Details details) throws DetailsException{
		Details detail=services.updateDetails(details);

		return new ResponseEntity<>(detail,HttpStatus.OK);
	}
	

	@DeleteMapping("deleteDetails/{userId}")
	public ResponseEntity<Details> deleteDetails(@PathVariable("userId") Integer userId) throws DetailsException{
		Details deletedetail=services.deleteDetails(userId);
		return new ResponseEntity<Details>(deletedetail,HttpStatus.OK);
	}
	@CrossOrigin(origins = "*")
	@GetMapping("/getUserId/{email}")
	public ResponseEntity<Integer> getUserIdByemail(@PathVariable("email") String email) throws DetailsException{
				
		Integer userId= services.getUserIdByEmail(email);
		return new ResponseEntity<Integer>(userId,HttpStatus.OK);
	}
	
	

	@GetMapping("/retrieveData")
	public ResponseEntity<List<Details>> GetDetailsById() throws DetailsException{
		List<Details> details= services.retreiveAllDetails();
		return new ResponseEntity<List<Details>>(details,HttpStatus.OK);
	}
	
	
}
