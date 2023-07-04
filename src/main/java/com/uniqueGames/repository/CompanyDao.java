package com.uniqueGames.repository;

import com.uniqueGames.model.Company;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {

	private SqlSessionTemplate sqlSession;
	
	/**company login*/
	public int login(Company company) {
		return sqlSession.selectOne("mapper.companyMember.login", company);
	}
	
	/**sign up member*/
	public int insert(Company company) {
		return sqlSession.insert("mapper.companyMember.insert", company);
	}
	/**id checking*/
	public int idCheck(String company_id) {
		return sqlSession.selectOne("mapper.companyMember.idCheck", company_id);
	}
	
	/**find-id-check*/
	public String findIdCheck(Company company) {
		return sqlSession.selectOne("mapper.companyMember.findId", company);
	}
	
	public int findPwdCheck(Company company) {
		return sqlSession.selectOne("mapper.companyMember.findPwd", company);
	}
	
	public int update(Company company) {
		return sqlSession.update("mapper.companyMember.update", company);
	}
	
	public Company companyPage(String company_id) {
		return sqlSession.selectOne("mapper.companyMember.companyPage", company_id);
	}
	
	public int changeCpassword(Company company) {
		return sqlSession.update("mapper.companyMember.changeCpassword", company);
	}
	
	public String getGameNameByCID(String company_id) {
		return sqlSession.selectOne("mapper.companyMember.getGameName", company_id);
	}
	
	public int deleteCompany(Company company) {
		return sqlSession.delete("mapper.companyMember.deleteCompany", company);
	}
	
	public int emailCheck(String email) {
		return sqlSession.selectOne("mapper.companyMember.emailCheck", email);
	}
	
	public int phoneCheck(String phone_num) {
		return sqlSession.selectOne("mapper.companyMember.phoneCheck", phone_num);
	}
	
}
