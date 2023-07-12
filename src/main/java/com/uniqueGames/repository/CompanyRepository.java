package com.uniqueGames.repository;

import com.uniqueGames.model.Company;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompanyRepository {
    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * company login
     */
    public int login(Company company) {
        return sqlSession.selectOne("mapper.companyMember.login", company);
    }

    /**
     * sign up member
     */
    public int insert(Company company) {
        return sqlSession.insert("mapper.companyMember.insert", company);
    }

    /**
     * id checking
     */
    public int idCheck(String companyId) {
        return sqlSession.selectOne("mapper.companyMember.idCheck", companyId);
    }

    /**
     * find-id-check
     */
    public String findIdCheck(Company company) {
        return sqlSession.selectOne("mapper.companyMember.findId", company);
    }

    public int findPwdCheck(Company company) {
        return sqlSession.selectOne("mapper.companyMember.findPwd", company);
    }

    public int update(Company company) {
        return sqlSession.update("mapper.companyMember.update", company);
    }

    public Company companyPage(String companyId) {
        return sqlSession.selectOne("mapper.companyMember.companyPage", companyId);
    }

    public int changeCpassword(Company company) {
        return sqlSession.update("mapper.companyMember.changeCpassword", company);
    }

    public String getGameNameByCID(String companyId) {
        return sqlSession.selectOne("mapper.companyMember.getGameName", companyId);
    }

    public int deleteCompany(Company company) {
        return sqlSession.delete("mapper.companyMember.deleteCompany", company);
    }

    public int emailCheck(String email) {
        return sqlSession.selectOne("mapper.companyMember.emailCheck", email);
    }

    public int phoneCheck(String phoneNum) {
        return sqlSession.selectOne("mapper.companyMember.phoneCheck", phoneNum);
    }

    public List<Company> aGetMemberList() {
        return sqlSession.selectList("mapper.companyMember.aGetMemberList");
    }

    public Company aGetDetailMember(String id) {
        return sqlSession.aGetDetailMember("mapper.companyMember.aGetDetailMember", id);
    }
}
