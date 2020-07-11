package com.petrolink.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petrolink.email.EmailService;
import com.petrolink.email.MailRequest;
import com.petrolink.email.MailResponse;


@RestController
@RequestMapping("/petrolink")
public class SendEmail {
	
	@Autowired
	private EmailService service;

	@Value("${mail.from}")
	private String mailFrom;
	
	@Value("${mail.to}")
	private String mailTo;
	
	@PostMapping("/sendEmail")
	public MailResponse sendEmail(@RequestBody MailRequest request) {
		
		Map<String, Object> model = new HashMap<>();
		model.put("name", request.getName());
		model.put("email", request.getEmail());
		model.put("subject", request.getSubject());
		model.put("message", request.getMessage());
		
		model.put("mailFrom", mailFrom);
		model.put("mailTo", mailTo);
		
		return service.sendEmail(request, model);

	}
	public SendEmail() {
		// TODO Auto-generated constructor stub
	}

}
