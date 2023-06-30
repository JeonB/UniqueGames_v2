package com.uniqueGames.service;


import com.uniqueGames.model.CommentVo;
import java.util.List;

public interface CommentService {

	public String commentInsert(CommentVo commentVo);
	
	public List<CommentVo> select(String no);
	
	public String delete(String no);
}
