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

    Notice selectContent(String no);

    int insertNotice(Notice notice);

    int updateNotice(Notice notice);

    int deleteNotice(String no);

    List<String> deleteListBefore(String[] list);

    int deleteList(@Param("list") String[] list, @Param("company") Company company);

    List<Notice> searchList(@Param("keywordList") String[] keywordList, @Param("pageMap")Map pageMap,
                            @Param("searchType") String searchType);

    void hitsCount(String no);

    int insertFile(Notice notice);

    int updateFile(Notice notice);

    int updateUploadFile(Notice notice);

    int deleteFile(Notice notice);

    int fileCheck(Notice notice);

    @Select("select count(*) from notice")
    int totRowCount();

    int totRowCountSearch(String[] keywordList);
}
