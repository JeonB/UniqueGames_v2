package com.uniqueGames.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Setter
@Getter
public class Notice {
	int rno;
	int postId;
	String companyId;
	String name;
	String title;
	String content;
	int noticeHits;
	Date noticeDate;
	String dateOutput;
	CommonsMultipartFile file;
	String imageId;
	String uploadFile;
	int cmtCount;

}
