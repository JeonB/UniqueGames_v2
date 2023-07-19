package com.uniqueGames.fileutil;

import com.uniqueGames.service.CompanyMemberService2;
import com.uniqueGames.service.GameService;
import com.uniqueGames.service.MemberService;
import com.uniqueGames.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentUtil {
    @Autowired
    OrderService orderService;

    @Autowired
    private PaymentUtil(OrderService orderService) {
        this.orderService = orderService;
    }

    public Map<String, Integer> getPagination(Map<String, String> param) {
        Map<String, Integer> result = new HashMap();

        int startCount = 0;
        int endCount = 0;
        int pageSize = 10; // 한페이지당 게시물 수
        int reqPage = 1; // 요청페이지
        int maxSize = 1; // 전체 페이지 수
        int dbCount = 0; // DB에서 가져온 전체 행수

        if (param.get("type").equals("member")) {
            dbCount = orderService.totRowCountMember(param.get("id"));
        } else if (param.get("type").equals("company")) {
            dbCount = orderService.totRowCountCompany(param.get("id"));
        } else if (param.get("type").equals("donation")) {
            pageSize = 8;
            dbCount = orderService.totRowCountAdmin();
        } else {

        }

        // total page count
        if (dbCount % pageSize == 0) {
            maxSize = dbCount / pageSize;
        } else {
            maxSize = dbCount / pageSize + 1;
        }

        // request page
        if (param.get("page") != null) {
            reqPage = Integer.parseInt(param.get("page"));
            startCount = (reqPage - 1) * pageSize + 1;
            endCount = reqPage * pageSize;
        } else {
            startCount = 1;
            endCount = pageSize;
        }

        result.put("startCount", startCount);
        result.put("endCount", endCount);
        result.put("pageSize", pageSize);
        result.put("reqPage", reqPage);
        result.put("maxSize", maxSize);
        result.put("dbCount", dbCount);

        return result;
    }
}
