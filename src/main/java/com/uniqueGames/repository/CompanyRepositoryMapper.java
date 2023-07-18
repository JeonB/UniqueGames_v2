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

   @Select("select * from tb_company where g_id = #{gId}")
   Company findByIndex(int gId);

   @Select("select * from tb_company where company_id = #{companyId}")
   Company findById(String companyId);

   @Select("select * from tb_company")
   List<Company> findAll();

   @Select("select * from tb_company where email = #{email} and name = #{name}")
   Company findByEmailAndName(String email, String name);

   @Select("update tb_company set name=#{name}, password=#{oldPassword} where email=#{email}")
   Integer update(Company company);
   
   @Select("select count(*) from tb_company where company_id = #{companyId} and password = #{password}")
   int passEqual(Company company);

}
