package com.uniqueGames.repository;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper extends JpaRepository<Member, String> {

//    int save(Member member);

//    @Select("select count(*) from member where member_id=#{memberId}")
//    int idCheck(String memberId);
//
//    @Select("select member_id from member where email=#{email} and name=#{name}")
//    String findMid(Member member);
//
//    @Select("select count(*) from member where email=#{email} and member_id=#{memberId} and name=#{name}")
//    int findMpass(Member member);
}
