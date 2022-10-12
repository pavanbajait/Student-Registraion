package com.pbajait.service;

import java.util.List;

import com.pbajait.Exception.DetailsException;
import com.pbajait.entity.Details;

public interface DetailsServices {

	Details addDetails(Details detail) throws DetailsException;

	Details updateDetails(Details planter) throws DetailsException;

	Details deleteDetails(Integer userId) throws DetailsException;

	List<Details> retreiveAllDetails() throws DetailsException;

	Integer getUserIdByEmail(String email) throws DetailsException;

}
