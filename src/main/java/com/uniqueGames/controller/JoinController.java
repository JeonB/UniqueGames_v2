package com.uniqueGames.controller;


import com.uniqueGames.model.CompanyVo;
import com.uniqueGames.model.MemberVo;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.MailSendService;
import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class JoinController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CompanyMemberService companyMemberService;
	
	@Autowired
	private MailSendService mailService;

	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
	
	@RequestMapping(value="/join_individual_proc", method=RequestMethod.POST)
	public ModelAndView join_proc(MemberVo memberVo) {
		ModelAndView mav = new ModelAndView();
		int result = memberService.memberJoinResult(memberVo);
		
		if(result==1) {
			mav.addObject("join_individual_result", "success");
			mav.setViewName("/login/login");
		}else {
			System.out.println("회원가입 실패");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	@ResponseBody
	public String id_check(String member_id) {
		return memberService.memberIdCheckResult(member_id);
	}
	
	/**이메일 중복 확인*/
	@RequestMapping(value="/email_check", method=RequestMethod.POST)
	@ResponseBody
	public String email_check(String email1, String email2) {
		String email = email1 + "@" + email2;
		
		int result = memberService.memberEmailCheckResult(email);
		return String.valueOf(result);
	}
	
	/**휴대전화 중복확인*/
	@RequestMapping(value="/phone_check", method=RequestMethod.POST)
	@ResponseBody
	public String phone_check(String phone1, String phone2, String phone3) {
		String phone_num = phone1+"-"+phone2+"-"+phone3;
		
		int result = memberService.memberPhoneCheckResult(phone_num);
		return String.valueOf(result);
	}
	
	@RequestMapping(value="/mailCheck", method=RequestMethod.POST)
	@ResponseBody
	public String mailCheck(String email) {
		return mailService.joinEmail(email);
	}
	
	/******************************************************************법인 회원가입**********************************************************************/
	@RequestMapping(value="/join_company_proc", method=RequestMethod.POST)
	public ModelAndView companyJoin(CompanyVo companyVo) {
		ModelAndView mav = new ModelAndView();
		int result = companyMemberService.companyJoinResult(companyVo);
		
		if(result==1) {
			mav.addObject("join_company_result", "success");
			mav.setViewName("/login/login");
		}else {
			System.out.println("회원가입 실패");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/c_id_check", method=RequestMethod.GET)
	@ResponseBody
	public String c_id_check(String company_id) {
		int result = companyMemberService.companyIdCheckResult(company_id);
		
		return String.valueOf(result);
	}
	
	/**법인 이메일 중복확인*/
	@RequestMapping(value="/c_email_check", method=RequestMethod.POST)
	@ResponseBody
	public String c_email_check(String email1, String email2) {
		String email = email1 + "@" + email2;
		int result = companyMemberService.companyEmailCheckResult(email);
		
		return String.valueOf(result);
	}
	
	
	
	/**법인 휴대전화 중복확인*/
	@RequestMapping(value="/c_phone_check", method=RequestMethod.POST)
	@ResponseBody
	public String c_phone_check(String phone1, String phone2, String phone3) {
		String phone_num = phone1+"-"+phone2+"-"+phone3;
		
		int result = companyMemberService.companyPhoneCheckResult(phone_num);
		return String.valueOf(result);
	}
	
	
}
