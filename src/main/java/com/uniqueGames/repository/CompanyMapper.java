package com.uniqueGames.repository;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyMapper {

    @Insert("insert into TB_COMPANY (company_id, password, name, email, tel, phone_num, addr) " +
            "values (#{companyId},#{password},#{name},#{email},#{tel},#{phoneNum},#{addr})")
    int save(Company company);

    @Select("select count(*) from TB_COMPANY where company_id=#{companyId}")
    int cidCheck(String companyId);

    @Select("select company_id from TB_COMPANY where email=#{email} and name=#{name}")
    String findCid(Company company);

    @Select("select company_id from TB_COMPANY where email=#{email} and company_id=#{companyId} and name=#{name}")
    String findCpass(Company company);

    @Update("update TB_COMPANY set password=#{newpassword} where company_id=#{companyId}")
    int changeCpass(Company company);

    @Select("select count(*) from TB_COMPANY where phone_num=#{phoneNum}")
    int cphoneCheck(String phoneNum);

    @Select("select count(*) from TB_COMPANY where email=#{email}")
    int cemailCheck(String email);

    @Update("update TB_COMPANY set password=#{newpassword} where company_id=#{companyId}")
    int CmypageNewPass(Company company);

    @Delete("delete from TB_COMPANY where company_id=#{companyId} and password=#{password}")
    int cdelete(String companyId, String password);

    @Update("update TB_COMPANY set email=#{email}, phone_num=#{phoneNum}, addr=#{addr} where company_id=#{companyId}")
    int update(Company company);

    // ADMIN
    @Select("SELECT company_id, name FROM TB_COMPANY ORDER BY ${order1} ${order2}")
    List<Company> aGetMemberList(@Param("order1") String order1, @Param("order2") String order2);

    @Select("SELECT * FROM TB_COMPANY WHERE COMPANY_ID=#{id}")
    Company aGetDetailMember(String id);

    @Select("SELECT COUNT(*) FROM TB_COMPANY")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM TB_COMPANY WHERE NAME LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);

    @Select("SELECT * FROM TB_COMPANY WHERE G_ID = #{id}")
    Company aGetCompany(int id);

    @Select("SELECT * FROM TB_COMPANY")
    List<Company> aGetAllCompanyList();

    @Select("SELECT * FROM TB_COMPANY WHERE UPPER(NAME) LIKE CONCAT('%', #{companyName}, '%')")
    List<Company> aGetSearched(String companyName);
}
