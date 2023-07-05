package com.uniqueGames.repository;


import com.uniqueGames.model.CommentVo;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CommentMapper {

	@Select("SELECT	* FROM COMMENT WHERE POST_ID = #{no} ORDER BY COMMENT_DATE ASC")
    List<CommentVo> selectComment(String no);

	@Insert("INSERT INTO COMMENT (POST_ID, MEMBER_ID, COMMENT_CONTENT)"
			+ " VALUES (#{post_id}, #{member_id}, #{comment_content})")
    int insertComment(CommentVo commentVo);

	@Delete("DELETE FROM COMMENT WHERE COMMENT_ID = #{no}")
    int deleteComment(String no);

}
