package com.uniqueGames.service;

import com.uniqueGames.model.Company;
import com.uniqueGames.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyMemberService")
public class CompanyMemberServiceImpl implements CompanyMemberService {

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public int companyLoginResult(Company company) {
		
		return companyRepository.login(company);
	}

	@Override
	public int companyJoinResult(Company company) {
		return companyRepository.insert(company);
	}

	@Override
	public int companyIdCheckResult(String companyId) {
		return companyRepository.idCheck(companyId);
	}

	@Override
	public String companyFindIdResult(Company company) {
		return companyRepository.findIdCheck(company);
	}

	@Override
	public int companyFindPwdResult(Company company) {
		return companyRepository.findPwdCheck(company);
	}

	@Override
	public int companyUpdateResult(Company company) {
		return companyRepository.update(company);
	}

	@Override
	public Company companyPageResult(String companyId) {
		return companyRepository.companyPage(companyId);
	}

	@Override
	public int companyChangeCPassword(String companyId, String cnewpassword) {
		Company company = new Company();
		company.setCompanyId(companyId);
		company.setCnewpassword(cnewpassword);
		return companyRepository.changeCpassword(company);
	}

	@Override
	public String companyGameName(String companyId) {
		return companyRepository.getGameNameByCID(companyId);
	}

	@Override
	public int companyDeleteResult(Company company) {
		return companyRepository.deleteCompany(company);
	}

	@Override
	public int companyEmailCheckResult(String email) {
		return companyRepository.emailCheck(email);
	}

	@Override
	public int companyPhoneCheckResult(String phoneNum) {
		return companyRepository.phoneCheck(phoneNum);
	}

}
