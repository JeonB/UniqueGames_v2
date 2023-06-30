package com.uniqueGames.repository;


import com.uniqueGames.model.IntroVo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 *  스프링 마이바티스 방법2
 *  인터페이스의 메소드위에 직접 쿼리를 작성하여 매핑
 *  매퍼(xml파일) 필요x
 */
@Repository
@Mapper
public interface DetailMapper2 {
    @Insert("INSERT INTO intro (name,title, content,upload)"
            + " VALUES ( #{name}, #{title}, #{content},#{upload})")
    void insertIntro(IntroVo vo);
    @Select("SELECT * FROM intro WHERE id = #{id}")
    IntroVo getIntro(int id);
    @Update(" UPDATE intro SET title = #{title}, content = #{content}, name = #{name}"
            + "  WHERE id = #{id}")
    void updateIntro(IntroVo vo);
    @Delete("DELETE FROM intro WHERE id = #{id}")
    void deleteIntro(int id);
    @Select("SELECT * FROM intro ORDER BY id")
    List<IntroVo> getIntroList();
}
