package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
	
	private int gId;
	private String companyId, password, name, email, tel, phoneNum, addr;
	private String email1, email2, email3, phone1, phone2, phone3, addr1, addr2, newpassword, game;


	public String getEmail() {
		if(email1!=null) {
			email = email1+"@"+email2;
		}
		return email;
	}

	public String getPhoneNum() {
		if(phone1!=null) {
			phoneNum=phone1+phone2+phone3;
		}
		return phoneNum;
	}

	public String getAddr() {
		if(addr1!=null) {
			addr = addr1+"   "+addr2;
		}
		return addr;
	}

}
