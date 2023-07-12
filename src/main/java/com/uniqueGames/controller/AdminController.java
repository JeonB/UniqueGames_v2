package com.uniqueGames.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.Order;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.CompanyService;
import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    MemberService memberService;
    @Autowired
    CompanyMemberService companyMemberService;

    @RequestMapping(value = "/admin")
    public String admin(){
        return "admin/admin";
    }

    @RequestMapping(value = "/admin-member-list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_member_list(String type, Model model) {
        JsonObject jObj = new JsonObject();
        JsonArray jArray = new JsonArray();

        if(type.equals("member") || !type.equals("company") || type == null){
            ArrayList<Member> list = memberService.aGetMemberList();

            if (list.size() == 0) {
                jObj.addProperty("nothing", true);
            } else {
                jObj.addProperty("nothing", false);
                jObj.addProperty("memberCount", list.size());
                jObj.addProperty("memberType", "개인 회원");
                jObj.addProperty("name", "이름");

                for (Member member : list) {
                    JsonObject obj = new JsonObject();

                    obj.addProperty("id", member.getMemberId());
                    obj.addProperty("name", member.getName());

                    jArray.add(obj);
                }
            }
        } else if (type.equals("company")) {
            ArrayList<Company> list = companyMemberService.aGetMemberList();

            if (list.size() == 0) {
                jObj.addProperty("nothing", true);
            } else {
                jObj.addProperty("nothing", false);
                jObj.addProperty("memberCount", list.size());
                jObj.addProperty("memberType", "법인 회원");
                jObj.addProperty("name", "회사명");

                for (Company member : list) {
                    JsonObject obj = new JsonObject();

                    obj.addProperty("id", member.getCompanyId());
                    obj.addProperty("name", member.getName());

                    jArray.add(obj);
                }
            }
        }
        jObj.add("memberList", jArray);

        return new Gson().toJson(jObj);
    }

    @RequestMapping(value = "/admin-game-list")
    public String admin_game_list() {
        return "admin/admin-game-list";
    }

    @RequestMapping(value = "/admin-game-register")
    public String admim_game_register() {
        return "admin/admin-game-register";
    }

    @RequestMapping(value = "/admin-donation")
    public String admim_donation() {
        return "admin/admin-donation";
    }

    @RequestMapping(value = "/admin-detail-member")
    public String admin_detail_member() {
        return "admin/admin-detail-member";
    }

    @RequestMapping(value = "/admin-update-game")
    public String admin_update_game() {
        return "admin/admin-update-game";
    }
}
