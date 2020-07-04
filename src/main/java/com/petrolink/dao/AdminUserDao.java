package com.petrolink.dao;

import org.springframework.data.repository.CrudRepository;

import com.petrolink.model.AdminUser;

public interface AdminUserDao extends CrudRepository<AdminUser, Integer> {

}
