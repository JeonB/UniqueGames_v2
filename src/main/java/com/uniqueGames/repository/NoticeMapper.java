package com.uniqueGames.repository;


import com.uniqueGames.model.Notice;

import java.util.List;

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

    int deleteList(String[] list);

    List<Notice> searchList(@Param("keyword") String keyword, @Param("start") int startCount,
                            @Param("end") int endCount);

    void hitsCount(String no);

    int insertFile(Notice notice);

    int updateFile(Notice notice);

    int updateUploadFile(Notice notice);

    int deleteFile(Notice notice);

    int fileCheck(Notice notice);

    @Select("select count(*) from notice")
    int totRowCount();

    @Select("SELECT COUNT(*) FROM NOTICE WHERE TITLE LIKE CONCAT('%', #{keyword}, '%')")
    int totRowCountSearch(String keyword);
}
