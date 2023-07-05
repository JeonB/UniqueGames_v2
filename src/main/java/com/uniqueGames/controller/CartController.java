package com.uniqueGames.controller;



import com.uniqueGames.model.*;
import com.uniqueGames.model.Company;
import com.uniqueGames.model.Game;
import com.uniqueGames.model.Order;
import com.uniqueGames.model.SessionConstants;
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
    Company company, @ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member, @ModelAttribute("game") Game game){

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
	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public ModelAndView cart(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member) {
		ModelAndView model = new ModelAndView();
		ArrayList<Order> cartList = orderService.getCartList(member.getMemberId());
		System.out.println(member.getMemberId());

		if (cartList.size() > 0) {
			model.addObject("cartList", cartList);
			model.addObject("nothingInCart", false);
		} else {
			model.addObject("nothingInCart", true);
		}

		model.setViewName("order/cart");

		return model;
	}

	@RequestMapping(value = "/cartDeleteOne", method = RequestMethod.GET)
	public String cartDeleteOne(int id) {
		String view;
		int result = orderService.getCartDeleteOne(id);

		if (result != 0) {
			view = "redirect://cart";
		} else {
			view = "order/error";
		}

		return view;
	}

	@RequestMapping(value = "/cartDeleteSelected")
	public String cartDeleteSelected(@RequestParam(value = "checkedList[]") List<Integer> checkedList) {
		int result = 0;

		for (int i = 0; i < checkedList.size(); i++) {
			result = orderService.getCartDeleteOne((int) checkedList.get(i));
			if (result == 0) {
				return "order/error";
			}
		}
		return "redirect://cart";
	}

	@RequestMapping(value = "/cartDeleteAll", method = RequestMethod.GET)
	public String cartDeleteSelected(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member) {
		String mId = member.getMemberId();
		int result = orderService.getCartDeleteAll(mId);


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
