package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.MailSendService;
import com.uniqueGames.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class CheckRestController {

    private MemberService memberService;
    private CompanyMemberService companyMemberService;
    private MailSendService mailSendService;

    @Autowired
    public CheckRestController(MemberService memberService,
                               CompanyMemberService companyMemberService,
                               MailSendService mailSendService) {
        this.memberService = memberService;
        this.companyMemberService = companyMemberService;
        this.mailSendService = mailSendService;
    }

    @PostMapping("mailCheck")
    public String mailSend(@RequestParam("email") String email) {
        return mailSendService.joinEmail(email);
    }


    /**0729 변경사항*/
    @PostMapping("idcheck1")
    public String idCheck1(@RequestParam("id") String id,
                           @RequestParam("type2") String type2) {
        int result = memberService.idCheck1(id, type2);
        return String.valueOf(result);
    }

    @PostMapping("phonecheck1")
    public String phoneCheck1(@RequestParam("phone_num") String phoneNum,
                              @RequestParam("type2") String type2) {
        int result = memberService.phoneCheck1(phoneNum, type2);
        return String.valueOf(result);
    }

    @PostMapping("emailduplicatecheck")
    public String emailDuplicateCheck(@RequestParam("email") String email,
                                      @RequestParam("type2") String type2) {
        int result = memberService.emailDuplicateCheck(email, type2);
        return String.valueOf(result);
    }

    @PostMapping("findId")
    public String findId(@RequestParam("email")String email,
                         @RequestParam("name") String name,
                         @RequestParam("type2")String type2) {
        String result = memberService.findId(email, name, type2);
        return result;
    }

    /** load new password modal from find-member password */
    @PostMapping("changepass")
    public String moveChangePass(@RequestParam("email")String email,
                                 @RequestParam("id")   String id,
                                 @RequestParam("name") String name,
                                 @RequestParam("type2")String type2) {

        String result = memberService.findPass(email, id, name, type2);
        return result;
    }

    @PostMapping(value="/deletecheck1")
    public String deleteCheck1(HttpSession session,
                                @RequestParam("id") String id,
                                @RequestParam("password") String password,
                                @RequestParam("type2") String type2) {

        int result = memberService.delete1(id, password, type2);
        if(result==1) {
            session.invalidate();
        }
        return String.valueOf(result);
    }
}
