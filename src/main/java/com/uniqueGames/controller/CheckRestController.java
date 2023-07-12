package com.uniqueGames.controller;

import com.uniqueGames.model.Member;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.MailSendService;
import com.uniqueGames.service.MemberService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
public class CheckRestController {

    private MemberService memberService;
    private MailSendService mailSendService;

    public CheckRestController(MemberService memberService, MailSendService mailSendService) {
        this.memberService = memberService;
        this.mailSendService = mailSendService;
    }

    @GetMapping("idcheck")
    public String idCheck(@RequestParam("member_id") String memberId) {
        int result = memberService.idCheck(memberId);
        return String.valueOf(result);
    }

    @PostMapping("phonecheck")
    public String phoneCheck(@RequestParam("phone_num") String phoneNum) {
        int result = memberService.phoneCheck(phoneNum);
        return String.valueOf(result);
    }

    @PostMapping("emailcheck")
    public String emailCheck(@RequestParam("email") String email) {
        int result = memberService.emailCheck(email);
        return String.valueOf(result);
    }

    @PostMapping("mailCheck")
    public String mailSend(@RequestParam("email") String email) {
        return mailSendService.joinEmail(email);
    }


	@PostMapping("findmid")
	public String findmid(@RequestParam("email")String email, @RequestParam("name")String name) {
		String result = memberService.findMid(email, name);
		System.out.println("아이디 찾기 result="+result);
		return result;
	}

    @PostMapping("changepass")
    public String moveChangePass(Model model,
                                 @RequestParam("email")String email,
                                 @RequestParam("member_id")String memberId,
                                 @RequestParam("name") String name) {

//        String result = memberService.findMpass(email, memberId, name);
//        return result;
            return "";
    }

    @RequestMapping(value="/deletecheck", method=RequestMethod.POST)
	public String deleteCheck(HttpSession session) {
        Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
		int result = memberService.delete(member.getMemberId(), member.getPassword());

		if(result==1) {
			session.invalidate();
		}
		return String.valueOf(result);
	}
}
