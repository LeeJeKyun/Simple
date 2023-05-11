package com.petpan.www.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.petpan.www.member.service.OAuthService;

@Controller
public class LoginController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	OAuthService oAuthService;
	
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
		
		String access_Token = oAuthService.getKakaoAccessToken(code);
		
		oAuthService.bringKaKaoInfo(access_Token);
		
		model.addAttribute("code", code);
		
		return "kakao";
	}
	@GetMapping("/naver")
	public String Naver(
				
			@RequestParam("code") String code
			
			) {
		
		String access_Token = oAuthService.getNaverAccessToken(code);
		
		oAuthService.bringNaverInfo(access_Token);
		
		return "naver";
	}
	

	
}
