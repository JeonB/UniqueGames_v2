package com.uniqueGames.controller;


import com.uniqueGames.model.*;
import com.uniqueGames.model.Company;
import com.uniqueGames.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({SessionConstants.LOGIN_MEMBER,"game","companyVo"})
public class CartController {
	@Autowired
    OrderServiceImpl orderService;

	@RequestMapping(value = "/cart", method = RequestMethod.POST)
	public String getValue(@RequestParam("selectedValue") String selectedValue, @ModelAttribute("companyVo")
    Company company, @ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member, @ModelAttribute("game") GameVo gameVo){

		/*
		* orderService의 데이터 insert 기능 추가
		* */
		OrderVo orderVo = orderService.addToOrderVo(
				member.getMemberId(),
				company.getCompany_id(),
				gameVo.getId(),
				Integer.parseInt(selectedValue),
				gameVo.getName(),
				gameVo.getImage_path()
				);
		orderService.insertCart(orderVo);
		return "order/cart";
	}
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cart(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member) {
		System.out.println(member.getMemberId());

		ModelAndView model = new ModelAndView();
		ArrayList<OrderVo> cartList = orderService.getCartList(member.getMemberId());

		if (cartList.size() > 0) {
			model.addObject("cartList", cartList);
			model.addObject("nothingInCart", false);
		} else {
			model.addObject("nothingInCart", true);
		}

		model.setViewName("order/cart");

		return model;
	}

	@RequestMapping(value = "/cart_delete_one", method = RequestMethod.GET)
	public String cart_delete_one(int id) {
		String view;
		int result = orderService.getCartDeleteOne(id);

		if (result != 0) {
			view = "redirect://cart";
		} else {
			view = "order/error";
		}

		return view;
	}

	@RequestMapping(value = "/cart_delete_selected")
	public String cart_delete_selected(@RequestParam(value = "checkedList[]") List<Integer> checkedList) {
		int result = 0;

		for (int i = 0; i < checkedList.size(); i++) {
			result = orderService.getCartDeleteOne((int) checkedList.get(i));
			if (result == 0) {
				return "order/error";
			}
		}
		return "redirect://cart";
	}

	@RequestMapping(value = "/cart_delete_all", method = RequestMethod.GET)
	public String cart_delete_selected(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member) {
		String m_id = member.getMemberId();
		int result = orderService.getCartDeleteAll(m_id);

		if (result == 0) {
			return "order/error";
		}

		return "redirect://cart";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "order/error";
	}

}
