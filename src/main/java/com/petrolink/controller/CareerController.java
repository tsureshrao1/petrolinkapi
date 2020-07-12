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

import com.petrolink.dao.CareerDao;
import com.petrolink.dao.ProfileDao;
import com.petrolink.file.service.FileDeleteRunner;
import com.petrolink.model.Career;


@CrossOrigin
@RestController
@RequestMapping("/petrolink")
public class CareerController {
	
	
	
	@Autowired
	private CareerDao careerDao;	
	
	@Value("${filePath}")
	private String filePath;
	
	@Autowired
	private ProfileDao profileDao;
	
	/*
	 * @PostMapping("/careers") public List<Career> career(@RequestBody List<Career>
	 * carrers) { careerDao.save(carrers); return carrers; }
	 */
	
	
	@PostMapping("/career")
	public Career career(@RequestBody Career carrer) {
		
		carrer.setCreatedDate(new Date());
		carrer.setLastUpdatedDate(new Date());
		carrer.setStatus(true);
		careerDao.save(carrer);
		return carrer;
	}

	@GetMapping("/career")
	public List<Career> getCareers() {	
		
		return (List<Career>) careerDao.findCareersByStatusActive();
	}
	
	@GetMapping("/career/deactive")
	public List<Career> getDeactiveCareers() {	
		
		return (List<Career>) careerDao.findCareersByStatusDeActive();
	}
	
	@GetMapping("/career/{id}")
	public Career get(@PathVariable int id) {
		
		Career carrer = null;
		try {
			carrer = careerDao.findOne(id);
		}catch (Exception e) {
			throw new RuntimeException("Career not found for the id "+id, e);
		}
		
		return carrer;
	}
	
	@DeleteMapping("/career/{id}")
	public Career deleteCareer(@PathVariable int id) {
		Career carrer = null;
		try {
			carrer = careerDao.findOne(id);
			careerDao.delete(id);
			new Thread(new FileDeleteRunner(id, filePath, profileDao)).start();
			
		}catch (Exception e) {
			throw e;
		}
		
		return carrer;
	}
	
	
	@PutMapping("/career")
	public Career updateCareer(@RequestBody Career carrer) {
		carrer.setLastUpdatedDate(new Date());
		careerDao.save(carrer);
		return careerDao.save(carrer);
	}

}
