package com.uniqueGames.fileutil;

import com.uniqueGames.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AdminUtil {

    MemberService memberService;
    CompanyMemberService companyMemberService;
    GameService gameService;
    OrderService orderService;
    CommentService commentService;

    @Value("${upload-directory}")
    private String root_path;

    @Autowired
    private AdminUtil(MemberService memberService, CompanyMemberService companyMemberService, GameService gameService, OrderService orderService, CommentService commentService) {
        this.memberService = memberService;
        this.companyMemberService = companyMemberService;
        this.gameService = gameService;
        this.orderService = orderService;
        this.commentService = commentService;
    }

    /**
     * pagin utils
     *
     * @param page
     * @return Map<String, Integer>
     */
    public Map<String, Integer> getPagination(String page, String keyword, String type) {
        Map<String, Integer> result = new HashMap();

        int startCount = 0;
        int endCount = 0;
        int pageSize = 8; // 한페이지당 게시물 수
        int reqPage = 1; // 요청페이지
        int maxSize = 1; // 전체 페이지 수
        int dbCount = 0; // DB에서 가져온 전체 행수

        if (keyword.equals("list") && type.equals("member")) {
            dbCount = memberService.totRowCount();
        } else if (keyword.equals("list") && type.equals("company")) {
            dbCount = companyMemberService.totRowCount();
        } else if (keyword.equals("list") && type.equals("game")) {
            dbCount = gameService.totRowCount();
        } else if (keyword.equals("list") && type.equals("comment")) {
            dbCount = commentService.totRowCount();
        } else if (type.equals("member_payment")) {
            dbCount = orderService.totRowCountMember(keyword);
        } else if (type.equals("company_payment")) {
            dbCount = orderService.totRowCountCompany(keyword);
        } else if (type.equals("admin_donation")) {
            pageSize = 8;
            dbCount = orderService.totRowCountAdmin();
        }


        // 총 페이지 수 계산
        if (dbCount % pageSize == 0) {
            maxSize = dbCount / pageSize;
        } else {
            maxSize = dbCount / pageSize + 1;
        }

        // 요청 페이지 계산
        if (page != null) {
            reqPage = Integer.parseInt(page);
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

    // 정렬 키워드 분리
    public String[] splitString(String array) {
        String[] result = array.split("_");
        return result;
    }
}
