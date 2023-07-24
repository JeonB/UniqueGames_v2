package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.*;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Order;

import com.uniqueGames.repository.CompanyRepositoryMapper;
import java.util.ArrayList;
import java.util.List;

import com.uniqueGames.service.GameService;
import com.uniqueGames.service.OrderService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
    private final OrderService orderService;
    private final GameService gameService;
    private final CompanyRepositoryMapper companyRepositoryMapper;

    @Autowired
    public CartController(OrderService orderService, GameService gameService,
            CompanyRepositoryMapper companyRepositoryMapper) {
        this.orderService = orderService;
        this.gameService = gameService;
        this.companyRepositoryMapper = companyRepositoryMapper;
    }


    /**
     * @param selectedValue Ajax로 전달 받은 후원 금액
     * @param gameId Ajax로 전달 받은 게임 식별자                     
     * @param member 회원 아이디
     * @param session 세션 속성값 제거를 위함
     * @return 장바구니 페이지
     */
    @PostMapping(value = "/cart")
    public String getValue(@RequestParam("selectedValue") String selectedValue,
     @RequestParam("gameId") int gameId, @Login Member member, HttpSession session) {
        Company company = companyRepositoryMapper.findByIndex(gameId);
        Order order = orderService.addToOrderVo(
                member.getMemberId(),
                company.getCompanyId(),
                gameId,
                Integer.parseInt(selectedValue)
        );
        orderService.insertCart(order);
        session.removeAttribute("gameId"); // "gameId" 세션 속성 값 제거
        return "order/cart";
    }

    @GetMapping(value = "/cart")
    public String cart(@Login Member member, Model model) {
        ArrayList<Order> cartList = orderService.getCartList(member.getMemberId());
        cartList = gameService.addGameInfo(cartList);

        if (cartList.size() == 0) {
            model.addAttribute("nothing", true);
        } else {
            model.addAttribute("nothing", false);
            model.addAttribute("cartList", cartList);
        }

        return "order/cart";
    }

    @RequestMapping(value = "/cartDeleteOne")
    public String cartDeleteOne(@Login Member member, int id, Model model) {
        if (orderService.getCartDeleteOne(id) != 0) {
            ArrayList<Order> cartList = orderService.getCartList(member.getMemberId());

            if (cartList.size() == 0) {
                model.addAttribute("nothing", true);
            } else {
                model.addAttribute("nothing", false);
                model.addAttribute("cartList", cartList);
            }

            return "order/cart";
        }
        return "order/error";

    }

    @RequestMapping(value = "/cartDeleteSelected")
    @ResponseBody
    public String cartDeleteSelected(@RequestParam(value = "checkedList[]") List<Integer> checkedList) {
        for (int chk : checkedList) {
            if (orderService.getCartDeleteOne(chk) == 0) {
                return "order/error";
            }
        }
        return "/cart";
    }

    @RequestMapping(value = "/cartDeleteAll")
    public String cartDeleteAll(@Login Member member, Model model) {
        if (orderService.getCartDeleteAll(member.getMemberId()) == 0) {
            return "order/error";
        }
        model.addAttribute("nothing", true);
        return "order/cart";
    }
}
