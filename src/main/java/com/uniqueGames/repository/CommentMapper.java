package com.uniqueGames.repository;


import com.uniqueGames.model.Comment;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

    @Select("SELECT tb_comment.ID AS ID, POST_ID, M_ID, COMMENT_CONTENT, COMMENT_DATE, PROFILE_IMG" +
            " FROM TB_COMMENT LEFT JOIN TB_MEMBER ON tb_comment.M_ID = tb_member.MEMBER_ID" +
            " WHERE POST_ID = #{no}" +
            " ORDER BY COMMENT_DATE ASC")
    public List<Comment> selectComment(String no);

    @Insert("INSERT INTO TB_COMMENT (POST_ID, M_ID, COMMENT_CONTENT)"
            + " VALUES (#{postId}, #{mId}, #{commentContent})")
    public int insertComment(Comment comment);

    @Delete("DELETE FROM TB_COMMENT WHERE ID = #{no}")
    public int deleteComment(String no);

    @Select("SELECT COUNT(*) FROM TB_COMMENT WHERE POST_ID = #{no}")
    public int getCmtCount(int no);

}
