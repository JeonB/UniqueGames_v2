package com.uniqueGames.repository;

import com.uniqueGames.model.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
	private final SqlSession sqlSession;

	public OrderRepository(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/** Cart **/
	// getCartList
	public ArrayList<Order> getCartList(String mId) {
		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getCartList", mId);
		ArrayList<Order> cartList = new ArrayList<Order>();
		
		for (Object cart : oList) {
			cartList.add((Order) cart);
		}

		return cartList;
	} // getCartList

	// getCartCount
	public int getCartCount(String mId) {
		return sqlSession.selectOne("mapper.orderMapper.getCartCount", mId);
	} // getCartCount

	// getCartDeleteAll
	public int getCartDeleteAll(String mId) {
		return sqlSession.delete("mapper.orderMapper.getCartDeleteAll", mId);
	} // getCartDeleteAll

	// getCartDeleteOne
	public int getCartDeleteOne(int id) {
		return sqlSession.delete("mapper.orderMapper.getCartDeleteOne", id);
	} // getCartDeleteOne

	public void insertCart(Order order){
		sqlSession.insert("mapper.orderMapper.insertCart", order);
	}
	/** Order **/
	// getOrderList
	public ArrayList<Order> getOrderList(List<Integer> checkedList) {
		ArrayList<Order> orderList = new ArrayList<Order>();

		for (int i = 0; i < checkedList.size(); i++) {
			Order order = sqlSession.selectOne("mapper.orderMapper.getOrderList", checkedList.get(i));
			order.setRno(i + 1);
			orderList.add(order);
		}

		return orderList;
	} // getCartList

	// getOrderAmount
	public int getOrderAmount(List<Integer> checkedList) {
		int totalAmount = 0;

		for (int id : checkedList) {
			int amount = sqlSession.selectOne("mapper.orderMapper.getOrderAmount", id);
			totalAmount += amount;
		}

		return totalAmount;
	} // getOrderAmount

	// getOrderComplete
	public int getOrderComplete(List<Integer> checkedList, String method) {
		int result = 0;

		for (int i = 0; i < checkedList.size(); i++) {
			Map<String, String> param = new HashMap<String, String>();
			param.put("id", checkedList.get(i).toString());
			param.put("method", method);

			result = sqlSession.update("mapper.orderMapper.getOrderComplete", param);

			if (result == 0) {
				i = checkedList.size();
			}
		}

		return result;
	} // getOrderComplete

	// getPaymentDetail
	public ArrayList<Order> getPaymentDetail(String mId, String array) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("mId", mId);
		param.put("array", array);

		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getPaymentDetail", param);
		ArrayList<Order> paymentList = new ArrayList<Order>();

		for (Object payment : oList) {
			paymentList.add((Order) payment);
		}

		return paymentList;
	} // getPaymentDetail

	// getPaymentCount
	public int getPaymentCount(String mId) {
		return sqlSession.selectOne("mapper.orderMapper.getPaymentCount", mId);
	} // getPaymentCount

	// getPaymentAmount
	public int getPaymentAmount(String mId) {
		return sqlSession.selectOne("mapper.orderMapper.getPaymentAmount", mId);
	} // getPaymentAmount

	// getDonationDetail
	public ArrayList<Order> getDonationDetail(String cId, String array) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("cId", cId);
		param.put("array", array);

		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getDonationDetail", param);
		ArrayList<Order> donationList = new ArrayList<Order>();

		for (Object donation : oList) {
			donationList.add((Order) donation);
		}

		return donationList;
	} // getDonationDetail

	// getExpected
	public int getExpected(String cId) {
		return sqlSession.selectOne("mapper.orderMapper.getExpected", cId);
	} // getExpected

	// getTotalDonation
	public int getTotalDonation(String cId) {
		return sqlSession.selectOne("mapper.orderMapper.getTotalDonation", cId);
	} // getTotalDonation

	// getDonationRank
	public ArrayList<Order> getDonationRank(String cId) {
		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getDonationRank", cId);
		ArrayList<Order> rankList = new ArrayList<Order>();

		for (Object donator : oList) {
			rankList.add((Order) donator);
		}

		return rankList;
	} // getDonationRank
}
