package com.uniqueGames.service;


import com.uniqueGames.model.Company;

import java.util.ArrayList;

public interface CompanyMemberService {
	
	int companyLoginResult(Company company);
	int companyJoinResult(Company company);
	int companyIdCheckResult(String companyId);
	String companyFindIdResult(Company company); //select
	int companyFindPwdResult(Company company);
	int companyUpdateResult(Company company);
	Company companyPageResult(String companyId);
	int companyChangeCPassword(String companyId, String newpassword);
	String companyGameName(String companyId);
	int companyDeleteResult(Company company);
	int companyEmailCheckResult(String email);
	int companyPhoneCheckResult(String phoneNum);

    ArrayList<Company> aGetMemberList();

    Company aGetDetailMember(String id);
}
