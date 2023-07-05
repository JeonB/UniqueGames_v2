package com.uniqueGames.service;


import com.uniqueGames.model.Order;
import java.util.ArrayList;
import java.util.List;

public interface OrderService {
	ArrayList<Order> getCartList(String m_id);

	int getCartCount(String m_id);

	int getCartDeleteAll(String m_id);

	int getCartDeleteOne(int id);

	void insertCart(Order order);

	ArrayList<Order> getOrderList(List<Integer> checkedList);

	int getOrderAmount(List<Integer> checkedList);

	int getOrderComplete(List<Integer> checkedList, String method);

	ArrayList<Order> getPaymentDetail(String m_id, String array);

	int getPaymentCount(String m_id);

	int getPaymentAmount(String m_id);

	ArrayList<Order> getDonationDetail(String c_id, String array);

	int getExpected(String c_id);

	int getTotalDonation(String c_id);

	ArrayList<Order> getDonationRank(String c_id);

}
