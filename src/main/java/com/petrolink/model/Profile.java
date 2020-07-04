package com.petrolink.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Profile")
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column
	private Integer careerId;

	@Column
	private String name;

	@Column
	private Date dateOfbirth;

	@Column
	private String nationality;

	@Column
	private String email;

	@Column
	private String phoneNumber;

	@Column
	private String mobileNumber;

	@Column
	private String describeCurrentJob;

	@Column
	private String expAbroad;

	@Column
	private String expOthers;

	@Column
	private String qualifications;

	@Column
	private String joiningPeriod;

	@Column
	private String currentSalary;

	@Column
	private String expSalary;

	@Column
	private String presentLocation;

	public Profile() {
		// TODO Auto-generated constructor stub
	}

	public Profile(Integer id, Integer careerId, String name, Date dateOfbirth, String nationality, String email,
			String phoneNumber, String mobileNumber, String describeCurrentJob, String expAbroad, String expOthers,
			String qualifications, String joiningPeriod, String currentSalary, String expSalary,
			String presentLocation) {
		super();
		this.id = id;
		this.careerId = careerId;
		this.name = name;
		this.dateOfbirth = dateOfbirth;
		this.nationality = nationality;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.describeCurrentJob = describeCurrentJob;
		this.expAbroad = expAbroad;
		this.expOthers = expOthers;
		this.qualifications = qualifications;
		this.joiningPeriod = joiningPeriod;
		this.currentSalary = currentSalary;
		this.expSalary = expSalary;
		this.presentLocation = presentLocation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCareerId() {
		return careerId;
	}

	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfbirth() {
		return dateOfbirth;
	}

	public void setDateOfbirth(Date dateOfbirth) {
		this.dateOfbirth = dateOfbirth;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getDescribeCurrentJob() {
		return describeCurrentJob;
	}

	public void setDescribeCurrentJob(String describeCurrentJob) {
		this.describeCurrentJob = describeCurrentJob;
	}

	public String getExpAbroad() {
		return expAbroad;
	}

	public void setExpAbroad(String expAbroad) {
		this.expAbroad = expAbroad;
	}

	public String getExpOthers() {
		return expOthers;
	}

	public void setExpOthers(String expOthers) {
		this.expOthers = expOthers;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getJoiningPeriod() {
		return joiningPeriod;
	}

	public void setJoiningPeriod(String joiningPeriod) {
		this.joiningPeriod = joiningPeriod;
	}

	public String getCurrentSalary() {
		return currentSalary;
	}

	public void setCurrentSalary(String currentSalary) {
		this.currentSalary = currentSalary;
	}

	public String getExpSalary() {
		return expSalary;
	}

	public void setExpSalary(String expSalary) {
		this.expSalary = expSalary;
	}

	public String getPresentLocation() {
		return presentLocation;
	}

	public void setPresentLocation(String presentLocation) {
		this.presentLocation = presentLocation;
	}

}
