package com.uniqueGames.controller;


import com.uniqueGames.service.CompanyMemberService2;
import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FindAccountController {

	private MemberService memberService;
	private CompanyMemberService2 companyMemberService2;

	@Autowired
	public FindAccountController(MemberService memberService, CompanyMemberService2 companyMemberService2) {
		this.memberService = memberService;
		this.companyMemberService2 = companyMemberService2;
	}

	@GetMapping("findMember")
	public String findId() {
		return "findAccount/member-find";
	}

	@RequestMapping(value="/findPwd", method=RequestMethod.GET)
	public String findPwd() {
		return "findPwd";
	}

	@GetMapping("/findCompany")
	public String findCompany() {
		return "findAccount/company-find";
	}

	/** changing password on modal */
	@PostMapping("/mchangepass")
	public String mChangePass(Model model,
								 @RequestParam("memberId") String memberId, @RequestParam("newpassword") String newpassword) {

		int result = memberService.changeMpass(memberId, newpassword);
		if(result == 1) {
			model.addAttribute("result", "change");
			model.addAttribute("url", "/login");
			return "login/login";
		}else {
			return "redirect:/findMember";
		}
	}

	@PostMapping("/cchangepass")
	public String cChangePass(Model model,
							  @RequestParam("companyId") String companyId, @RequestParam("newpassword") String newpassword) {

		int result = companyMemberService2.changeCpass(companyId, newpassword);
		if(result == 1) {
			model.addAttribute("result", "change");
			model.addAttribute("url", "/login");
			return "login/login";
		}else {
			return "redirect:/findMember";
		}
	}
}
