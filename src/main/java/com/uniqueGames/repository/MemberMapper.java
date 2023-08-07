package com.uniqueGames.repository;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface MemberMapper {

    @Insert("INSERT INTO TB_MEMBER (MEMBER_ID, PASSWORD, NAME, EMAIL, PHONE_NUM, ADDR, TEL, PROFILE_IMG) " +
            " VALUES (#{memberId},#{password},#{name},#{email},#{phoneNum},#{addr},#{tel}, #{profileImg})")
    int save(Member member);

    @Update("UPDATE TB_MEMBER SET PASSWORD=#{newpassword} WHERE MEMBER_ID=#{memberId}")
    int changeMpass(Member member);

    @Select("SELECT MEMBER_ID, NAME FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, MEMBER_ID, NAME FROM TB_MEMBER) AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Member> aGetMemberList(@Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT * FROM TB_MEMBER WHERE MEMBER_ID=#{id}")
    Member aGetDetailMember(String id);

    @Update("UPDATE TB_MEMBER SET PASSWORD=#{newpassword} WHERE MEMBER_ID=#{memberId}")
    int mypageNewPass(Member member);

    @Update("UPDATE TB_MEMBER SET PROFILE_IMG = #{profileImg}, EMAIL = #{email}, ADDR = #{addr}, PHONE_NUM = #{phoneNum}, TEL = #{tel} WHERE MEMBER_ID = #{memberId}")
    int update(Member member);
  
    @Select("SELECT COUNT(*) FROM TB_MEMBER")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM TB_MEMBER WHERE NAME LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);

    @Delete("DELETE FROM TB_MEMBER WHERE MEMBER_ID = #{mid}")
    int aDeleteMember(String mid);

    int idCheck1(Map param);
    int emailDuplicateCheck(Map param);
    int phoneCheck1(Map param);
    String findId(Map param);
    String findPass(Map param);
    int delete1(Map param);


}
