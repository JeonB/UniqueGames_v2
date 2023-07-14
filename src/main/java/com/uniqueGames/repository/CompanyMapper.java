package com.uniqueGames.repository;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyMapper {

    @Insert("insert into company (company_id, password, name, email, tel, phone_num, addr) " +
            "values (#{companyId},#{password},#{name},#{email},#{tel},#{phoneNum},#{addr})")
    int save(Company company);

    @Select("select count(*) from company where company_id=#{companyId}")
    int cidCheck(String companyId);

    @Select("select company_id from company where email=#{email} and name=#{name}")
    String findCid(Company company);

    @Select("select company_id from company where email=#{email} and company_id=#{companyId} and name=#{name}")
    String findCpass(Company company);

    @Update("update company set password=#{newpassword} where company_id=#{companyId}")
    int changeCpass(Company company);

    @Select("select count(*) from company where phone_num=#{phoneNum}")
    int cphoneCheck(String phoneNum);

    @Select("select count(*) from company where email=#{email}")
    int cemailCheck(String email);

    @Update("update company set password=#{newpassword} where company_id=#{companyId}")
    int CmypageNewPass(Company company);

    @Delete("delete from company where company_id=#{companyId} and password=#{password}")
    int cdelete(String companyId, String password);

    @Update("update company set email=#{email}, phone_num=#{phoneNum}, addr=#{addr} where company_id=#{companyId}")
    int update(Company company);
}
