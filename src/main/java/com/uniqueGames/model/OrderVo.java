package com.uniqueGames.model;

public class OrderVo {
	// Field
	String order_date, method, payment_status, gametitle, game_img, userId, m_id, c_id;
	int id, g_id, amount, rno;
	public OrderVo(){
	}
	public OrderVo( String m_id, String c_id, int g_id,int amount, String gametitle, String game_img) {

		this.gametitle = gametitle;
		this.game_img = game_img;
		this.m_id = m_id;
		this.c_id = c_id;
		this.g_id = g_id;
		this.amount = amount;
	}

	// Method
	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getGametitle() {
		return gametitle;
	}

	public void setGametitle(String gametitle) {
		this.gametitle = gametitle;
	}

	public String getGame_img() {
		return game_img;
	}

	public void setGame_img(String game_img) {
		this.game_img = game_img;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getG_id() {
		return g_id;
	}

	public void setG_id(int g_id) {
		this.g_id = g_id;
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
