package com.uniqueGames.repository;


import com.uniqueGames.model.Company;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CompanyRepositoryMapper {

//   @Insert("insert into company(email, name, password) values(#{email}, #{name}, #{password})")
//   @Options(useGeneratedKeys = true, keyProperty = "id")
//   void save(CompanyVo company);

   @Select("SELECT * FROM TB_COMPANY WHERE G_ID = #{gId}")
   Company findByIndex(int gId);

   @Select("SELECT * FROM TB_COMPANY WHERE COMPANY_ID = #{companyId}")
   Company findById(String companyId);

   @Select("SELECT * FROM TB_COMPANY")
   List<Company> findAll();

   @Select("SELECT * FROM TB_COMPANY WHERE EMAIL = #{email} AND NAME = #{name}")
   Company findByEmailAndName(String email, String name);

   @Select("UPDATE TB_COMPANY SET NAME=#{name}, PASSWORD=#{oldPassword} WHERE EMAIL=#{email}")
   Integer update(Company company);
   
   @Select("SELECT COUNT(*) FROM TB_COMPANY WHERE COMPANY_ID = #{companyId} AND PASSWORD = #{password}")
   int passEqual(Company company);

}
