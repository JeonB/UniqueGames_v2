package com.uniqueGames.service;


import com.uniqueGames.model.OrderVo;
import java.util.ArrayList;
import java.util.List;

public interface OrderService {
	ArrayList<OrderVo> getCartList(String m_id);

	int getCartCount(String m_id);

	int getCartDeleteAll(String m_id);

	int getCartDeleteOne(int id);

	void insertCart(OrderVo orderVo);

	ArrayList<OrderVo> getOrderList(List<Integer> checkedList);

	int getOrderAmount(List<Integer> checkedList);

	int getOrderComplete(List<Integer> checkedList, String method);

	ArrayList<OrderVo> getPaymentDetail(String m_id, String array);

	int getPaymentCount(String m_id);

	int getPaymentAmount(String m_id);

	ArrayList<OrderVo> getDonationDetail(String c_id, String array);

	int getExpected(String c_id);

	int getTotalDonation(String c_id);

	ArrayList<OrderVo> getDonationRank(String c_id);

}
