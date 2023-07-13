//package com.uniqueGames.controller;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//
//import com.uniqueGames.model.Company;
//import com.uniqueGames.model.Member;
//import com.uniqueGames.model.Order;
//import com.uniqueGames.model.SessionConstants;
//
//import java.util.ArrayList;
//
//import com.uniqueGames.service.OrderService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//@Controller
//@SessionAttributes(SessionConstants.LOGIN_MEMBER)
//public class PaymentDetailController {
//	@Autowired
//	OrderService orderService;
//
//	/** paymentDetail **/
//	@RequestMapping(value = "/paymentDetail", method = RequestMethod.GET)
//	public String paymentDetail() {
//		return "payment-detail";
//	}
//
//	/** paymentDetailData **/
//	@RequestMapping(value = "/paymentDetailData", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
//	@ResponseBody
//	public String paymentDetailData(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Member member, String array) {
//		String mId = member.getMemberId();
//		ArrayList<Order> list = orderService.getPaymentDetail(mId, array);
//
//		// list 객체의 데이터를 JSON 형태로 생성
//		JsonObject jlist = new JsonObject();
//		JsonArray jarray = new JsonArray();
//
//		if (list.size() == 0) {
//			jlist.addProperty("nothing", true);
//		} else {
//			jlist.addProperty("nothing", false);
//		}
//		jlist.addProperty("count", orderService.getPaymentCount(mId));
//		jlist.addProperty("totalAmount", orderService.getPaymentAmount(mId));
//
//		for (Order payment : list) {
//			JsonObject jobj = new JsonObject();
//
//			jobj.addProperty("rno", payment.getRno());
//			jobj.addProperty("orderdate", payment.getOrderDate());
//			jobj.addProperty("gametitle", payment.getGametitle());
//			jobj.addProperty("amount", payment.getAmount());
//
//			jarray.add(jobj);
//		}
//		jlist.add("jlist", jarray);
//
//		return new Gson().toJson(jlist);
//	}
//
//	/** donationDetail **/
//	@RequestMapping(value = "/donationDetail", method = RequestMethod.GET)
//	public String donationDetail() {
//		return "donation-detail";
//	}
//
//	/** donationDetailData **/
//	@RequestMapping(value = "/donationDetailData", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
//	@ResponseBody
//	public String donationDetailData(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Company company, String array) {
//		String cId = company.getCompanyId();
//		ArrayList<Order> list = orderService.getDonationDetail(cId, array);
//
//
//		// list 객체의 데이터를 JSON 형태로 생성
//		JsonObject jlist = new JsonObject();
//		JsonArray jarray = new JsonArray();
//
//		if (list.size() == 0) {
//			jlist.addProperty("nothing", true);
//		} else {
//			jlist.addProperty("nothing", false);
//		}
//		jlist.addProperty("expected", orderService.getExpected(cId));
//		jlist.addProperty("totalAmount", orderService.getTotalDonation(cId));
//
//		for (Order payment : list) {
//			JsonObject jobj = new JsonObject();
//
//			jobj.addProperty("rno", payment.getRno());
//			jobj.addProperty("orderdate", payment.getOrderDate());
//			jobj.addProperty("gametitle", payment.getGametitle());
//			jobj.addProperty("amount", payment.getAmount());
//
//			jarray.add(jobj);
//		}
//		jlist.add("jlist", jarray);
//
//		return new Gson().toJson(jlist);
//	}
//
//	/** donationRank **/
//	@RequestMapping(value = "/donationRank", method = RequestMethod.GET)
//	public String donationRank() {
//		return "donation-rank";
//	}
//
//	/** donationRankData **/
//	@RequestMapping(value = "/donationRankData", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
//	@ResponseBody
//	public String donationRankData(@ModelAttribute(SessionConstants.LOGIN_MEMBER) Company company) {
//		String cId = company.getCompanyId();
//		ArrayList<Order> list = orderService.getDonationRank(cId);
//
//		// list 객체의 데이터를 JSON 형태로 생성
//		JsonObject jlist = new JsonObject();
//		JsonArray jarray = new JsonArray();
//
//		if (list.size() == 0) {
//			jlist.addProperty("nothing", true);
//		} else {
//			jlist.addProperty("nothing", false);
//		}
//
//		for (Order payment : list) {
//			JsonObject jobj = new JsonObject();
//
//			jobj.addProperty("rno", payment.getRno());
//			jobj.addProperty("userId", payment.getUserId());
//			jobj.addProperty("gametitle", payment.getGametitle());
//			jobj.addProperty("amount", payment.getAmount());
//
//			jarray.add(jobj);
//		}
//		jlist.add("jlist", jarray);
//
//		return new Gson().toJson(jlist);
//	}
//}
