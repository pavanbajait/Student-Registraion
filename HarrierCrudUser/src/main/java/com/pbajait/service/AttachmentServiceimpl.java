package com.pbajait.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.pbajait.Exception.DetailsException;
import com.pbajait.entity.Attachment;
import com.pbajait.entity.Details;
import com.pbajait.repository.AttachmentDao;
import com.pbajait.repository.DetailsDao;

@Service
public class AttachmentServiceimpl implements AttachmentService{

	@Autowired
	private AttachmentDao dao;
	
	@Autowired
	private DetailsDao Ddao;
	
	
	
	public AttachmentServiceimpl(AttachmentDao dao) {
		super();
		this.dao = dao;
	}



	@Override
	public Attachment saveAttachment(Integer person_user_id,MultipartFile file) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("enter");
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		try {
			if(fileName.contains("..")) {
				throw new Exception("fileName contains inavalid path sequemnce"+fileName);
			
			}
			
			Details person = Ddao.findById(person_user_id).orElseThrow(() -> new DetailsException("Details  not found"));;
			
			Attachment attachment = new Attachment(person,fileName, file.getContentType(),file.getBytes());
			return dao.save(attachment);
			
		}catch(Exception e) {
			throw new Exception("Could not save file: "+ fileName); 
			
		}
		
	}



	@Override
	public Attachment getAttachment(String fileld){
		
		return dao
				.findById(fileld);
//				.orElseThrow(() -> new Exception("File not found with Id: "+ fileld));
		
		
	}

}
