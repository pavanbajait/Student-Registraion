package com.pbajait.entity;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attachment {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	
	private String fileName;
	
	private String fileType;
	
	@Lob
	private byte[] data;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Details person;


	public Attachment(Details person,String fileName, String fileType, byte[] data) {
		this.person = person;
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	
	
	
}
