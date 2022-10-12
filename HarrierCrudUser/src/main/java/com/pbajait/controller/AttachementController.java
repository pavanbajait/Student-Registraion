package com.pbajait.controller;

import java.awt.PageAttributes;
import java.net.http.HttpHeaders;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pbajait.entity.Attachment;
import com.pbajait.model.ResponseData;
import com.pbajait.service.AttachmentService;

@RestController
@CrossOrigin(origins = "*")
public class AttachementController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	public AttachementController(AttachmentService attachmentService) {
		this.attachmentService= attachmentService;
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/upload/{person_user_id}")
	public ResponseData uploadFile(@PathVariable("person_user_id") Integer person_user_id, @RequestParam("file") MultipartFile file ) throws Exception
	{
		Attachment attachment = null;
		String downloadUrl = "";
		attachment = attachmentService.saveAttachment(person_user_id,file);
		
		downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
				.path("/download/")
				.path(attachment.getId())
				.toUriString();
		
		return new ResponseData(attachment.getFileName(),
				downloadUrl,
				file.getContentType(),
				file.getSize());
		
	}
	
	@GetMapping("/download/{fileld}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileld){
		Attachment attachment = null;
		
		try {
			attachment  = attachmentService.getAttachment(fileld);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(attachment.getFileType()))
				.header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + attachment.getFileName() + "\"")
				.body(new ByteArrayResource(attachment.getData()));
		
	}
}
