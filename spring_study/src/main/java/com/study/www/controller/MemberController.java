package com.study.www.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.study.www.security.MemberVO;
import com.study.www.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member/*")
@Controller
public class MemberController {

	private final MemberService msv;
	private final BCryptPasswordEncoder bcEncoder;
	
	@GetMapping("/memberRegister")
	public void register() {}
	@PostMapping("/memberRegister")
	public String insert(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
		int isOk = msv.register(mvo);
		return "index";
	}
	@GetMapping("/memberLogin")
	public void login() {}
	
	@PostMapping("/memberLogin")
	public String login(HttpServletRequest request, RedirectAttributes re) {
		
		re.addAttribute("email",request.getAttribute("email"));
		log.info("email>>>> {}",request.getAttribute("email"));
		re.addAttribute("errMsg",request.getAttribute("errMsg"));
		return "redirect:/member/memberLogin";
	}
//	리스트, 수정,삭제 
	
	@GetMapping("/memberList")
	public void list(Model m) {
		m.addAttribute("list",msv.getList());
		log.info("<< memberList >>");
	}
	
	@GetMapping("/memberModify")
	public void modify (Principal p,Model m) {
		String email = p.getName();
		m.addAttribute("mvo",msv.detail(email));
	}
	@PostMapping("/memberModify")
	public String modify(MemberVO mvo, HttpServletRequest request, HttpServletResponse response) {
		int isOk = 2;
		if(mvo.getPwd().isEmpty()) {
			isOk = msv.modifyPwdEmpty(mvo);
		} else {
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			isOk = msv.modify(mvo);
		}
		// 로그아웃 진행
		logout(request, response);
		
		return "redirect:/member/memberLogin";
	}
	@GetMapping("/remove")
	public String removeMember(@RequestParam("email") String email, HttpServletRequest req, HttpServletResponse res) {
		int isOk = msv.remove(email);
		logout(req, res);
		return "redirect:/";
	}
	
	private void logout(HttpServletRequest req, HttpServletResponse res) {
		// 로그아웃 진행
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req, res, authentication);
	}
	
	
	
}
