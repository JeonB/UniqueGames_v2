package com.uniqueGames.repository;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyMapper {

    int save(Company company);

    @Select("select count(*) from company where company_id=#{companyId}")
    int idCheck(String companyId);

    @Select("select company_id from company where email=#{email} and name=#{name}")
    String findCid(Company company);

    @Select("select company_id from company where email=#{email} and company_id=#{companyId} and name=#{name}")
    String findCpass(Company company);

    @Update("update company set password=#{cnewpassword} where company_id=#{companyId}")
    int changeCpass(Company company);

    @Select("select count(*) from company where phone_num=#{phoneNum}")
    int phoneCheck(String phoneNum);

    @Select("select count(*) from company where email=#{email}")
    int emailCheck(String email);

    @Delete("delete from company where company_id=#{companyId} and password=#{password}")
    int delete(String companyId, String password);
}
