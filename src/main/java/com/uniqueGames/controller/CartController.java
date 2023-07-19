package com.uniqueGames.controller;


import com.uniqueGames.config.Login;
import com.uniqueGames.model.*;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Order;

import java.util.ArrayList;
import java.util.List;

import com.uniqueGames.service.GameService;
import com.uniqueGames.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({SessionConstants.LOGIN_MEMBER, "game", "companyVo"})
public class CartController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GameService gameService;

    @PostMapping(value = "/cart")
    public String getValue(@RequestParam("selectedValue") String selectedValue, @ModelAttribute("companyVo")
    Company company, @ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member, @ModelAttribute("game") Game game) {

        /*
         * orderService의 데이터 insert 기능 추가
         * */
        Order order = orderService.addToOrderVo(

                member.getMemberId(),
                company.getCompanyId(),

                game.getId(),
                Integer.parseInt(selectedValue),
                game.getName(),
                game.getImagePath()
        );
        orderService.insertCart(order);
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
