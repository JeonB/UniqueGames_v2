package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameVo {
	int id, rno;
	String name = "";
	String game_genre = "";
	String image_path = "";
	String description = "";
	String like_list = "";
	int donation_status;
	int like_count;

	public String getLike_list() {return like_list;}
	public void setLike_list(String like_list) {this.like_list = like_list;}
	public int getLike_count() {return like_count;}
	public void setLike_count(int like_count) {this.like_count = like_count;}
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
		return game_genre;
	}
	public void setGame_genre(String game_genre) {
		this.game_genre = game_genre;
	}
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDonation_status() {
		return donation_status;
	}
	public void setDonation_status(int donation_status) {
		this.donation_status = donation_status;
	}
}
