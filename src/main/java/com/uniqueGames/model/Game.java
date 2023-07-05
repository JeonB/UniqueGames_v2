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

	public String getLike_list() {return likeList;}
	public void setLike_list(String like_list) {this.likeList = like_list;}
	public int getLike_count() {return likeCount;}
	public void setLike_count(int like_count) {this.likeCount = like_count;}
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
	public String getGame_genre() {
		return gameGenre;
	}
	public void setGame_genre(String game_genre) {
		this.gameGenre = game_genre;
	}
	public String getImage_path() {
		return imagePath;
	}
	public void setImage_path(String image_path) {
		this.imagePath = image_path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDonation_status() {
		return donationStatus;
	}
	public void setDonation_status(int donation_status) {
		this.donationStatus = donation_status;
	}
}
