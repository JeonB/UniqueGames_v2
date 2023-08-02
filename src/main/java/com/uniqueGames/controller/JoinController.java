package com.uniqueGames.controller;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class JoinController {

	private MemberService memberService;
	private CompanyMemberService companyMemberService;

	@Autowired
	public JoinController(MemberService memberService, CompanyMemberService companyMemberService) {
		this.memberService = memberService;
		this.companyMemberService = companyMemberService;
	}
	@GetMapping("join")
	public String join() {
		return "join/member-join";
	}

	@GetMapping("joincompany")
	public String companyJoin() { return "join/company-join"; }

	@PostMapping("join")
	public String joinProc(Member member, Model model) {
		String fileName = memberService.fileCheck(member.getFile());
		member.setProfileImg(fileName);
		int result = memberService.save(member);
		if(result == 1) {
			memberService.fileSave();
			model.addAttribute("result", "join");
			model.addAttribute("url", "/login");
		}else {
			return "redirect:/";
		}
		return "login/login";
	}
	@PostMapping("joincompany")
	public String joinCompanyProc(Company company, Model model) {
		String fileName = companyMemberService.fileCheck(company.getFile());
		company.setProfileImg(fileName);
		int result = companyMemberService.save(company);
		if(result == 1) {
			companyMemberService.fileSave();
			model.addAttribute("result", "join");
			model.addAttribute("url", "/login");
		}else {
			return "redirect:/";
		}
		return "login/login";
	}
}
