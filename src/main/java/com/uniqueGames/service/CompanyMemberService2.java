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

    public int cidCheck(String companyId) {
        return companyMapper.cidCheck(companyId);
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

    public int changeCpass(String companyId, String newpassword) {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setNewpassword(newpassword);
        return companyMapper.changeCpass(company);
    }

    public int CmypageNewPass(String companyId, String password, String newpassword) {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setPassword(password);
        company.setNewpassword(newpassword);
        return companyMapper.CmypageNewPass(company);
    }

    public int cphoneCheck(String phoneNum) {
        return companyMapper.cphoneCheck(phoneNum);
    }

    public int cemailCheck(String email) {
        return companyMapper.cemailCheck(email);
    }

    public int cdelete(String companyId, String password) {
        return companyMapper.cdelete(companyId, password);
    }

    public int update(Company company) {
        return companyMapper.update(company);
    }

}
