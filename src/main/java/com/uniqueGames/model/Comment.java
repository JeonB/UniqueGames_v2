package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	int commentId;
	int postId;
	String memberId;
	String commentContent;
	String commentDate;

}
