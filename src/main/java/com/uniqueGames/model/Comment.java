package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	int comment_id;
	int post_id;
	String member_id;
	String comment_content;
	String comment_date;

}
