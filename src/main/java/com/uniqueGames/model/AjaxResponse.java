package com.uniqueGames.model;

import lombok.Data;

@Data
public class AjaxResponse {
    private String result2;
    private String message2;
    private Integer likecount;

    public AjaxResponse(String result2, String message2, Integer likecount) {
        this.result2 = result2;
        this.message2 = message2;
        this.likecount = likecount;
    }
}
