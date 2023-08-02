package com.uniqueGames.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Getter
@Setter
//@Entity
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
public class Member {

	private int id;
	private String memberId, password, name, email, tel, phoneNum, addr;
	private String email1, email2, email3, phone1, phone2, phone3, addr1, addr2, newpassword;
	private MultipartFile file;
	private String profileImg;
	private String newProfileImg;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String memberId;
//
//	@Column(nullable = false)
//	private String password;
//
//	@Column(nullable = false)
//	private String name;
//
//	@Column(nullable = false)
//	private String email;
//
//	@Column
//	private String tel;
//
//	@Column
//	private String phoneNum;
//
//	@Column
//	private String addr;

	public String getEmail() {

		if(email1!=null) {
			email = email1+"@"+email2;
		}
		return email;
	}

	public String getPhoneNum() {

		if(phone1!=null) {
			phoneNum = phone1+phone2+phone3;
		}
		return phoneNum;
	}

	public String getAddr() {
		if(addr1!=null) {
			addr = addr1 +"   "+ addr2;
		}
		return addr;
	}
}