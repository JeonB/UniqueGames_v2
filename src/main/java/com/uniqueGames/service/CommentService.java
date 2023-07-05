package com.uniqueGames.service;


import com.uniqueGames.model.CommentVo;
import java.util.List;

public interface CommentService {

	String commentInsert(CommentVo commentVo);
	
	List<CommentVo> select(String no);
	
	String delete(String no);
}
