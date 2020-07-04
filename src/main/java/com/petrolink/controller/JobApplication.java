package com.petrolink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petrolink.dao.ProfileDao;
import com.petrolink.model.Profile;

@CrossOrigin
@RestController
@RequestMapping("/petrolink")
public class JobApplication {

	@Autowired
	private ProfileDao profileDao;

	public JobApplication() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/profile")
	public Profile profle(@RequestBody Profile profile) {

		profileDao.save(profile);
		return profile;
	}

	@GetMapping("/profile")
	public List<Profile> getProfiles() {
		return (List<Profile>) profileDao.findAll();
	}

	@GetMapping("/profile/career/{careerId}")
	public List<Profile> get(@PathVariable int careerId) {

		List<Profile> profiles = null;
		try {
			profiles = profileDao.findProfileByCareerId(careerId);
		} catch (Exception e) {
			throw new RuntimeException("Job Posting not found for the careerId " + careerId, e);
		}

		return profiles;
	}

}
