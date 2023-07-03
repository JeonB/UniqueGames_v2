package com.uniqueGames.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import com.uniqueGames.model.CompanyVo;
import com.uniqueGames.model.MemberVo;
import com.uniqueGames.model.OrderVo;
import com.uniqueGames.model.SessionConstants;
import com.uniqueGames.service.OrderServiceImpl;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(SessionConstants.LOGIN_MEMBER)
public class PaymentDetailController {
	@Autowired
    OrderServiceImpl orderService;

	/** payment_detail **/
	@RequestMapping(value = "/payment_detail", method = RequestMethod.GET)
	public String payment_detail() {
		return "payment-detail";
	}

	/** payment_detail_data **/
	@RequestMapping(value = "/payment_detail_data", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String payment_detail_data(@ModelAttribute(SessionConstants.LOGIN_MEMBER) MemberVo member, String array) {
		String m_id = member.getMember_id();
		ArrayList<OrderVo> list = orderService.getPaymentDetail(m_id, array);

		// list 객체의 데이터를 JSON 형태로 생성
		JsonObject jlist = new JsonObject();
		JsonArray jarray = new JsonArray();

		if (list.size() == 0) {
			jlist.addProperty("nothing", true);
		} else {
			jlist.addProperty("nothing", false);
		}
		jlist.addProperty("count", orderService.getPaymentCount(m_id));
		jlist.addProperty("totalAmount", orderService.getPaymentAmount(m_id));

		for (OrderVo payment : list) {
			JsonObject jobj = new JsonObject();

			jobj.addProperty("rno", payment.getRno());
			jobj.addProperty("orderdate", payment.getOrder_date());
			jobj.addProperty("gametitle", payment.getGametitle());
			jobj.addProperty("amount", payment.getAmount());

			jarray.add(jobj);
		}
		jlist.add("jlist", jarray);

		return new Gson().toJson(jlist);
	}

	/** donation_detail **/
	@RequestMapping(value = "/donation_detail", method = RequestMethod.GET)
	public String donation_detail() {
		return "donation-detail";
	}

	/** donation_detail_data **/
	@RequestMapping(value = "/donation_detail_data", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String donation_detail_data(@ModelAttribute(SessionConstants.LOGIN_MEMBER) CompanyVo company, String array) {
		String c_id = company.getCompany_id();
		ArrayList<OrderVo> list = orderService.getDonationDetail(c_id, array);


		// list 객체의 데이터를 JSON 형태로 생성
		JsonObject jlist = new JsonObject();
		JsonArray jarray = new JsonArray();

		if (list.size() == 0) {
			jlist.addProperty("nothing", true);
		} else {
			jlist.addProperty("nothing", false);
		}
		jlist.addProperty("expected", orderService.getExpected(c_id));
		jlist.addProperty("totalAmount", orderService.getTotalDonation(c_id));

		for (OrderVo payment : list) {
			JsonObject jobj = new JsonObject();

			jobj.addProperty("rno", payment.getRno());
			jobj.addProperty("orderdate", payment.getOrder_date());
			jobj.addProperty("gametitle", payment.getGametitle());
			jobj.addProperty("amount", payment.getAmount());

			jarray.add(jobj);
		}
		jlist.add("jlist", jarray);

		return new Gson().toJson(jlist);
	}

	/** donation_rank **/
	@RequestMapping(value = "/donation_rank", method = RequestMethod.GET)
	public String donation_rank() {
		return "donation-rank";
	}

	/** donation_rank_data **/
	@RequestMapping(value = "/donation_rank_data", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String donation_rank_data(@ModelAttribute(SessionConstants.LOGIN_MEMBER) CompanyVo company) {
		String c_id = company.getCompany_id();
		ArrayList<OrderVo> list = orderService.getDonationRank(c_id);

		// list 객체의 데이터를 JSON 형태로 생성
		JsonObject jlist = new JsonObject();
		JsonArray jarray = new JsonArray();

		if (list.size() == 0) {
			jlist.addProperty("nothing", true);
		} else {
			jlist.addProperty("nothing", false);
		}

		for (OrderVo payment : list) {
			JsonObject jobj = new JsonObject();

			jobj.addProperty("rno", payment.getRno());
			jobj.addProperty("userId", payment.getUserId());
			jobj.addProperty("gametitle", payment.getGametitle());
			jobj.addProperty("amount", payment.getAmount());

			jarray.add(jobj);
		}
		jlist.add("jlist", jarray);

		return new Gson().toJson(jlist);
	}
}
