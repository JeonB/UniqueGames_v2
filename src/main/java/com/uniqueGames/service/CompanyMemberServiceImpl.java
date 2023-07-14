package com.uniqueGames.service;

import com.uniqueGames.model.Company;
import com.uniqueGames.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public int companyChangeCPassword(String companyId, String newpassword) {
        Company company = new Company();
        company.setCompanyId(companyId);
        company.setNewpassword(newpassword);
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

    @Override
    public ArrayList<Company> aGetMemberList() {
        ArrayList<Company> cList = new ArrayList<>();
        for (Company company : companyRepository.aGetMemberList()) {
            cList.add(company);
        }

        return cList;
    }

    @Override
    public Company aGetDetailMember(String id) {
        return companyRepository.aGetDetailMember(id);
    }

}
