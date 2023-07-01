package com.uniqueGames.controller;


import com.uniqueGames.model.CompanyVo;
import com.uniqueGames.model.MemberVo;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.MemberService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FindAccountController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private CompanyMemberService companyMemberService;

	@RequestMapping(value="/findId", method=RequestMethod.GET)
	public String findId() {
		return "findAccount/findId";
	}
	
	@RequestMapping(value="/findPwd", method=RequestMethod.GET)
	public String findPwd() {
		return "findAccount/findPwd";
	}
	
	@RequestMapping("/findCompany")
	public String findCompany() {
		return "findAccount/findCompany";
	}

	@RequestMapping(value="/findId_check", method=RequestMethod.POST)
	@ResponseBody
	public String findId_check(MemberVo memberVo) {
		String result = memberService.memberFindIdResult(memberVo);
		return result;
	}
	
	/**Member password change; href to newpassword.jsp*/
	@RequestMapping(value="/findPwd_check", method=RequestMethod.POST)
	public ModelAndView findPwd_check(MemberVo memberVo) {
		ModelAndView mav = new ModelAndView();
		int result = memberService.memberFindPwdResult(memberVo);
		
		if(result == 1) {
			mav.addObject("member_id", memberVo.getMember_id());
			mav.setViewName("findAccount/newPassword");
		}else {
			mav.addObject("find_result", "fail");
			mav.setViewName("findAccount/findId");
		}
		
		return mav;
	}
	
	/** myPage -> changing Password*/
	@RequestMapping(value="/myPageChangePassword", method=RequestMethod.GET)
	public ModelAndView MyPageChangePassword(String member_id) {
		ModelAndView mav = new ModelAndView();
		MemberVo memberVo = memberService.memberMyPageResult(member_id);
		
		mav.addObject("member_id", memberVo.getMember_id());
		mav.addObject("password", memberVo.getPassword());
		mav.setViewName("findAccount/newPassword");
		
		return mav;
	}
	
	/**findPwd -> newpassword.jsp -> actual change password logic*/
	@RequestMapping(value="/mChangePassword", method=RequestMethod.POST)
	public ModelAndView Mnewpassword(String member_id, String mnewpassword, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int result = memberService.memberChangeMPassword(member_id, mnewpassword);
		
		if(result==1) {
			session.invalidate();
			mav.addObject("changePassword_result", "success");
			mav.setViewName("login/login");
		}else {
			System.out.println("비밀번호 변경 실패");
		}
		return mav;
	}
	
	/******************************************************************법인 아이디 중복확인**********************************************************************/
	
	@RequestMapping(value="/cfindId_check", method=RequestMethod.POST)
	@ResponseBody
	public String cfindId_check(CompanyVo companyVo) {
		String result = companyMemberService.companyFindIdResult(companyVo);
		
		return result;
	}
	
	/**CompanyMyPage -> changing Password*/
	@RequestMapping(value="/CompanyPageChangePassword", method=RequestMethod.GET)
	public ModelAndView CompanyPageChangePassword(String company_id) {
		ModelAndView mav = new ModelAndView();
		CompanyVo companyVo = companyMemberService.companyPageResult(company_id);
		mav.addObject("company_id", companyVo.getCompany_id());
		mav.addObject("password", companyVo.getPassword());
		
		mav.setViewName("findAccount/cnewPassword");
		return mav;
	}
	/**Company password change; href to cnewpassword.jsp*/
	@RequestMapping(value="/cfindPwd_check", method=RequestMethod.POST)
	public ModelAndView cfindPwd_check(CompanyVo companyVo) {
		ModelAndView mav = new ModelAndView();
		int result = companyMemberService.companyFindPwdResult(companyVo);
		
		if(result == 1) {
			mav.addObject("company_id", companyVo.getCompany_id());
			mav.addObject("password", companyVo.getPassword());
			mav.setViewName("findAccount/cnewPassword");
		}else {
			mav.addObject("find_result", "fail");
			mav.setViewName("findAccount/findCompany");
		}
		
		return mav;
	}
	
	@RequestMapping(value="/cChangePassword", method=RequestMethod.POST)
	public ModelAndView Cnewpassword(String company_id, String cnewpassword, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		int result = companyMemberService.companyChangeCPassword(company_id, cnewpassword);
		
		if(result==1) {
			session.invalidate();
			mav.addObject("changePassword_result", "success");
			mav.setViewName("login/login");
		}else {
			System.out.println("비밀번호 변경 실패");
		}
		return mav;
	}

}
