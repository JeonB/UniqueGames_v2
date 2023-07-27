package com.uniqueGames.service;

import com.uniqueGames.model.Comment;
import com.uniqueGames.repository.CommentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	CommentMapper commentMapper;

	@Autowired
	public CommentService(CommentMapper commentMapper) {
		this.commentMapper = commentMapper;
	}

	/**
	 * 댓글 - 작성
	 */
	public String commentInsert(Comment comment) {
		String result = "FAIL";

		comment.setCommentContent(comment.getCommentContent().replaceAll("\r\n", "<br>"));
		int dbResult = commentMapper.insertComment(comment);
		if (dbResult == 1) {
			result = "SUCCESS";
		}

		return result;
	}

	/**
	 * 댓글 - 페이지별 댓글 전체 리스트
	 */
	public List<Comment> select(String no) {

		return commentMapper.selectComment(no);
	}

	public Comment selectOne(int no) {

		return commentMapper.selectOneComment(no);
	}
	
	/**
	 * 댓글 - 삭제
	 */
	public String delete(String no) {
		String result = "FAIL";
		int dbResult = commentMapper.deleteComment(no);
		
		if (dbResult == 1) {
			result = "SUCCESS";
		}
		
		return result;
	}

}
