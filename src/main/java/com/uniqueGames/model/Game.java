package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
	int id, rno;
	String name = "";
	String gameGenre = "";
	String imagePath = "";
	String description = "";
	String likeList = "";
	int donationStatus;
	int likeCount;

	public String getLikeList() {return likeList;}
	public void setLikeList(String like_list) {this.likeList = like_list;}
	public int getLikeCount() {return likeCount;}
	public void setLikeCount(int like_count) {this.likeCount = like_count;}
	public int getRno() {return rno;}
	public void setRno(int rno) {this.rno = rno;}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGameGenre() {
		return gameGenre;
	}
	public void setGameGenre(String game_genre) {
		this.gameGenre = game_genre;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String image_path) {
		this.imagePath = image_path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDonationStatus() {
		return donationStatus;
	}
	public void setDonationStatus(int donation_status) {
		this.donationStatus = donation_status;
	}
}
