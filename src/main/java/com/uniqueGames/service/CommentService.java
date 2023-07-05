package com.uniqueGames.service;


import com.uniqueGames.model.Comment;
import java.util.List;

public interface CommentService {

	public String commentInsert(Comment comment);
	
	public List<Comment> select(String no);
	
	public String delete(String no);
}
