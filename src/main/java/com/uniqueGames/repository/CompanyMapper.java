package com.uniqueGames.repository;

import com.uniqueGames.model.Company;
import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyMapper {

    @Insert("insert into TB_COMPANY (company_id, password, name, email, tel, phone_num, addr, profile_img) " +
            "values (#{companyId},#{password},#{name},#{email},#{tel},#{phoneNum},#{addr},#{profileImg})")
    int save(Company company);

    @Update("update TB_COMPANY set password=#{newpassword} where company_id=#{companyId}")
    int changeCpass(Company company);

    @Update("update TB_COMPANY set password=#{newpassword} where company_id=#{companyId}")
    int CmypageNewPass(Company company);


    @Update("update tb_company set profile_img = #{newProfileImg}, email = #{email}, addr = #{addr}, phone_num = #{phoneNum}, tel = #{tel} where company_id = #{companyId}")
    int update(Company company);

    // ADMIN
    @Select("SELECT RNO, COMPANY_ID, NAME FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, COMPANY_ID, NAME FROM TB_COMPANY) AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Company> aGetMemberList(@Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

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

    @Select("select b.name from tb_company a, tb_game b where a.g_id = b.id and company_id=#{companyId}")
    String gameName(String companyId);

    @Select("SELECT COUNT(*) FROM TB_COMPANY WHERE COMPANY_ID = #{cId} AND G_ID IS NOT NULL")
    int aGetGameRegistered(String cId);

    @Update("UPDATE TB_COMPANY SET G_ID = #{gid} WHERE COMPANY_ID = #{cid}")
    int aSetGid(int gid, String cid);

    @Select("SELECT COUNT(*) FROM TB_COMPANY WHERE COMPANY_ID = #{cid} AND G_ID = #{gid}")
    int aGetSameGame(int gid, String cid);

    @Delete("UPDATE TB_COMPANY SET G_ID = NULL WHERE COMPANY_ID = #{cid}")
    int aDeleteGid(String cid);

    @Delete("DELETE FROM TB_COMPANY WHERE COMPANY_ID = #{mid}")
    int aDeleteMember(String mid);
}
