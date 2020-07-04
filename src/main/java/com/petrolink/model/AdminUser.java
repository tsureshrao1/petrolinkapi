package com.petrolink.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AdminUser")

public class AdminUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;

	@Column()
	private String userName;

	@Column
	private String password;

	@Column
	private String email;

	@Column
	private boolean status;

	@Column
	private Date createdDate;

	@Column
	private Date lastLoggedIn;

	@Column
	private Date lastLoggedOut;

	public AdminUser() {
		super();
	}

	public AdminUser(Integer id, String userName, String password, String email, boolean status, Date createdDate,
			Date lastLoggedIn, Date lastLoggedOut) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.status = status;
		this.createdDate = createdDate;
		this.lastLoggedIn = lastLoggedIn;
		this.lastLoggedOut = lastLoggedOut;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastLoggedIn() {
		return lastLoggedIn;
	}

	public void setLastLoggedIn(Date lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	public Date getLastLoggedOut() {
		return lastLoggedOut;
	}

	public void setLastLoggedOut(Date lastLoggedOut) {
		this.lastLoggedOut = lastLoggedOut;
	}

}
