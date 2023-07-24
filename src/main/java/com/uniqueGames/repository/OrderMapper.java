package com.uniqueGames.repository;

import com.uniqueGames.model.Order;
import com.uniqueGames.model.Payment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    // Cart
    @Select("SELECT ID, G_ID, AMOUNT FROM TB_ORDER WHERE PAYMENT_STATUS = 'NOT' AND M_ID = #{mId}")
    List<Order> getCartList(String mId);

    @Select("SELECT COUNT(G_ID) FROM TB_ORDER WHERE PAYMENT_STATUS = 'NOT' AND M_ID = #{mId}")
    int getCartCount(String mId);

    @Delete("DELETE FROM TB_ORDER WHERE PAYMENT_STATUS = 'NOT' AND M_ID = #{mId}")
    int getCartDeleteAll(String mId);

    @Delete("DELETE FROM TB_ORDER WHERE ID = #{id}")
    int getCartDeleteOne(int id);

    @Insert("INSERT INTO TB_ORDER( M_ID, C_ID, G_ID, AMOUNT) VALUES (#{mId},#{cId},#{gId},#{amount})")
    int insertCart(Order order);

    // Order
    @Select("SELECT * FROM TB_ORDER WHERE ID IN (${idList})")
    List<Order> getOrderList(@Param("idList") String idList);

    @Select("SELECT SUM(AMOUNT) FROM TB_ORDER WHERE ID IN (${idList})")
    int getOrderAmount(@Param("idList") String idList);

    @Update("UPDATE TB_ORDER SET PAYMENT_STATUS = 'COMPLETE', ORDER_DATE = NOW() WHERE ID IN (${idStr})")
    int getOrderComplete(@Param("idStr") String idStr);

    // Details
    @Select("SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY ${order1} ${order2}) AS RNO, " +
            "DATE_FORMAT(ORDER_DATE, '%y-%m-%d') AS ORDER_DATE, AMOUNT FROM TB_ORDER WHERE M_ID = #{mId} AND " +
            "PAYMENT_STATUS = 'COMPLETE') AS TB1 WHERE RNO BETWEEN ${start} AND ${end}")
    List<Order> getPaymentDetail(String mId, @Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT IFNULL(COUNT(*), 0) COUNT FROM TB_ORDER WHERE M_ID = #{mId} AND PAYMENT_STATUS = 'COMPLETE'")
    int getPaymentCount(String mId);

    @Select("SELECT IFNULL(SUM(AMOUNT), 0) PAYMENT_AMOUNT FROM TB_ORDER WHERE M_ID = #{mId} AND PAYMENT_STATUS = 'COMPLETE'")
    int getPaymentAmount(String mId);

    @Select("SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY ${order1} ${order2}) AS RNO, ORDER_DATE, AMOUNT " +
            "FROM (SELECT DATE_FORMAT(ORDER_DATE, '%y-%m') AS ORDER_DATE,  SUM(AMOUNT) AS AMOUNT FROM TB_ORDER " +
            "WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE' GROUP BY DATE_FORMAT(ORDER_DATE, '%y-%m'))AS TB1) AS TB2 " +
            "WHERE RNO BETWEEN ${start} AND ${end}")
    List<Order> getDonationDetail(String cId, @Param("order1") String order1, @Param("order2") String order2, @Param("start") int start, @Param("end") int end);

    @Select("SELECT IFNULL(SUM(AMOUNT), 0) AMOUNT FROM TB_ORDER WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE' " +
            "AND DATE_FORMAT(ORDER_DATE, '%y-%m') = DATE_FORMAT(NOW(), '%y-%m')")
    int getExpected(String cId);

    @Select("SELECT IFNULL(SUM(AMOUNT), 0) TOTAL_AMOUNT FROM TB_ORDER WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE'")
    int getTotalDonation(String cId);

    @Select("SELECT ROW_NUMBER() OVER(ORDER BY SUM(AMOUNT) DESC) AS RNO, M.MEMBER_ID USERID,  SUM(AMOUNT) " +
            "FROM TB_ORDER AS O INNER JOIN MEMBER AS M ON O.M_ID = M.ID WHERE C_ID = #{cId} AND PAYMENT_STATUS = 'COMPLETE' " +
            "GROUP BY M.MEMBER_ID")
    List<Order> getDonationRank(String cId);

    @Select("SELECT COUNT(*) FROM TB_ORDER WHERE M_ID = #{id} AND PAYMENT_STATUS = 'COMPLETE'")
    int totRowCountMember(String id);

    @Select("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM TB_ORDER WHERE C_ID='test' AND PAYMENT_STATUS='COMPLETE' " +
            "GROUP BY DATE_FORMAT(ORDER_DATE, '%y-%m')) AS TB1")
    int totRowCountCompany(String id);

    @Select("SELECT DISTINCT(DATE_FORMAT(ORDER_DATE, '%Y')) AS ORDER_DATE FROM TB_ORDER")
    ArrayList<String> aGetYearList();

    @Select("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM TB_ORDER WHERE DATE_FORMAT(ORDER_DATE, '%Y')=#{year} AND " +
            "DATE_FORMAT(ORDER_DATE, '%c')=${month} AND PAYMENT_STATUS = 'COMPLETE' GROUP BY G_ID) AS TB1")
    int aTotRowCountDonationBothSelected(@Param("year") String year, @Param("month") String month);

    @Select("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM TB_ORDER WHERE DATE_FORMAT(ORDER_DATE, '%Y')=#{month} " +
            "AND PAYMENT_STATUS = 'COMPLETE' GROUP BY DATE_FORMAT(ORDER_DATE, '%c'),G_ID) AS TB1")
    int aTotRowCountDonationYearSelected(String year);

    @Select("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM TB_ORDER WHERE DATE_FORMAT(ORDER_DATE, '%c')=#{month} " +
            "AND PAYMENT_STATUS = 'COMPLETE' GROUP BY DATE_FORMAT(ORDER_DATE, '%Y'),G_ID) AS TB1")
    int aTotRowCountDonationMonthSelected(String month);

    @Select("SELECT COUNT(*) FROM (SELECT COUNT(*) FROM TB_ORDER WHERE PAYMENT_STATUS = 'COMPLETE' " +
            "GROUP BY DATE_FORMAT(ORDER_DATE, '%Y'), DATE_FORMAT(ORDER_DATE, '%c'), G_ID) AS TB1")
    int aTotRowCountDonationAll();

    @Select({
            "SELECT * FROM (",
            "SELECT ROW_NUMBER() OVER (ORDER BY ${order1} ${order2}) AS RNO, GAME, COMPANY, AMOUNT, ORDER_DATE",
            "FROM (",
            "SELECT TB_GAME.NAME AS GAME, TB_COMPANY.NAME AS COMPANY, SUM(AMOUNT) AS AMOUNT, DATE_FORMAT(ORDER_DATE, '%y-%m') AS ORDER_DATE",
            "FROM TB_ORDER AS TB_ORDER",
            "JOIN GAME AS TB_GAME ON TB_ORDER.G_ID = TB_GAME.ID",
            "JOIN COMPANY AS TB_COMPANY ON TB_ORDER.C_ID = TB_COMPANY.COMPANY_ID",
            "WHERE PAYMENT_STATUS = 'COMPLETE'",
            "GROUP BY TB_GAME.NAME, TB_COMPANY.NAME, DATE_FORMAT(ORDER_DATE, '%y-%m') ) AS TB1",
            ") AS TB2 WHERE RNO BETWEEN #{startCount} AND #{endCount}"
    })
    ArrayList<Payment> aGetDonationList(@Param("order1") String order1, @Param("order2") String order2,
                                        @Param("startCount") Integer startCount, @Param("endCount") Integer endCount);

}
