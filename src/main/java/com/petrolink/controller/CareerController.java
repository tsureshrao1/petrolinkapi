package com.petrolink.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petrolink.dao.CareerDao;
import com.petrolink.model.Career;



@RestController
@RequestMapping("/petrolink")
public class CareerController {
	
	
	@Autowired
	private CareerDao careerDao;

	@PostMapping("/career")
	public String career(@RequestBody List<Career> carrers) {
		careerDao.save(carrers);
		return "Careers Added : " + carrers.size();
	}

	@GetMapping("/career")
	public List<Career> getCareers() {
		return (List<Career>) careerDao.findAll();
	}
	
	@GetMapping("/career/{id}")
	public Career get(@PathVariable int id) {
		Career carrer = careerDao.findOne(id);
		if(carrer != null) {
			return carrer;
		}else {
			throw new RuntimeException("Career not found for the id "+id);
		}
	}
	
	@DeleteMapping("/career/{id}")
	public String deleteCareer(@PathVariable int id) {
		try {
			careerDao.delete(id);
		}catch (Exception e) {
			throw e;
		}
		
		return "Career record delected";
	}
	
	
	@PutMapping("/career")
	public Career updateCareer(@RequestBody Career carrer) {
		careerDao.save(carrer);
		return careerDao.save(carrer);
	}

}
