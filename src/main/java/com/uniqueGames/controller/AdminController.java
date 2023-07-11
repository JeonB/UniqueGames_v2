package com.uniqueGames.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin/admin";
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
