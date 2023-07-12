package com.uniqueGames.service;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import com.uniqueGames.repository.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyMemberService2 {

    @Autowired
    private CompanyMapper companyMapper;

    public int save(Company company) {
        return companyMapper.save(company);
    }

    public int idCheck(String companyId) {
        return companyMapper.idCheck(companyId);
    }

    public String findCid(String email, String name) {
        Company company = new Company();
        company.setEmail(email);
        company.setName(name);
        return companyMapper.findCid(company);
    }

    public String findCpass(String email, String companyId, String name) {
        Company company = new Company();
        company.setEmail(email);
        company.setCompanyId(companyId);
        company.setName(name);
        return companyMapper.findCpass(company);
    }

    public int changeCpass(String companyId, String cnewpassword) {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setCnewpassword(cnewpassword);
        return companyMapper.changeCpass(company);
    }

    public int phoneCheck(String phoneNum) {
        return companyMapper.phoneCheck(phoneNum);
    }

    public int emailCheck(String email) {
        return companyMapper.emailCheck(email);
    }

    public int delete(String companyId, String password) {
        return companyMapper.delete(companyId, password);
    }

}
