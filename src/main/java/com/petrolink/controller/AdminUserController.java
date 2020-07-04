package com.petrolink.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private AdminUserDao adminUserDao;

	@PostMapping("/admin/user")
	public AdminUser createAdminUser(@RequestBody AdminUser adminUser) {

		adminUser.setStatus(true);
		adminUser.setCreatedDate(new Date());
		adminUserDao.save(adminUser);
		return adminUser;
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
