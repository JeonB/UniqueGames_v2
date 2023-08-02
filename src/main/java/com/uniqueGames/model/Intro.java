package com.uniqueGames.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
@Entity(name = "tb_intro")
public class Intro {

    /**
     * 회사(팀) 소개글 저장 객체
     * id 식별자
     * name 회사(팀) 이름
     * title 회사(팀) 소개글 제목
     * content 회사(팀) 소개글 내용
     * uploadFile 대표 이미지 파일
     * upload 이미지 파일 경로
     * cId 회사 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 200)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String content;
    @Transient
    private MultipartFile uploadFile;
    private String uploadImg;
    private String cId;


}