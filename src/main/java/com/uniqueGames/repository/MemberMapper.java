package com.uniqueGames.repository;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    int save(Member member);

    @Select("select count(*) from tb_member where member_id=#{memberId}")
    int idCheck(String memberId);

    @Select("select member_id from tb_member where email=#{email} and name=#{name}")
    String findMid(Member member);

    @Select("select member_id from tb_member where email=#{email} and member_id=#{memberId} and name=#{name}")
    String findMpass(Member member);

    @Update("update tb_member set password=#{newpassword} where member_id=#{memberId}")
    int changeMpass(Member member);

    @Select("select count(*) from tb_member where phone_num=#{phoneNum}")
    int phoneCheck(String phoneNum);

    @Select("select count(*) from tb_member where email=#{email}")
    int emailCheck(String email);

    @Select("SELECT member_id, name FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, member_id, name FROM TB_MEMBER) AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Member> aGetMemberList(@Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT * FROM TB_MEMBER WHERE MEMBER_ID=#{id}")
    Member aGetDetailMember(String id);

    @Delete("delete from tb_member where member_id=#{memberId} and password=#{password}")
    int delete(String memberId, String password);

    @Update("update tb_member set password=#{newpassword} where member_id=#{memberId}")
    int mypageNewPass(Member member);

    @Update("update tb_member set profile_img = #{profileImg}, email = #{email}, addr = #{addr}, phone_num = #{phoneNum} where member_id = #{memberId}")
    int update(Member member);
  
    @Select("SELECT COUNT(*) FROM TB_MEMBER")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM TB_MEMBER WHERE NAME LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);

    @Delete("DELETE FROM TB_MEMBER WHERE MEMBER_ID = #{mid}")
    int aDeleteMember(String mid);
}
