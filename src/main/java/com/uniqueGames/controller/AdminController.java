package com.uniqueGames.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.uniqueGames.fileutil.AdminUtil;
import com.uniqueGames.model.*;
import com.uniqueGames.service.*;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private CompanyMemberService2 companyMemberService;
    @Autowired
    private GameService gameService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AdminUtil adminUtil;

    // ADMIN - INDEX
    @RequestMapping(value = "/admin")
    public String admin() {
        return "/admin/admin";
    }

    // ADMIN - MEMBER LIST : MEMBER / COMPANY
    @RequestMapping(value = "/admin-member-list", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_member_list(String type, String array, String page) {
        JsonObject jObj = new JsonObject();
        JsonArray jArray = new JsonArray();
        String[] arr = adminUtil.splitString(array);

        Map<String, Integer> pageMap = adminUtil.getPagination(page, "list", type);
        if (type.equals("member") || !type.equals("company") || type == null) {
            if (arr[0].equals("id")) {
                arr[0] = "MEMBER_ID";
            }
            ArrayList<Member> list = memberService.aGetMemberList(arr[0], arr[1], pageMap.get("startCount"), pageMap.get("endCount"));

            if (list.size() == 0) {
                jObj.addProperty("nothing", true);
            } else {
                jObj.addProperty("nothing", false);
                jObj.addProperty("nameField", "이름");
                jObj.addProperty("str", "전체 개인 회원 : " + pageMap.get("dbCount") + "명");

                for (Member member : list) {
                    JsonObject obj = new JsonObject();

                    obj.addProperty("id", member.getMemberId());
                    obj.addProperty("name", member.getName());

                    jArray.add(obj);
                }
            }
        } else if (type.equals("company")) {
            if (arr[0].equals("id")) {
                arr[0] = "COMPANY_ID";
            }
            ArrayList<Company> list = companyMemberService.aGetMemberList(arr[0], arr[1], (int) pageMap.get("startCount"), (int) pageMap.get("endCount"));

            if (list.size() == 0) {
                jObj.addProperty("nothing", true);
            } else {
                jObj.addProperty("nothing", false);
                jObj.addProperty("nameField", "회사명");
                jObj.addProperty("str", "전체 법인 회원 : " + pageMap.get("dbCount") + "명");

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

    @RequestMapping(value = "/admin-delete-member", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_delete_member(String mid, String type) {
        if (type.equals("member")) {
            if (memberService.aDeleteMember(mid) == 0) {
                return "failed";
            }
        } else {
            if (companyMemberService.aDeleteMember(mid) == 0) {
                return "failed";
            }
        }
        return "complete";
    }

    @RequestMapping(value = "/admin-delete-members", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_delete_members(@RequestParam(value = "midList[]") java.util.List<String> midList, String type) {
        if (type.equals("member")) {
            for (String mid : midList) {
                if (memberService.aDeleteMember(mid) == 0) {
                    return "failed";
                }
            }
        } else {
            for (String mid : midList) {
                if (companyMemberService.aDeleteMember(mid) == 0) {
                    return "failed";
                }
            }
        }
        return "complete";
    }


    @RequestMapping(value = "/admin-game-list")
    public String admin_game_list() {
        return "admin/admin-game-list";
    }

    // ADMIN - GAME LIST
    @RequestMapping(value = "/admin-game-list-data", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_game_list_data(String type, String array, String page) {
        JsonObject jObj = new JsonObject();
        JsonArray jArray = new JsonArray();
        String[] arr = adminUtil.splitString(array);

        Map<String, Integer> pageMap = adminUtil.getPagination(page, "list", type);
        ArrayList<Game> list = gameService.aGetGameList(arr[0], arr[1], pageMap.get("startCount"), pageMap.get("endCount"));

        if (list.size() == 0) {
            jObj.addProperty("nothing", true);
        } else {
            jObj.addProperty("nothing", false);
            jObj.addProperty("gameCount", list.size());

            for (Game game : list) {
                JsonObject obj = new JsonObject();
                Company company = companyMemberService.aGetCompany(game.getId());

                obj.addProperty("id", game.getId());
                obj.addProperty("title", game.getName());
                obj.addProperty("company", company.getName());
                obj.addProperty("cId", company.getCompanyId());

                jArray.add(obj);
            }
        }

        jObj.add("gameList", jArray);
        jObj.addProperty("dbCount", pageMap.get("dbCount"));
        jObj.addProperty("pageSize", pageMap.get("pageSize"));
        jObj.addProperty("maxSize", pageMap.get("maxSize"));
        jObj.addProperty("page", pageMap.get("reqPage"));

        return new Gson().toJson(jObj);
    }

    // ADMIN - GAME REGISTER
    @RequestMapping(value = "/admin-game-register")
    public String admin_game_register(Model model) {
        ArrayList<Company> cList = companyMemberService.aGetAllCompanyList();

        model.addAttribute("companyList", cList);
        model.addAttribute("result", "none");
        model.addAttribute("modalDisplay", "none");
        return "admin/admin-game-register";
    }

    @RequestMapping(value = "/admin-game-register-form")
    public String admin_game_register_form(Model model, String name, String cId, String genre, String imagePath, String description) {
        if (companyMemberService.aGetGameRegistered(cId) == 0) {
            // register
            if (gameService.aRegisterGame(name, genre, imagePath, description) != 0) {
                if (companyMemberService.aSetGid(gameService.aGetGid(name), cId) != 0) {
                    model.addAttribute("result", "register_complete");
                } else {
                    gameService.aDeleteGame(gameService.aGetGid(name));
                    model.addAttribute("result", "register_gid_error");
                }
            } else {
                model.addAttribute("result", "register_failed");
            }
        } else {
            model.addAttribute("result", "registered");
        }
        model.addAttribute("modalDisplay", "none");
        return "admin/admin-game-register";
    }

    @RequestMapping(value = "/admin-company-selector", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_company_selector(String keyword, Model model) {
        JsonObject jObj = new JsonObject();
        JsonArray jArray = new JsonArray();

        ArrayList<Company> list = companyMemberService.aGetSearched(keyword.toUpperCase());

        if (list.size() == 0) {
            jObj.addProperty("nothing", true);
        } else {
            jObj.addProperty("nothing", false);

            for (Company company : list) {
                JsonObject obj = new JsonObject();
                obj.addProperty("id", company.getCompanyId());
                obj.addProperty("name", company.getName());

                jArray.add(obj);
            }
        }

        jObj.add("companyList", jArray);

        return new Gson().toJson(jObj);
    }

    // ADMIN - DONATION
    @RequestMapping(value = "/admin-donation")
    public String admim_donation(Model model) {
        ArrayList<String> yearList = orderService.aGetYearList();
        yearList.add(0, "All");

        model.addAttribute("yearList", yearList);

        return "admin/admin-donation";
    }

    @RequestMapping(value = "/admin-donation-data", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String admin_donation_data(String array, String page) {
        JsonObject jObj = new JsonObject();
        JsonArray jArray = new JsonArray();
        String[] arr = adminUtil.splitString(array);

        Map<String, String> param = new HashMap<>();
        param.put("page", page);
        param.put("type", "donation");
        Map<String, Integer> pageMap = adminUtil.getPagination(page, "list", "admin_donation");

        if (arr[0].equals("name")) {
            arr[0] = "COMPANY";
        }
        ArrayList<Payment> list = orderService.aGetDonationList(arr[0], arr[1], pageMap.get("startCount"), pageMap.get("endCount"));

        if (list.size() == 0) {
            jObj.addProperty("nothing", true);
        } else {
            jObj.addProperty("nothing", false);

            for (Payment payment : list) {
                JsonObject obj = new JsonObject();

                obj.addProperty("rno", payment.getRno());
                obj.addProperty("game", payment.getGame());
                obj.addProperty("company", payment.getCompany());
                obj.addProperty("amount", payment.getAmountStr());

                jArray.add(obj);
            }
        }

        jObj.add("donationList", jArray);
        jObj.addProperty("dbCount", pageMap.get("dbCount"));
        jObj.addProperty("pageSize", pageMap.get("pageSize"));
        jObj.addProperty("maxSize", pageMap.get("maxSize"));
        jObj.addProperty("page", pageMap.get("reqPage"));

        return new Gson().toJson(jObj);
    }

    // ADMIN - DETAIL : MEMBER / COMPANY
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

    // ADMIN - GAME UPDATE
    @RequestMapping(value = "/admin-update-game")
    public String admin_update_game(int id, Model model) {
        Game game = gameService.aGetGame(id);
        Company company = companyMemberService.aGetCompany(id);
        ArrayList<Company> cList = companyMemberService.aGetAllCompanyList();
        String url = "/detail/" + id;

        model.addAttribute("id", game.getId());
        model.addAttribute("title", game.getName());
        model.addAttribute("company", company.getName());
        model.addAttribute("cid", company.getCompanyId());
        model.addAttribute("genre", game.getGameGenre());
//        model.addAttribute("img", game.getImagePath());
        model.addAttribute("desciption", game.getDescription());
        model.addAttribute("url", url);
        model.addAttribute("companyList", cList);
        model.addAttribute("result", "none");

        return "admin/admin-update-game";
    }

    @RequestMapping(value = "/admin-game-update-form")
    public String admin_game_update_form(Model model, String name, String newname, String oldcid, String cId, String genre, String imagePath, String description) {
        int gid = gameService.aGetGid(name);

        if (!oldcid.equals(cId)) {
            if (companyMemberService.aDeleteGid(oldcid) == 0) {
                model.addAttribute("result", "update_gid_error");
                return "admin/admin-update-game";
            }
        }
        if (companyMemberService.aSetGid(gid, cId) != 0) {
            if (gameService.aUpdateGame(newname, genre, imagePath, description, gid) != 0) {
                model.addAttribute("result", "update_complete");
            } else {
                model.addAttribute("result", "update_failed");
            }
        } else {
            model.addAttribute("result", "update_failed");
        }
        model.addAttribute("modalDisplay", "none");
        return "admin/admin-update-game";
    }
}
