package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	int id;
	int postId;
	String mId;
	String commentContent;
	String commentDate;
	String profileImg;
	String reason;
	String reportedUsers;
}
