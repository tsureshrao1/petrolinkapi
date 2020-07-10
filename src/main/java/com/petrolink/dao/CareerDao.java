package com.petrolink.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.petrolink.model.Career;

public interface CareerDao extends CrudRepository<Career, Integer> {
	
	@Query("SELECT career FROM Career career WHERE career.status = 1 order by career.id  desc")
	List<Career> findCareersByStatusActive();
	
	@Query("SELECT career FROM Career career WHERE career.status = 0 order by career.id  desc")
	List<Career> findCareersByStatusDeActive();

}
