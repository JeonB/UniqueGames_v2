package com.uniqueGames.controller;


import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.SessionConstants;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DeleteAccountController {

	@GetMapping("deleteaccount")
	public String deletePwd(HttpSession session, Model model) {
		String viewName = "";
		String mode = session.getAttribute("login").toString();

		if(mode.equals("member")) {
			Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
			model.addAttribute("member", member);
			viewName = "deleteAccount/member-delete";
		}else if(mode.equals("company")){
			Company company = (Company)session.getAttribute(SessionConstants.LOGIN_MEMBER);
			model.addAttribute("company", company);
			viewName = "deleteAccount/company-delete";
		}
		return viewName;
	}

}
