package com.pbajait.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.pbajait.entity.Attachment;

public interface AttachmentService {

	Attachment saveAttachment(Integer person_user_id,MultipartFile file) throws Exception;

	Attachment getAttachment(String field);

}
