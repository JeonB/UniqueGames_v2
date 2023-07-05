package com.uniqueGames.repository;


import com.uniqueGames.model.Intro;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 스프링 마이바티스 방법1
 * 매퍼(xml파일)과 매핑하여 리포지토리로 사용.
 * 기존 DAO필요 X
 */
@Repository
@Mapper
public interface DetailMapper {
    void insertIntro(Intro vo);
    Intro getIntro(int id);
    void updateIntro(Intro vo);
    void deleteIntro(int id);
    List<Intro> getIntroList();
}
