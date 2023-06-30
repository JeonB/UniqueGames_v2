package com.uniqueGames.service;

import com.uniqueGames.model.CommentVo;
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
	public String commentInsert(CommentVo commentVo) {
		String result = "FAIL";

		commentVo.setComment_content(commentVo.getComment_content().replaceAll("\r\n", "<br>"));
		int dbResult = commentMapper.insertComment(commentVo);
		if (dbResult == 1) {
			result = "SUCCESS";
		}

		return result;
	}

	/**
	 * 댓글 - 페이지별 댓글 전체 리스트
	 */
	@Override
	public List<CommentVo> select(String no) {

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
