package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
	// Field
	String orderDate, method, paymentStatus, gametitle, gameImg, userId, mId, cId;
	int id, gId, amount, rno;
	public Order(){
	}
	public Order(String mId, String cId, int gId, int amount, String gametitle, String gameImg) {

		this.gametitle = gametitle;
		this.gameImg = gameImg;
		this.mId = mId;
		this.cId = cId;
		this.gId = gId;
		this.amount = amount;
	}

	// Method
	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getGametitle() {
		return gametitle;
	}

	public void setGametitle(String gametitle) {
		this.gametitle = gametitle;
	}

	public String getGameImg() {
		return gameImg;
	}

	public void setGameImg(String gameImg) {
		this.gameImg = gameImg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getmId() {
		return mId;
	}

	public void setmId(String mId) {
		this.mId = mId;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getgId() {
		return gId;
	}

	public void setgId(int gId) {
		this.gId = gId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}
}
