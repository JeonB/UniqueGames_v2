package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.repository.CompanyRepositoryMapper;
import com.uniqueGames.repository.MemberRepositoryMapper;
import com.uniqueGames.service.CompanyMemberService2;
import com.uniqueGames.service.MemberService;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class MyPageController {

    private MemberService memberService;
    private CompanyMemberService2 companyMemberService2;
    private MemberRepositoryMapper memberRepositoryMapper;
    private CompanyRepositoryMapper companyRepositoryMapper;
    @Autowired
    public MyPageController(MemberService memberService, CompanyMemberService2 companyMemberService2,
                            MemberRepositoryMapper memberRepositoryMapper, CompanyRepositoryMapper companyRepositoryMapper) {
        this.memberService = memberService;
        this.companyMemberService2 = companyMemberService2;
        this.memberRepositoryMapper = memberRepositoryMapper;
        this.companyRepositoryMapper = companyRepositoryMapper;
    }

    @GetMapping("/mypage")
    public String mypage(HttpSession session, Model model) {
        String viewName = "";
        String addr = "";
        String addr1 = "";
        String addr2 = "";

        String mode = session.getAttribute(SessionConstants.LOGIN_MEMBER).toString();

        if(mode.contains("Member")) {
            Member member = memberRepositoryMapper.findById(((Member)session.getAttribute(SessionConstants.LOGIN_MEMBER)).getMemberId());

            addr = member.getAddr();
            if(!addr.equals("   ")){
                String[] addrSplit = addr.split("   ");
                if (addrSplit.length == 1) {
                    addr1 = addrSplit[0];
                } else if (addrSplit.length == 2) {
                    addr1 = addrSplit[0];
                    addr2 = addrSplit[1];
                }
            }else {
                addr1 = "";
                addr2 = "";
            }
            member.setAddr1(addr1);
            member.setAddr2(addr2);
            model.addAttribute("member", member);
            viewName = "myPage/member-page";
        }else if(mode.contains("Company")) {
            Company company = companyRepositoryMapper.findById(((Company) session.getAttribute(SessionConstants.LOGIN_MEMBER)).getCompanyId());
            String game = companyMemberService2.gameName(company.getCompanyId());
            addr = company.getAddr();
            if(!addr.equals("   ")){
                String[] addrSplit = addr.split("   ");
                if (addrSplit.length == 1) {
                    addr1 = addrSplit[0];
                } else if (addrSplit.length == 2) {
                    addr1 = addrSplit[0];
                    addr2 = addrSplit[1];
                }
            }else {
                addr1 = "";
                addr2 = "";
            }
            company.setGame(game);
            company.setAddr1(addr1);
            company.setAddr2(addr2);

            model.addAttribute("company", company);
            viewName = "myPage/company-page";
        }

        return viewName;
    }



    @PostMapping("memberupdate")
    public String memberUpdate(HttpSession session, Member member,
                               @RequestParam("deleteImg") String deleteImg) {
        String oldFile = member.getProfileImg();
        String fileName = memberService.fileCheck(member.getFile());
        if(fileName != null) { //파일이 넘어오면
            member.setNewProfileImg(fileName);
        } else { //파일이 안 넘어오면
            member.setNewProfileImg(oldFile);
        }
        //기본 값 클릭해서 이미지 삭제 할 때
        if(deleteImg.equals("delete")) {
            memberService.fileDelete(oldFile);
            member.setNewProfileImg("");
        }

        int result = memberService.update(member);
        if(result == 1) {
            memberService.fileSave();
            if(!oldFile.isEmpty() && !member.getNewProfileImg().equals(oldFile)){
                //기존 파일이 있을 때 그리고 변경된 프로필 이미지와 기존 프로필 이미지가 다를 때 기존 프로필 이미지를 삭제
                memberService.fileDelete(oldFile);
            }
            session.setAttribute("login", "member");
        }else {
            System.out.println("수정 실패");
        }
        return "redirect:/";
    }

    @PostMapping("/companyupdate")
    public String companyUpdate(HttpSession session, Company company) {
        String oldFile = company.getProfileImg();
        String fileName = companyMemberService2.fileCheck(company.getFile());
        if(fileName != null) {
            company.setNewProfileImg(fileName);
        }else {
            company.setNewProfileImg(oldFile);
        }

        int result = companyMemberService2.update(company);
        if(result == 1) {
            companyMemberService2.fileSave();
            if(!oldFile.isEmpty() && !company.getNewProfileImg().equals(oldFile)){
                companyMemberService2.fileDelete(oldFile);
            }
            session.setAttribute("login", "company");
            System.out.println("마이페이지 수정 완료");
        }else {
            System.out.println("수정 실패");
        }
        return "redirect:/";
    }


    @GetMapping("newpass")
    public String mypageNewPass(HttpSession session, Model model) {
        String mode = session.getAttribute("login").toString();
        String viewName = "";
        if(mode.equals("member")){
            Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
            model.addAttribute("member", member);
            viewName = "findAccount/member-newpass";
        }else if(mode.equals("company")){
            Company company = (Company)session.getAttribute(SessionConstants.LOGIN_MEMBER);
            model.addAttribute("company", company);
            viewName = "findAccount/company-newpass";
        }
        return viewName;
    }

    @PostMapping("/mypagenewpass")
    public String mypageNewPass(HttpSession session, Model model,
                                @RequestParam("password") String password,
                                @RequestParam("newpassword") String newpassword) {
        String viewName = "";
        Member member = (Member)session.getAttribute(SessionConstants.LOGIN_MEMBER);
        int result = memberService.mypageNewPass(member.getMemberId(), password, newpassword);

        if(result == 1) {
            session.invalidate();
            model.addAttribute("result", "change");
            model.addAttribute("url", "/login");
            viewName = "login/login";
        }else {
            viewName = "redirect:/";
        }
        return viewName;
    }

    @PostMapping("/cmypagenewpass")
    public String CmypageNewPass(HttpSession session, Model model,
                                @RequestParam("password") String password,
                                @RequestParam("newpassword") String newpassword) {
        String viewName = "";
        Company company = (Company)session.getAttribute(SessionConstants.LOGIN_MEMBER);
        int result = companyMemberService2.CmypageNewPass(company.getCompanyId(), password, newpassword);
        if(result == 1) {
            session.invalidate();
            model.addAttribute("result", "change");
            model.addAttribute("url", "/login");
            viewName = "login/login";
        }else {
            viewName = "redirect:/";
        }
        return viewName;
    }

    @GetMapping("mypagepayment")
    public String mypagePayment(@Login Member member, Model model) {
        model.addAttribute("member", member);
        return "myPage/member-payment";
    }

    @GetMapping("mypagedonation")
    public String mypageDonation(@Login Company company, Model model) {
        model.addAttribute("company", company);
        return "myPage/company-donation";
    }
}
