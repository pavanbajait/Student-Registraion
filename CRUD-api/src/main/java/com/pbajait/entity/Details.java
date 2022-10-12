package com.pbajait.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Details {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;

	private String affilation;
	
	@NotEmpty
	@Size(min = 2, message = "First Name must be min of 2 characters")
	private String firstName;

	@NotEmpty
	@Size(min = 2, message = "Last Name must be min of 2 characters")
	private String lastName;
	
	@NotEmpty(message = "Gender should not be empty")
	private String gender;
	
	@Email(message = "Email is not valid")
	@Column(unique = true)
	private String email;
	

	@NotNull
	@Pattern(regexp = "[0-9]{10}",message = "Mobile No must have 10 digits")
	private String mobileNo;
	
	@NotNull(message = "Enter the date of birth")
	private LocalDate dob;
	
	@NotEmpty
	@Size(min = 2, message = "city Name must be min of 2 characters")
	private String city;
//	
//	@NotEmpty
//	@Size(min = 2, message = "professionalSkills Name must be min of 2 characters")
//	private String[] professionalSkills;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "person")
	@JoinColumn(name = "imgId")
	private Attachment image;
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Details other = (Details) obj;
		return Objects.equals(email, other.email) && Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, firstName, lastName);
	}
	
	
	
	
}
