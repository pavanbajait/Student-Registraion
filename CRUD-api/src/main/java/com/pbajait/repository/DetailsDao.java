package com.pbajait.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pbajait.entity.Details;

@Repository
public interface DetailsDao  extends JpaRepository<Details, Integer>{

	@Query("select userId from Details where email = ?1")
	public Integer getUserIdByEmail(String email);
	
}
