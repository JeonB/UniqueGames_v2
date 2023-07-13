package com.uniqueGames.repository;

import com.uniqueGames.model.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    // Cart
    @Select("SELECT ID, G_ID, GAME_IMG, GAMETITLE, AMOUNT FROM ORDERS WHERE PAYMENT_STATUS = 'NOT' AND M_ID = #{mId}")
    List<Order> getCartList(String mId);

    @Select("SELECT COUNT(G_ID) FROM ORDERS WHERE PAYMENT_STATUS = 'NOT' AND M_ID = #{mId}")
    int getCartCount(String mId);

    @Delete("DELETE FROM ORDERS WHERE PAYMENT_STATUS = 'NOT' AND M_ID = #{mId}")
    int getCartDeleteAll(String mId);

    @Delete("DELETE FROM ORDERS WHERE ID = #{id}")
    int getCartDeleteOne(int id);

    @Insert("INSERT INTO ORDERS( M_ID, C_ID, G_ID, AMOUNT,GAMETITLE, GAME_IMG) VALUES (#{mId},#{cId},#{gId},#{amount},#{gametitle},#{gameImg})")
    int insertCart(Order order);

	// Order
    @Select("SELECT * FROM ORDERS WHERE ID IN (${idList})")
    List<Order> getOrderList(@Param("idList") String idList);

    @Select("SELECT SUM(AMOUNT) FROM ORDERS WHERE ID IN (${idList})")
    int getOrderAmount(@Param("idList") String idList);

    @Update("UPDATE ORDERS SET PAYMENT_STATUS = 'COMPLETE', ORDER_DATE = NOW(), METHOD = ${method} WHERE ID IN (${idList})")
    int getOrderComplete(@Param("idStr") String idStr, @Param("method") String method);

	// Details
    @Select("SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, DATE_FORMAT(ORDER_DATE, '%y-%m-%d') ORDER_DATE, GAMETITLE, AMOUNT FROM ORDERS WHERE M_ID = ${mId} AND PAYMENT_STATUS = 'COMPLETE'")
    List<Order> getPaymentDetail(@Param("order1") String order1, @Param("order2") String order2, @Param("mId") String mId);

    @Select("SELECT IFNULL(COUNT(*), 0) COUNT FROM ORDERS WHERE M_ID = #{mId} AND PAYMENT_STATUS = 'COMPLETE'")
    int getPaymentCount(String mId);

    @Select("SELECT IFNULL(SUM(AMOUNT), 0) PAYMENT_AMOUNT FROM ORDERS WHERE M_ID = #{mId} AND PAYMENT_STATUS = 'COMPLETE'")
    int getPaymentAmount(String mId);

    @Select("SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, DATE_FORMAT(ORDER_DATE, '%y-%m') ORDER_DATE, GAMETITLE, SUM(AMOUNT) AMOUNT FROM ORDERS WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE' GROUP BY DATE_FORMAT(ORDER_DATE, '%y-%m'), GAMETITLE")
    List<Order> getDonationDetail(@Param("order1") String order1, @Param("order2") String order2, @Param("cId") String cId);

    @Select("SELECT IFNULL(SUM(AMOUNT), 0) AMOUNT FROM ORDERS WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE' AND DATE_FORMAT(ORDER_DATE, '%y-%m') = DATE_FORMAT(NOW(), '%y-%m')")
    int getExpected(String cId);

    @Select("SELECT IFNULL(SUM(AMOUNT), 0) TOTAL_AMOUNT FROM ORDERS WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE'")
    int getTotalDonation(String cId);

    @Select("SELECT ROW_NUMBER() OVER(ORDER BY SUM(AMOUNT) DESC) AS RNO, M.MEMBER_ID USERID, O.GAMETITLE, SUM(AMOUNT) FROM ORDERS AS O INNER JOIN MEMBER AS M ON O.M_ID = M.ID WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE' GROUP BY M.MEMBER_ID, O.GAMETITLE")
    List<Order> getDonationRank(String cId);
}
