package com.pbajait.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbajait.entity.Attachment;

@Repository
public interface AttachmentDao extends JpaRepository<Attachment, Integer>{

	
	public Attachment findById(String fileld);
	
	

}
