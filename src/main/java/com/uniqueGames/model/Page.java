package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Page {
    private String page;
    private String keyword;
    private int startCount;
    private int endCount;
    private int pageSize; // 한페이지당 게시물 수
    private int reqPage; // 요청페이지
    private int pageCount; // 전체 페이지 수
    private int dbCount; // DB 에서 가져온 전체 행수

    public Page(String page, String keyword) {
        this.page = page;
        this.keyword = keyword;

    }

}
