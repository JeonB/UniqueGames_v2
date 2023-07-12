package com.uniqueGames.repository;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

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

    @Select("SELECT member_id, name FROM MEMBER ORDER BY ${order1} ${order2}")
    List<Member> aGetMemberList(@Param("order1") String order1, @Param("order2") String order2);

    @Select("SELECT * FROM MEMBER WHERE MEMBER_ID=#{id}")
    Member aGetDetailMember(String id);
}
