package com.uniqueGames.repository;


import com.uniqueGames.model.Company;
import com.uniqueGames.model.Notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NoticeMapper {
    List<Notice> selectNotice(@Param("start") int startCount, @Param("end") int endCount);

    List<Notice> searchList(@Param("keywordList") String[] keywordList, @Param("pageMap") Map pageMap,
                            @Param("searchType") String searchType);

    int insertNotice(Notice notice);

    Notice selectContent(String no);

    int updateNotice(Notice notice);

    int deleteNotice(String no);

    List<String> deleteListBefore(String[] list);

    int deleteList(@Param("list") String[] list, @Param("company") Company company);

    void hitsCount(String no);

    int insertImage(Notice notice);

    @Select("SELECT UPLOAD_IMG FROM TB_NOTICE_IMAGE WHERE POST_ID = #{no}")
    String[] getDbImage(int no);

    int deleteImage(List<String> deleteImg);

    @Select("SELECT COUNT(*) FROM TB_NOTICE")
    int totRowCount();

    int totRowCountSearch(@Param("keywordList") String[] keywordList, @Param("searchType") String searchType);
}
