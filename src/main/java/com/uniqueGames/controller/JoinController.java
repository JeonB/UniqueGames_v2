package com.uniqueGames.controller;

import com.uniqueGames.model.Member;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.MailSendService;
import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class JoinController {

	private MemberService memberService;

	@Autowired
	public JoinController(MemberService memberService) {
		this.memberService = memberService;
	}
	@GetMapping("join")
	public String join() {
		return "join/join";
	}

//	@PostMapping("join")
//	public String joinProc(Member member, Model model) {
//		int result = memberService.save(member);
//		if(result == 1) {
//			model.addAttribute("join_result", "success");
//		}else {
//			return "redirect:/";
//		}
//		return "redirect:/login";
//	}
//
//	@ResponseBody
//	@GetMapping("idcheck")
//	public String idCheck(@RequestParam("member_id") String memberId) {
//		int result = memberService.idCheck(memberId);
//		return String.valueOf(result);
//	}
}
