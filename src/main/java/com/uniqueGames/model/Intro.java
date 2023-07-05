package com.uniqueGames.model;

import org.springframework.web.multipart.MultipartFile;


//VO(Value Object)
public class Intro {

    /**
     * 회사(팀) 소개글 저장 객체
     * id 식별자
     * name 회사(팀) 이름
     * title 회사(팀) 소개글 제목
     * content 회사(팀) 소개글 내용
     * uploadFile 대표 이미지 파일
     * upload 이미지 파일 경로
     * c_id 회사 아이디
     */
    private int id;
    private String name;
    private String title;
    private String content;
    private MultipartFile uploadFile;
    private String upload;
    private String c_id;

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }


    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(MultipartFile uploadFile) {
        this.uploadFile = uploadFile;
    }
}