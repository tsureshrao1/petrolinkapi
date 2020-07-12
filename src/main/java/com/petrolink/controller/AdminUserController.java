package com.petrolink.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petrolink.dao.AdminUserDao;
import com.petrolink.model.AdminUser;

@CrossOrigin
@RestController
@RequestMapping("/petrolink")
public class AdminUserController {
	
	
	@Value("${admin.default.user}")
	private String adminDefautUser;
	
	@Value("${admin.default.password}")
	private String adminDefautPassword;

	@Autowired
	private AdminUserDao adminUserDao;

	@PostMapping("/admin/user")
	public AdminUser createAdminUser(@RequestBody AdminUser adminUser) {

		
		AdminUser user = adminUserDao.findAdminUser(adminUser.getUserName(), adminUser.getPassword());
		
		if(user != null) {
			System.out.println("Existing user with same username and password found. User Name : "+adminUser.getUserName());
			return null;
			
		}
			
		adminUser.setStatus(true);
		adminUser.setCreatedDate(new Date());
		adminUserDao.save(adminUser);
		
		System.out.println("New User created with username and password. User Name : "+adminUser.getUserName());
		
		return adminUser;
	}
	
	
	@PostMapping("/admin/user/login")
	public AdminUser loginAdminUser(@RequestBody AdminUser adminUser) {

		AdminUser user = adminUserDao.findAdminUser(adminUser.getUserName(), adminUser.getPassword());
		
		if(user != null) {
			System.out.println("Login user name and password found. User Name : "+adminUser.getUserName());
			return user;
		}
		
		if(user == null && adminDefautUser.equals(adminUser.getUserName()) 
				&& adminDefautPassword.equals(adminUser.getPassword())) {
			
			adminUser.setStatus(true);
			adminUser.setCreatedDate(new Date());
			adminUserDao.save(adminUser);
			
			return adminUser;
		}
		
		System.out.println("Login user name and password not found. User Name : "+adminUser.getUserName());
		
		return null;
	}
	
	

	@GetMapping("/admin/user")
	public List<AdminUser> getAdminUsers() {
		return (List<AdminUser>) adminUserDao.findAll();
	}

	@GetMapping("/admin/user/{id}")
	public AdminUser get(@PathVariable int id) {

		AdminUser adminUser = null;
		try {
			adminUser = adminUserDao.findOne(id);
		} catch (Exception e) {
			throw new RuntimeException("Admin User not found for the id " + id, e);
		}

		return adminUser;
	}

	@DeleteMapping("/admin/user/{id}")
	public AdminUser deleteAdminUser(@PathVariable int id) {
		AdminUser adminUser = null;
		try {
			adminUser = adminUserDao.findOne(id);
			adminUserDao.delete(id);
		} catch (Exception e) {
			
			throw new RuntimeException("Admin User not found for the id " + id, e);
			
		}

		return adminUser;
	}

	@PutMapping("/admin/user")
	public AdminUser updateAdminUser(@RequestBody AdminUser adminUser) {

		adminUserDao.save(adminUser);
		return adminUserDao.save(adminUser);
	}

}
