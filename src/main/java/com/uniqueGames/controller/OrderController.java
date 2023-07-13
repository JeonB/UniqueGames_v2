package com.uniqueGames.controller;

import com.uniqueGames.config.Login;
import com.uniqueGames.model.Member;
import com.uniqueGames.model.Order;

import java.util.ArrayList;
import java.util.List;

import com.uniqueGames.service.MemberService;
import com.uniqueGames.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    ArrayList<Integer> idList;
    String idStr;

    @RequestMapping(value = "/order")
    public String order(@Login Member member, Model model, String[] checkedList) {
        idList = new ArrayList<Integer>();
        for (String id : checkedList) {
            idList.add(Integer.parseInt(id));
        }

        idStr = orderService.listToString(idList);
        ArrayList<Order> orderList = orderService.getOrderList(idStr);
        int totalAmount = orderService.getOrderAmount(idStr);
        Member buyer = memberService.aGetDetailMember(member.getMemberId());

        model.addAttribute("orderList", orderList);
        model.addAttribute("count", "총 " + orderList.size() + "개");
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalAmountStr", orderService.formatCurrency(totalAmount));
        model.addAttribute("member", buyer);

        return "order/order";
    }

    @RequestMapping(value = "/orderDeleteOne")
    public String orderDeleteOne(Model model, int id, @Login Member member) {
        idList.remove(Integer.valueOf(id));
        if (idList.size() == 0) {
            return "order/error";
        }

        idStr = orderService.listToString(idList);
        ArrayList<Order> orderList = orderService.getOrderList(idStr);
        int totalAmount = orderService.getOrderAmount(idStr);
        Member buyer = memberService.aGetDetailMember(member.getMemberId());

        model.addAttribute("orderList", orderList);
        model.addAttribute("count", "총 " + orderList.size() + "개");
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("totalAmountStr", orderService.formatCurrency(totalAmount));
        model.addAttribute("member", buyer);

        return "order/order";
    }

    @RequestMapping(value = "/order-pay")
    @ResponseBody
    public String order_pay(String method) {
        if (orderService.getOrderComplete(idStr, method) == 0) {
            return "order/error";
        }
        return "order/order";
    }

    @RequestMapping(value = "/order-complete")
    public String order_complete() {
        return "order/order-complete";
    }
}
