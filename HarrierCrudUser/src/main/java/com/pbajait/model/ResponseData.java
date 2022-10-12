package com.pbajait.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {
	
	
	public String fileName;
	public String downloadUrl;
	public String fileType;
	public long fileSize;
	
	

	
	
}
