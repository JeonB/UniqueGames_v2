package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
	int id, rno;
	String name = "";
	String gameGenre = "";
	String uploadImg = "";
	String description = "";
	int donationStatus;
	int likeCount;
}
