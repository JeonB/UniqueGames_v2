package com.uniqueGames.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uniqueGames.fileutil.AdminUtil;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.Notice;
import com.uniqueGames.model.Order;
import com.uniqueGames.service.CompanyMemberService;
import com.uniqueGames.service.CompanyMemberService2;
import com.uniqueGames.service.CompanyService;
import com.uniqueGames.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Controller
public class AdminController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CompanyMemberService2 companyMemberService;
    @Autowired
    private AdminUtil adminUtil;

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin/admin";
    }

    @RequestMapping(value = "/admin-member-list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_member_list(String type, String array, String page, Model model) {
        JsonObject jObj = new JsonObject();
        JsonArray jArray = new JsonArray();
        String order1;
        String order2;

        if (array.equals("id_asc")) {
            order1 = "ID";
            order2 = "ASC";
        } else if (array.equals("id_desc")) {
            order1 = "ID";
            order2 = "DESC";
        } else if (array.equals("name_asc")) {
            order1 = "NAME";
            order2 = "ASC";
        } else {
            order1 = "NAME";
            order2 = "DESC";
        }

        Map<String, Integer> pageMap = adminUtil.getPagination(page, "list", type);
        if (type.equals("member") || !type.equals("company") || type == null) {
            if (order1.equals("ID")) {
                order1 = "MEMBER_ID";
            }
            ArrayList<Member> list = memberService.aGetMemberList(order1, order2, pageMap.get("startCount"), pageMap.get("endCount"));

            if (list.size() == 0) {
                jObj.addProperty("nothing", true);
            } else {
                jObj.addProperty("nothing", false);
                jObj.addProperty("name", "이름");
                jObj.addProperty("str", "전체 개인 회원 : " + list.size() + "명");

                for (Member member : list) {
                    JsonObject obj = new JsonObject();

                    obj.addProperty("id", member.getMemberId());
                    obj.addProperty("name", member.getName());

                    jArray.add(obj);
                }
            }
        } else if (type.equals("company")) {
            if (order1.equals("ID")) {
                order1 = "COMPANY_ID";
            }
            ArrayList<Company> list = companyMemberService.aGetMemberList(order1, order2);

            if (list.size() == 0) {
                jObj.addProperty("nothing", true);
            } else {
                jObj.addProperty("nothing", false);
                jObj.addProperty("name", "회사명");
                jObj.addProperty("str", "전체 법인 회원 : " + list.size() + "명");

                for (Company member : list) {
                    JsonObject obj = new JsonObject();

                    obj.addProperty("id", member.getCompanyId());
                    obj.addProperty("name", member.getName());

                    jArray.add(obj);
                }
            }
        }

        jObj.add("memberList", jArray);
        jObj.addProperty("mType", type);
        jObj.addProperty("dbCount", pageMap.get("dbCount"));
        jObj.addProperty("pageSize", pageMap.get("pageSize"));
        jObj.addProperty("maxSize", pageMap.get("maxSize"));
        jObj.addProperty("page", pageMap.get("reqPage"));

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
    public String admin_detail_member(String id, String type, Model model) {

        if (type.equals("member")) {
            Member member = memberService.aGetDetailMember(id);
            String pn = member.getTel() + ") " + member.getPhoneNum();

            model.addAttribute("id", member.getMemberId());
            model.addAttribute("name", member.getName());
            model.addAttribute("password", member.getPassword());
            model.addAttribute("phone", pn);
            model.addAttribute("addr", member.getAddr());
        } else if (type.equals("company")) {
            Company member = companyMemberService.aGetDetailMember(id);
            String pn = member.getTel() + ") " + member.getPhoneNum();

            model.addAttribute("id", member.getCompanyId());
            model.addAttribute("name", member.getName());
            model.addAttribute("password", member.getPassword());
            model.addAttribute("phone", pn);
            model.addAttribute("addr", member.getAddr());
        }
        model.addAttribute("type", type);


        return "admin/admin-detail-member";
    }

    @RequestMapping(value = "/admin-update-game")
    public String admin_update_game() {
        return "admin/admin-update-game";
    }
}
