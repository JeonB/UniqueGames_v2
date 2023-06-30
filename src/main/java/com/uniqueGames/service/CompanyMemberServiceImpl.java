package com.uniqueGames.service;

import com.uniqueGames.model.CompanyVo;
import com.uniqueGames.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("companyMemberService")
public class CompanyMemberServiceImpl implements CompanyMemberService {

	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public int companyLoginResult(CompanyVo companyVo) {
		
		return companyDao.login(companyVo);
	}

	@Override
	public int companyJoinResult(CompanyVo companyVo) {
		return companyDao.insert(companyVo);
	}

	@Override
	public int companyIdCheckResult(String company_id) {
		return companyDao.idCheck(company_id);
	}

	@Override
	public String companyFindIdResult(CompanyVo companyVo) {
		return companyDao.findIdCheck(companyVo);
	}

	@Override
	public int companyFindPwdResult(CompanyVo companyVo) {
		return companyDao.findPwdCheck(companyVo);
	}

	@Override
	public int companyUpdateResult(CompanyVo companyVo) {
		return companyDao.update(companyVo);
	}

	@Override
	public CompanyVo companyPageResult(String company_id) {
		return companyDao.companyPage(company_id);
	}

	@Override
	public int companyChangeCPassword(String company_id, String cnewpassword) {
		CompanyVo companyVo = new CompanyVo();
		companyVo.setCompany_id(company_id);
		companyVo.setCnewpassword(cnewpassword);
		return companyDao.changeCpassword(companyVo);
	}

	@Override
	public String companyGameName(String company_id) {
		return companyDao.getGameNameByCID(company_id);
	}

	@Override
	public int companyDeleteResult(CompanyVo companyVo) {
		return companyDao.deleteCompany(companyVo);
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
