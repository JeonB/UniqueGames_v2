package com.uniqueGames.controller;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.CompanyMemberService2;
import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JoinController {

	private MemberService memberService;
	private CompanyMemberService2 companyMemberService2;

	@Autowired
	public JoinController(MemberService memberService, CompanyMemberService2 companyMemberService2) {
		this.memberService = memberService;
		this.companyMemberService2 = companyMemberService2;
	}
	@GetMapping("join")
	public String join() {
		return "join/member-join";
	}

	@GetMapping("joincompany")
	public String companyJoin() { return "join/company-join"; }

	@PostMapping("join")
	public String joinProc(Member member, Model model) {
		int result = memberService.save(member);
		if(result == 1) {
			model.addAttribute("result", "join");
			model.addAttribute("url", "/login");
		}else {
			return "redirect:/";
		}
		return "login/login";
	}

	@PostMapping("joincompany")
	public String joinCompanyProc(Company company, Model model) {
		int result = companyMemberService2.save(company);
		if(result == 1) {
			model.addAttribute("result", "join");
			model.addAttribute("url", "/login");
		}else {
			return "redirect:/";
		}
		return "login/login";
	}



}
