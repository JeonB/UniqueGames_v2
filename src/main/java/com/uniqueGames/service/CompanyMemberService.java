package com.uniqueGames.service;


import com.uniqueGames.model.Company;

public interface CompanyMemberService {
	
	int companyLoginResult(Company company);
	int companyJoinResult(Company company);
	int companyIdCheckResult(String company_id);
	String companyFindIdResult(Company company); //select
	int companyFindPwdResult(Company company);
	int companyUpdateResult(Company company);
	Company companyPageResult(String company_id);
	int companyChangeCPassword(String company_id, String cnewpassword);
	String companyGameName(String company_id);
	int companyDeleteResult(Company company);
	int companyEmailCheckResult(String email);
	int companyPhoneCheckResult(String phone_num);

}
