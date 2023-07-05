package com.uniqueGames.service;


import com.uniqueGames.model.Order;
import com.uniqueGames.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
    OrderRepository orderRepository;

	@Override
	public ArrayList<Order> getCartList(String m_id) {
		return orderRepository.getCartList(m_id);
	}

	@Override
	public int getCartCount(String m_id) {
		return orderRepository.getCartCount(m_id);
	}

	@Override
	public int getCartDeleteAll(String m_id) {
		return orderRepository.getCartDeleteAll(m_id);
	}

	@Override
	public int getCartDeleteOne(int id) {
		return orderRepository.getCartDeleteOne(id);
	}

	@Override
	public void insertCart(Order order) {
		orderRepository.insertCart(order);
	}
	public Order addToOrderVo(String m_id, String c_id, int g_id, int amount, String gametitle, String game_img){
		return new Order(m_id,c_id,g_id,amount,gametitle,game_img);
	}

	@Override
	public ArrayList<Order> getOrderList(List<Integer> checkedList) {
		return orderRepository.getOrderList(checkedList);
	}

	@Override
	public int getOrderAmount(List<Integer> checkedList) {
		return orderRepository.getOrderAmount(checkedList);
	}

	@Override
	public int getOrderComplete(List<Integer> checkedList, String method) {
		return orderRepository.getOrderComplete(checkedList, method);
	}

	@Override
	public ArrayList<Order> getPaymentDetail(String m_id, String array) {
		return orderRepository.getPaymentDetail(m_id, array);
	}

	@Override
	public int getPaymentCount(String m_id) {
		return orderRepository.getPaymentCount(m_id);
	}

	@Override
	public int getPaymentAmount(String m_id) {
		return orderRepository.getPaymentAmount(m_id);
	}

	@Override
	public ArrayList<Order> getDonationDetail(String c_id, String array) {
		return orderRepository.getDonationDetail(c_id, array);
	}

	@Override
	public int getExpected(String c_id) {
		return orderRepository.getExpected(c_id);
	}

	@Override
	public int getTotalDonation(String c_id) {
		return orderRepository.getTotalDonation(c_id);
	}

	@Override
	public ArrayList<Order> getDonationRank(String c_id) {
		return orderRepository.getDonationRank(c_id);
	}

}
