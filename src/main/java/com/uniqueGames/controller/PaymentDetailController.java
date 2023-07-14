package com.uniqueGames.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


import com.uniqueGames.config.Login;
import com.uniqueGames.fileutil.AdminUtil;
import com.uniqueGames.fileutil.PaymentUtil;
import com.uniqueGames.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.uniqueGames.service.OrderService;
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
    OrderService orderService;
    @Autowired
    PaymentUtil paymentUtil;

    @RequestMapping(value = "/payment-detail")
    public String payment_detail() {
        return "order/payment-detail";
    }

    @RequestMapping(value = "/payment-detail-data", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String paymentDetailData(@Login Member member, String array, String page, String type) {
        String mId = member.getMemberId();
        String[] arr = orderService.splitString(array);

        Map<String, String> param = new HashMap<>();
        param.put("page", page);
        param.put("id", mId);
        param.put("type", type);
        Map<String, Integer> pageMap = paymentUtil.getPagination(param);

        JsonObject jObj = new JsonObject();
        JsonArray jarray = new JsonArray();

        if (arr[0].equals("orderdate")) {
            arr[0] = "ORDER_DATE";
        }
        ArrayList<Order> list = orderService.getPaymentDetail(mId, arr[0], arr[1].toUpperCase(), pageMap.get("startCount"), pageMap.get("endCount"));


        if (list.size() == 0) {
            jObj.addProperty("nothing", true);
        } else {
            jObj.addProperty("nothing", false);
        }
        jObj.addProperty("totalCount", "총 " + orderService.getPaymentCount(mId) + "개");
        jObj.addProperty("totalAmount", orderService.formatCurrency(orderService.getPaymentAmount(mId)) + "원");

        for (Order payment : list) {
            JsonObject jpay = new JsonObject();

            jpay.addProperty("rno", payment.getRno());
            jpay.addProperty("date", payment.getOrderDate());
            jpay.addProperty("title", payment.getGametitle());
            jpay.addProperty("amount", payment.getAmountStr());

            jarray.add(jpay);
        }
        jObj.add("paymentList", jarray);
        jObj.addProperty("dbCount", pageMap.get("dbCount"));
        jObj.addProperty("pageSize", pageMap.get("pageSize"));
        jObj.addProperty("maxSize", pageMap.get("maxSize"));
        jObj.addProperty("page", pageMap.get("reqPage"));

        return new Gson().toJson(jObj);
    }

    @RequestMapping(value = "/donation-detail", method = RequestMethod.GET)
    public String donationDetail() {
        return "order/donation-detail";
    }

    @RequestMapping(value = "/donation-detail-data", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String donationDetailData(@Login Company company, String array, String page, String type) {
        String cId = company.getCompanyId();
        String[] arr = orderService.splitString(array);

        Map<String, String> param = new HashMap<>();
        param.put("page", page);
        param.put("id", cId);
        param.put("type", type);
        Map<String, Integer> pageMap = paymentUtil.getPagination(param);

        JsonObject jObj = new JsonObject();
        JsonArray jarray = new JsonArray();

        if (arr[0].equals("orderdate")) {
            arr[0] = "ORDER_DATE";
        }
        ArrayList<Order> list = orderService.getDonationDetail(cId, arr[0], arr[1].toUpperCase(), pageMap.get("startCount"), pageMap.get("endCount"));


        if (list.size() == 0) {
            jObj.addProperty("nothing", true);
        } else {
            jObj.addProperty("nothing", false);
        }

        for (Order donation : list) {
            JsonObject jpay = new JsonObject();

            jpay.addProperty("rno", donation.getRno());
            jpay.addProperty("date", donation.getOrderDate());
            jpay.addProperty("title", donation.getGametitle());
            jpay.addProperty("amount", donation.getAmountStr());

            jarray.add(jpay);
        }
        jObj.add("donationList", jarray);
        jObj.addProperty("dbCount", pageMap.get("dbCount"));
        jObj.addProperty("pageSize", pageMap.get("pageSize"));
        jObj.addProperty("maxSize", pageMap.get("maxSize"));
        jObj.addProperty("page", pageMap.get("reqPage"));

        return new Gson().toJson(jObj);
    }
}
