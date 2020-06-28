package com.petrolink.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Career")

public class Career {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Integer id;
	
	@Column
	private String role;
	@Column
	private String skills;
	@Column
	private String location;
	@Column
	private String jobDescription;
		
	public Career() {
		super();
	}
	
	
	
	
	public Career(String role, String skills, String location, String jobDescription) {
		super();
		this.role = role;
		this.skills = skills;
		this.location = location;
		this.jobDescription = jobDescription;
	}

   
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}




	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	
	
	

}
