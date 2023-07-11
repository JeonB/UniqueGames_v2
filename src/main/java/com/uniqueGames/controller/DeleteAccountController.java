package com.uniqueGames.controller;


import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.MemberService;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DeleteAccountController {

	@Autowired
	private MemberService memberSerivce;

	@Autowired
	private CompanyMemberService companyMemberService;


	@GetMapping("deleteAccount")
	public String deletePwd(HttpSession session, Model model) {
		String viewName = "";
		String mode = session.getAttribute("login").toString();
		System.out.println("mode=" + mode);
		if(mode.equals("member")) {
			Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
			model.addAttribute("member", member.getMemberId());
			viewName = "deleteAccount/member-delete";
		}
		return viewName;
	}

//	@RequestMapping(value="/delete_check", method=RequestMethod.POST)
//	@ResponseBody
//	public String delete_check(Member member, HttpSession session) {
//		int result = memberSerivce.memberDeleteResult(member);
//
//		if(result==1) {
//			session.invalidate();
//		}
//		return String.valueOf(result);
//	}

	/**************************회원 탈퇴***************************************
	 @RequestMapping(value="/deleteCompany", method=RequestMethod.GET)
	 public ModelAndView deleteCompany(String company_id) {
	 ModelAndView mav = new ModelAndView();
	 CompanyVo companyVo = companyMemberService.companyPageResult(company_id);

	 mav.addObject("companyVo", companyVo);
	 mav.setViewName("/deleteAccount/deleteCompany");

	 return mav;
	 }
	 */
	@RequestMapping(value="/company_delete_check", method=RequestMethod.POST)
	@ResponseBody
	public String company_delete_check(Company company, HttpSession session) {
		int result = companyMemberService.companyDeleteResult(company);

		if(result==1) {
			session.invalidate();
		}

		return String.valueOf(result);
	}

}
