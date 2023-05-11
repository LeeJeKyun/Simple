package com.petpan.www.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public void Login() {
		logger.info("/login [GET]");
		
		
		
	}
	@GetMapping("/kakao")
	public String Kakao(
			
			Model model, 
			 @RequestParam("code") String code
			
			) {
		logger.info("/kakao [GET]");
		logger.info("code : {}", code);
		
		model.addAttribute("code", code);
		
		return "skippage";
	}

	
}
