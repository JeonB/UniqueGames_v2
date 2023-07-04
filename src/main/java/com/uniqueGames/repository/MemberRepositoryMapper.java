package com.uniqueGames.repository;


import com.uniqueGames.model.MemberVo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepositoryMapper {

   //@Insert("insert into member(email, name, password) values(#{email}, #{name}, #{password})")
   //@Options(useGeneratedKeys = true, keyProperty = "id")
   void save(MemberVo member);

   //@Select("select * from member where id = #{findId}")
//   MemberVo findById(Long findId);
//
   @Select("select * from member where member_id = #{memberId}")
   MemberVo findById(String memberId);

   @Select("select * from member")
   List<MemberVo> findAll();
   
   @Select("select count(*) from member where member_id = #{memberId} and password = #{password}")
   int passEqual(MemberVo member);

   //@Select("select * from member where email = #{email} and name = #{name}")
   //XmlMapper로 구현
//   Optional<MemberVo> findByEmailAndName(String email, String name);

   @Select("update member set name=#{name}, password=#{oldPassword} where email=#{email}")
   Integer update(MemberVo member);

}
