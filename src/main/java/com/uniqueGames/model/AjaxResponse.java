package com.uniqueGames.model;

import lombok.Data;

@Data
public class AjaxResponse {
    private String result;
    private String message;
    private Integer likeCount;

    public AjaxResponse(String result, String message, Integer likeCount) {
        this.result = result;
        this.message = message;
        this.likeCount = likeCount;
    }

}
