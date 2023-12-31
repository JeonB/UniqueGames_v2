package com.uniqueGames.repository;
import com.uniqueGames.model.Intro;
import com.uniqueGames.model.Pagination;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 스프링 마이바티스 방법
 * 매퍼(xml파일)과 매핑하여 리포지토리로 사용.
 * 기존 순수 JDBC DBConnector를 사용한 DAO 대체
 */
@Repository
@Mapper
public interface DetailMapper {

    /**
     * 회사 소개 게시글 CRUD 메소드
     * @param vo 회사 소개 인스턴스
     */
    void insertIntro(Intro vo);
    Intro getIntro(int id);
    void updateIntro(Intro vo);
    void deleteIntro(int id);
    List<Intro> getIntroList();
    Integer findByAllCount();

    @Select("select * from TB_INTRO " + "order by ${order} limit #{perRows} offset #{perFirstRow}")
    List<Intro> findByAllPaginationAndSearch(Pagination pagination);

    @Select("select id from TB_INTRO where c_id = #{cId}")
    Integer findIdByCId(String cId);
}
