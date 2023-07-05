package com.uniqueGames.service;

import com.uniqueGames.model.Comment;
import com.uniqueGames.repository.CommentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	/**
	 * 댓글 - 작성
	 */
	@Override
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
	@Override
	public List<Comment> select(String no) {

		return commentMapper.selectComment(no);
	}
	
	/**
	 * 댓글 - 삭제
	 */
	@Override
	public String delete(String no) {
		String result = "FAIL";
		int dbResult = commentMapper.deleteComment(no);
		
		if (dbResult == 1) {
			result = "SUCCESS";
		}
		
		return result;
	}

}
