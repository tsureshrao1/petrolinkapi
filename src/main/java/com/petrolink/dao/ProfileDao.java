package com.petrolink.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.petrolink.model.Profile;

public interface ProfileDao extends CrudRepository<Profile, Integer> {
	
	
	@Query("SELECT profile FROM Profile profile WHERE profile.careerId = ?1")
	List<Profile> findProfileByCareerId(Integer careerId);
	
	}