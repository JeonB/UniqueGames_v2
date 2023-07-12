package com.uniqueGames.controller;


import com.uniqueGames.repository.MemberRepositoryMapper;
import com.uniqueGames.service.CompanyMemberService;

import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FindAccountController {

	private MemberService memberService;

	public FindAccountController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("findMember")
	public String findId() {
		return "findAccount/find-member";
	}

	@RequestMapping(value="/findPwd", method=RequestMethod.GET)
	public String findPwd() {
		return "findPwd";
	}

	@GetMapping("/findCompany")
	public String findCompany() {
		return "findAccount/find-company";
	}

	@PostMapping("/mchangepass")
	public String mChangePass(Model model,
								 @RequestParam("memberId") String memberId, @RequestParam("mnewpassword") String mnewpassword) {
		System.out.println("왜 안돼");
//		int result = memberService.changeMpass(memberId, mnewpassword);
//		if(result == 1) {
//			model.addAttribute("result", "success");
			return "redirect:/login";
//		}else {
//			model.addAttribute("result", "error");
//			return "redirect:/findMember";
//		}
	}

//	@PostMapping("findId_check")
//	@ResponseBody
//	public String findId_check(Member member) {
//		String result = memberService.memberFindIdResult(member);
//		return result;
//	}

//	@PostMapping("findIdCheck")
//	@ResponseBody
//	public String findIdCheck(Member member) {
//		String result = memberRepositoryMapper.findId(member);
//		return result;
//	}
//
//	/**Member password change; href to newpassword.jsp*/
//	@RequestMapping(value="/findPwd_check", method=RequestMethod.POST)
//	public ModelAndView findPwd_check(Member member) {
//		ModelAndView mav = new ModelAndView();
//		int result = memberServiceMethod.memberFindPwdResult(member);
//
//		if(result == 1) {
//			mav.addObject("memberId", member.getMemberId());
//			mav.setViewName("member-newpass");
//		}else {
//			mav.addObject("find_result", "fail");
//			mav.setViewName("find-member");
//		}
//
//		return mav;
//	}
//
//	/** myPage -> changing Password*/
//	@GetMapping("myPageChangePassword")
//	public ModelAndView MyPageChangePassword(String memberId) {
//		ModelAndView mav = new ModelAndView();
//		Member member = memberServiceMethod.memberMyPageResult(memberId);
//
//		mav.addObject("memberId", member.getMemberId());
//		mav.addObject("password", member.getPassword());
//		mav.setViewName("member-newpass");
//
//		return mav;
//	}
//
//	/**findPwd -> newpassword.jsp -> actual change password logic*/
//	@RequestMapping(value="/mChangePassword", method=RequestMethod.POST)
//	public ModelAndView Mnewpassword(String memberId, String mnewpassword, HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		int result = memberServiceMethod.memberChangeMPassword(memberId, mnewpassword);
//
//		if(result==1) {
//			session.invalidate();
//			mav.addObject("changePassword_result", "success");
//			mav.setViewName("login/login");
//		}else {
//			System.out.println("비밀번호 변경 실패");
//		}
//		return mav;
//	}
//
//	/******************************************************************법인 아이디 중복확인**********************************************************************/
//
//	@RequestMapping(value="/cfindId_check", method=RequestMethod.POST)
//	@ResponseBody
//	public String cfindId_check(Company company) {
//		String result = companyMemberService.companyFindIdResult(company);
//
//		return result;
//	}
//
//	/**CompanyMyPage -> changing Password*/
//	@RequestMapping(value="/CompanyPageChangePassword", method=RequestMethod.GET)
//	public ModelAndView CompanyPageChangePassword(String companyId) {
//		ModelAndView mav = new ModelAndView();
//		Company company = companyMemberService.companyPageResult(companyId);
//		mav.addObject("companyId", company.getCompanyId());
//		mav.addObject("password", company.getPassword());
//
//		mav.setViewName("company-newpass");
//		return mav;
//	}
//	/**Company password change; href to cnewpassword.jsp*/
//	@RequestMapping(value="/cfindPwd_check", method=RequestMethod.POST)
//	public ModelAndView cfindPwd_check(Company company) {
//		ModelAndView mav = new ModelAndView();
//		int result = companyMemberService.companyFindPwdResult(company);
//
//		if(result == 1) {
//			mav.addObject("companyId", company.getCompanyId());
//			mav.addObject("password", company.getPassword());
//			mav.setViewName("company-newpass");
//		}else {
//			mav.addObject("find_result", "fail");
//			mav.setViewName("find-company");
//		}
//
//		return mav;
//	}
//
//	@RequestMapping(value="/cChangePassword", method=RequestMethod.POST)
//	public ModelAndView Cnewpassword(String companyId, String cnewpassword, HttpSession session) {
//		ModelAndView mav = new ModelAndView();
//		int result = companyMemberService.companyChangeCPassword(companyId, cnewpassword);
//
//		if(result==1) {
//			session.invalidate();
//			mav.addObject("changePassword_result", "success");
//			mav.setViewName("login/login");
//		}else {
//			System.out.println("비밀번호 변경 실패");
//		}
//		return mav;
//	}

}
