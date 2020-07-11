package com.petrolink.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.petrolink.model.AdminUser;

public interface AdminUserDao extends CrudRepository<AdminUser, Integer> {
	
	
	@Query("SELECT adminUser FROM AdminUser adminUser WHERE adminUser.userName = ?1 and adminUser.password = ?2")
	AdminUser findAdminUser(String userName, String password);

}
