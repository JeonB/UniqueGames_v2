package com.uniqueGames.controller;

import com.uniqueGames.model.*;
import com.uniqueGames.repository.CompanyRepositoryMapper;
import com.uniqueGames.service.GameService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author jeonb 
 * 회사 소개 페이지 Mybatis 컨트롤러
 * CRUD 메소드 구현
 */
@Controller
@RequestMapping(value = "/detail")
public class DetailMapperController {

    private final GameService gameService;

    @Autowired
    public DetailMapperController(GameService gameService) {
        this.gameService = gameService;
    }

    /** goDetail()
     * @return 회사 상세페이지 리턴
     */
    @GetMapping("/{detailId}")
    public String goDetail(@PathVariable("detailId") int detailId, Model model,HttpSession session) {
        Game game = gameService.getGameForIndex(detailId);
        model.addAttribute("game", game);
        session.setAttribute("gameId",detailId);
        // 요청된 detailId에 따라 해당 페이지로 이동
        switch (detailId) {
            case 1:
                return "detail/detail";
            case 2:
                return "detail/detail2";
            case 3:
                return "detail/detail3";
            case 4:
                return "detail/detail4";
            default:
                return "redirect:/";
        }
    }
    /**
     * @return 후원 팝업창 리턴
     */
    @GetMapping("/popUp")
    public String getPopUp(){
        return "detail/pop-up";
    }
}
