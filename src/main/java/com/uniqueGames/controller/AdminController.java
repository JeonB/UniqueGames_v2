package com.uniqueGames.controller;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin/admin";
    }
}
