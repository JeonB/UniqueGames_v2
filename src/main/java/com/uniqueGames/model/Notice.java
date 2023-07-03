package com.uniqueGames.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Setter
@Getter
public class Notice {
	int rno;
	int post_id;
	String company_id;
	String name;
	String title;
	String content;
	int notice_hits;
	Date notice_date;
	String date_output;
	CommonsMultipartFile file;
	String image_id;
	String upload_file;
	int cmtCount;

}
