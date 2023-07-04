package com.uniqueGames.service;

import com.uniqueGames.model.Company;
import com.uniqueGames.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyMemberService")
public class CompanyMemberServiceImpl implements CompanyMemberService {

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public int companyLoginResult(Company company) {
		
		return companyDao.login(company);
	}

	@Override
	public int companyJoinResult(Company company) {
		return companyDao.insert(company);
	}

	@Override
	public int companyIdCheckResult(String company_id) {
		return companyDao.idCheck(company_id);
	}

	@Override
	public String companyFindIdResult(Company company) {
		return companyDao.findIdCheck(company);
	}

	@Override
	public int companyFindPwdResult(Company company) {
		return companyDao.findPwdCheck(company);
	}

	@Override
	public int companyUpdateResult(Company company) {
		return companyDao.update(company);
	}

	@Override
	public Company companyPageResult(String company_id) {
		return companyDao.companyPage(company_id);
	}

	@Override
	public int companyChangeCPassword(String company_id, String cnewpassword) {
		Company company = new Company();
		company.setCompany_id(company_id);
		company.setCnewpassword(cnewpassword);
		return companyDao.changeCpassword(company);
	}

	@Override
	public String companyGameName(String company_id) {
		return companyDao.getGameNameByCID(company_id);
	}

	@Override
	public int companyDeleteResult(Company company) {
		return companyDao.deleteCompany(company);
	}

	@Override
	public int companyEmailCheckResult(String email) {
		return companyDao.emailCheck(email);
	}

	@Override
	public int companyPhoneCheckResult(String phone_num) {
		return companyDao.phoneCheck(phone_num);
	}

}
