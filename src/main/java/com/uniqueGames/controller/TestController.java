package com.uniqueGames.controller;

import com.uniqueGames.model.IntroVo;
import com.uniqueGames.repository.DetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    DetailMapper detailMapper;
    @GetMapping("/test")
    String dbConVerify(){
        IntroVo vo = detailMapper.getIntro(2);
        return "연결 확인!";
    }
}
