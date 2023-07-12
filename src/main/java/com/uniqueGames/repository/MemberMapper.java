package com.uniqueGames.repository;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MemberMapper {

    int save(Member member);

    @Select("select count(*) from member where member_id=#{memberId}")
    int idCheck(String memberId);

    @Select("select member_id from member where email=#{email} and name=#{name}")
    String findMid(Member member);

    @Select("select member_id from member where email=#{email} and member_id=#{memberId} and name=#{name}")
    String findMpass(Member member);

    @Update("update member set password=#{mnewpassword} where member_id=#{memberId}")
    int changeMpass(Member member);

    @Select("select count(*) from member where phone_num=#{phoneNum}")
    int phoneCheck(String phoneNum);

    @Select("select count(*) from member where email=#{email}")
    int emailCheck(String email);

    @Select("SELECT member_id, name FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, member_id, name FROM MEMBER) AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Member> aGetMemberList(@Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT * FROM MEMBER WHERE MEMBER_ID=#{id}")
    Member aGetDetailMember(String id);

    @Delete("delete from member where member_id=#{memberId} and password=#{password}")
    int delete(String memberId, String password);

    @Select("SELECT COUNT(*) FROM MEMBER")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM MEMBER WHERE NAME LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);

}