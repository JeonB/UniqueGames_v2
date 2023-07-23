package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.CompanyMemberService2;
import com.uniqueGames.service.MailSendService;
import com.uniqueGames.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class CheckRestController {

    private MemberService memberService;
    private CompanyMemberService2 companyMemberService2;
    private MailSendService mailSendService;

    @Autowired
    public CheckRestController(MemberService memberService,
                               CompanyMemberService2 companyMemberService2,
                               MailSendService mailSendService) {
        this.memberService = memberService;
        this.companyMemberService2 = companyMemberService2;
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


    /** load finding id result modal from find-member id */
	@PostMapping("findmid")
	public String findmid(@RequestParam("email")String email, @RequestParam("name")String name) {
		String result = memberService.findMid(email, name);
		return result;
	}

    /** load new password modal from find-member password */
    @PostMapping("changempass")
    public String moveChangePass(Model model,
                                 @RequestParam("email")String email,
                                 @RequestParam("member_id")String memberId,
                                 @RequestParam("name") String name) {

        String result = memberService.findMpass(email, memberId, name);
        return result;
    }

    @PostMapping(value="/deletecheck")
	public String deleteCheck(HttpSession session) {
        Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
		int result = memberService.delete(member.getMemberId(), member.getPassword());

		if(result==1) {
			session.invalidate();
		}
		return String.valueOf(result);
	}


    /**company*/
    @GetMapping("cidcheck")
    public String cidCheck(@RequestParam("company_id") String companyId) {
        int result = companyMemberService2.cidCheck(companyId);
        return String.valueOf(result);
    }

    @PostMapping("cphonecheck")
    public String cphoneCheck(@RequestParam("phone_num") String phoneNum) {
        int result = companyMemberService2.cphoneCheck(phoneNum);
        return String.valueOf(result);
    }

    @PostMapping("cemailcheck")
    public String cemailCheck(@RequestParam("email") String email) {
        int result = companyMemberService2.cemailCheck(email);
        return String.valueOf(result);
    }

    @PostMapping("findcid")
    public String findCid(@RequestParam("email")String email, @RequestParam("name")String name) {
        String result = companyMemberService2.findCid(email, name);
        return result;
    }

    /** load new password modal from find-member password */
    @PostMapping("changecpass")
    public String cmoveChangePass(Model model,
                                 @RequestParam("email")String email,
                                 @RequestParam("company_id")String companyId,
                                 @RequestParam("name") String name) {

        String result = companyMemberService2.findCpass(email, companyId, name);
        return result;
    }
    @PostMapping("/cdeletecheck")
    public String cdeleteCheck(HttpSession session) {
        Company company = (Company)session.getAttribute(SessionConstants.LOGIN_MEMBER);
        int result = companyMemberService2.cdelete(company.getCompanyId(), company.getPassword());

        if(result==1) {
            session.invalidate();
        }
        return String.valueOf(result);
    }

    @PostMapping("memberprofile")
    public String memberprofile(@Login Member member, MultipartFile image) {
        String oldFile = member.getProfileImg();
        String result = memberService.fileCheck(image);
        member.setProfileImg(result);
        int result1 = memberService.update(member);

        if(result1 == 1) {
            if(!member.getProfileImg().isEmpty()){
                memberService.fileSave();
                memberService.fileDelete(oldFile);
            }

        }
        return result;
    }

    @PostMapping("companyprofile")
    public String companyprofile(@Login Company company, MultipartFile image) {
        String oldFile = company.getProfileImg();
        String result = companyMemberService2.fileCheck(image);
        log.info(result);
        company.setProfileImg(result);
        int result1 = companyMemberService2.update(company);

        if(result1 == 1) {
            if(!company.getProfileImg().isEmpty()){
                companyMemberService2.fileSave();
                companyMemberService2.fileDelete(oldFile);
            }

        }
        return result;
    }


}
