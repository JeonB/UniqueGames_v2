package com.uniqueGames.repository;


import com.uniqueGames.model.Comment;

import java.util.List;

import com.uniqueGames.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

    @Select("SELECT TB_COMMENT.ID AS ID, POST_ID, M_ID, COMMENT_CONTENT, COMMENT_DATE, PROFILE_IMG" +
            " FROM TB_COMMENT LEFT JOIN TB_MEMBER ON TB_COMMENT.M_ID = TB_MEMBER.MEMBER_ID" +
            " WHERE POST_ID = #{no}" +
            " ORDER BY COMMENT_DATE ASC")
    List<Comment> selectComment(String no);

    @Select("SELECT * FROM TB_COMMENT WHERE ID = #{no}")
    Comment selectOneComment(int no);

    @Insert("INSERT INTO TB_COMMENT (POST_ID, M_ID, COMMENT_CONTENT)"
            + " VALUES (#{postId}, #{mId}, #{commentContent})")
    int insertComment(Comment comment);

    @Delete("DELETE FROM TB_COMMENT WHERE ID = #{no}")
    int deleteComment(String no);

    @Select("SELECT COUNT(*) FROM TB_COMMENT WHERE POST_ID = #{no}")
    int getCmtCount(int no);

    @Update("UPDATE TB_COMMENT SET REPORTED_USERS = IF(" +
            "REPORTED_USERS IS NULL, #{member.id}, CONCAT(REPORTED_USERS, ',', #{member.id})" +
            ")" +
            "WHERE ID = #{comment.id}")
    void report(@Param("comment") Comment comment, @Param("member") Member member);

    @Select("SELECT * FROM (SELECT row_number() over(order by COMMENT_DATE DESC) as RNO, ID, POST_ID, M_ID, COMMENT_CONTENT, COMMENT_DATE, REPORTED_USERS" +
            " FROM TB_COMMENT) T1" +
            " WHERE REPORTED_USERS IS NOT NULL AND RNO BETWEEN #{startCount} AND #{endCount} order by length(REPORTED_USERS)")
    List<Comment> selectAll(@Param("startCount") int startCount, @Param("endCount") int endCount);

    @Update("UPDATE TB_COMMENT SET REPORTED_USERS = NULL WHERE ID = #{id}")
    int reportCancel(int id);

    @Select("SELECT COUNT(*) FROM TB_COMMENT")
    int totRowCount();
}