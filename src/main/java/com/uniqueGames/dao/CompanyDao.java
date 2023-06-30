//package com.team2.uniquegames_v2.dao;
//
//import com.team2.uniquegames_v2.vo.CompanyVo;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class CompanyDao {
//
//	@Autowired
//	private SqlSessionTemplate sqlSession;
//
//	/**company login*/
//	public int login(CompanyVo companyVo) {
//		return sqlSession.selectOne("mapper.companyMember.login", companyVo);
//	}
//
//	/**sign up member*/
//	public int insert(CompanyVo companyVo) {
//		return sqlSession.insert("mapper.companyMember.insert", companyVo);
//	}
//	/**id checking*/
//	public int idCheck(String company_id) {
//		return sqlSession.selectOne("mapper.companyMember.idCheck", company_id);
//	}
//
//	/**find-id-check*/
//	public String findIdCheck(CompanyVo companyVo) {
//		return sqlSession.selectOne("mapper.companyMember.findId", companyVo);
//	}
//
//	public int findPwdCheck(CompanyVo companyVo) {
//		return sqlSession.selectOne("mapper.companyMember.findPwd", companyVo);
//	}
//
//	public int update(CompanyVo companyVo) {
//		return sqlSession.update("mapper.companyMember.update", companyVo);
//	}
//
//	public CompanyVo companyPage(String company_id) {
//		return sqlSession.selectOne("mapper.companyMember.companyPage", company_id);
//	}
//
//	public int changeCpassword(CompanyVo companyVo) {
//		return sqlSession.update("mapper.companyMember.changeCpassword", companyVo);
//	}
//
//	public String getGameNameByCID(String company_id) {
//		return sqlSession.selectOne("mapper.companyMember.getGameName", company_id);
//	}
//
//	public int deleteCompany(CompanyVo companyVo) {
//		return sqlSession.delete("mapper.companyMember.deleteCompany", companyVo);
//	}
//
//	public int emailCheck(String email) {
//		return sqlSession.selectOne("mapper.companyMember.emailCheck", email);
//	}
//
//	public int phoneCheck(String phone_num) {
//		return sqlSession.selectOne("mapper.companyMember.phoneCheck", phone_num);
//	}
//
//}
