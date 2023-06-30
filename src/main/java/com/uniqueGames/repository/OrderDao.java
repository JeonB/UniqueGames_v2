package com.uniqueGames.repository;

import com.uniqueGames.model.OrderVo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao{
	private final SqlSession sqlSession;

	public OrderDao(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	/** Cart **/
	// getCartList
	public ArrayList<OrderVo> getCartList(String m_id) {
		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getCartList", m_id);
		ArrayList<OrderVo> cartList = new ArrayList<OrderVo>();
		
		for (Object cart : oList) {
			cartList.add((OrderVo) cart);
		}

		return cartList;
	} // getCartList

	// getCartCount
	public int getCartCount(String m_id) {
		return sqlSession.selectOne("mapper.orderMapper.getCartCount", m_id);
	} // getCartCount

	// getCartDeleteAll
	public int getCartDeleteAll(String m_id) {
		return sqlSession.delete("mapper.orderMapper.getCartDeleteAll", m_id);
	} // getCartDeleteAll

	// getCartDeleteOne
	public int getCartDeleteOne(int id) {
		return sqlSession.delete("mapper.orderMapper.getCartDeleteOne", id);
	} // getCartDeleteOne

	public void insertCart(OrderVo orderVo){
		sqlSession.insert("mapper.orderMapper.insertCart", orderVo);
	}
	/** Order **/
	// getOrderList
	public ArrayList<OrderVo> getOrderList(List<Integer> checkedList) {
		ArrayList<OrderVo> orderList = new ArrayList<OrderVo>();

		for (int i = 0; i < checkedList.size(); i++) {
			OrderVo order = sqlSession.selectOne("mapper.orderMapper.getOrderList", checkedList.get(i));
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
	public ArrayList<OrderVo> getPaymentDetail(String m_id, String array) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("m_id", m_id);
		param.put("array", array);

		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getPaymentDetail", param);
		ArrayList<OrderVo> paymentList = new ArrayList<OrderVo>();

		for (Object payment : oList) {
			paymentList.add((OrderVo) payment);
		}

		return paymentList;
	} // getPaymentDetail

	// getPaymentCount
	public int getPaymentCount(String m_id) {
		return sqlSession.selectOne("mapper.orderMapper.getPaymentCount", m_id);
	} // getPaymentCount

	// getPaymentAmount
	public int getPaymentAmount(String m_id) {
		return sqlSession.selectOne("mapper.orderMapper.getPaymentAmount", m_id);
	} // getPaymentAmount

	// getDonationDetail
	public ArrayList<OrderVo> getDonationDetail(String c_id, String array) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("c_id", c_id);
		param.put("array", array);

		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getDonationDetail", param);
		ArrayList<OrderVo> donationList = new ArrayList<OrderVo>();

		for (Object donation : oList) {
			donationList.add((OrderVo) donation);
		}

		return donationList;
	} // getDonationDetail

	// getExpected
	public int getExpected(String c_id) {
		return sqlSession.selectOne("mapper.orderMapper.getExpected", c_id);
	} // getExpected

	// getTotalDonation
	public int getTotalDonation(String c_id) {
		return sqlSession.selectOne("mapper.orderMapper.getTotalDonation", c_id);
	} // getTotalDonation

	// getDonationRank
	public ArrayList<OrderVo> getDonationRank(String c_id) {
		List<Object> oList = sqlSession.selectList("mapper.orderMapper.getDonationRank", c_id);
		ArrayList<OrderVo> rankList = new ArrayList<OrderVo>();

		for (Object donator : oList) {
			rankList.add((OrderVo) donator);
		}

		return rankList;
	} // getDonationRank
}
