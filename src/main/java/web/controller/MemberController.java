package web.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.dto.Member;
import web.service.face.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired private MemberService memberService;

	@GetMapping("/join")
	public void join() {
		logger.info("/member/join [GET]");
		
	}
	
	@PostMapping("/join")
	public String joinProc(
			
			Member member
			
			
			) {
		logger.info("/member/join [POST]");
		logger.info("/member/join - joinMem : {}", member);
		
		memberService.join(member);
		
		return "redirect:/main";
	}
	
	@GetMapping("/login")
	public void login() {
		logger.info("/member/login [GET]");
		
	}
	
	@PostMapping("/login")
	public String loginProc(
			
			Member member
			, HttpSession session
			
			) {
		
		logger.info("/member/login [POST]");
		logger.info("member/login - loginMeme : {}", member);
		
		if(memberService.login(member)) {
			session.setAttribute("login", true);
			session.setAttribute("userid", member.getUserid());
		}
		
		return "redirect:/main";
	}
	
	@GetMapping("/logout")
	public String logout(
			
			HttpSession session
			
			) {
		logger.info("/member/logout [GET]");
		session.invalidate();
		
		return "redirect:/main";
		
	}
	
}
