package com.uniqueGames.service;

import com.uniqueGames.model.Game;
import com.uniqueGames.model.Order;
import com.uniqueGames.model.Payment;
import com.uniqueGames.repository.GameMapper;
import com.uniqueGames.repository.OrderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Repository
@Service
public class OrderService {
    private final OrderMapper orderMapper;
    private final GameMapper gameMapper;
    @Autowired
    public OrderService(OrderMapper orderMapper, GameMapper gameMapper) {
        this.orderMapper = orderMapper;
        this.gameMapper = gameMapper;
    }


    public static String formatCurrency(int amount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(amount);
    }

    // Cart
    public ArrayList<Order> getCartList(String mId) {
        ArrayList<Order> oList = new ArrayList<>();
        for (Order order : orderMapper.getCartList(mId)) {
            oList.add(order);
        }
        return oList;
    }

    public int getCartCount(String mId) {
        return orderMapper.getCartCount(mId);
    }

    public int getCartDeleteAll(String mId) {
        return orderMapper.getCartDeleteAll(mId);
    }

    public int getCartDeleteOne(int id) {
        return orderMapper.getCartDeleteOne(id);
    }

    public int insertCart(Order order) {
        return orderMapper.insertCart(order);
    }

    public Order addToOrderVo(String m_id, String c_id, int g_id, int amount) {
        return new Order(m_id, c_id, g_id, amount);
    }

    // Order
    public String listToString(ArrayList<Integer> idList) {
        StringBuilder sb = new StringBuilder();
        for (int id : idList) {
            sb.append(id);
            if (id != idList.get(idList.size() - 1)) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public ArrayList<Order> getOrderList(String idStr) {
        ArrayList<Order> oList = new ArrayList<>();
        for (Order order : orderMapper.getOrderList(idStr)) {
            oList.add(order);
        }
        return oList;
    }

    public int getOrderAmount(String idStr) {
        return orderMapper.getOrderAmount(idStr);
    }

    public int getOrderComplete(String idStr) {
        return orderMapper.getOrderComplete(idStr);
    }

    // Details
    public ArrayList<Order> getPaymentDetail(String mId, String order1, String order2, int start, int end) {
        ArrayList<Order> oList = new ArrayList<>();
        for (Order order : orderMapper.getPaymentDetail(mId, order1, order2, start, end)) {
            oList.add(order);
        }
        return oList;
    }

    public int getPaymentCount(String mId) {
        return orderMapper.getPaymentCount(mId);
    }

    public int getPaymentAmount(String mId) {
        return orderMapper.getPaymentAmount(mId);
    }

    public ArrayList<Order> getDonationDetail(String cId, String order1, String order2, int start, int end) {
        ArrayList<Order> oList = new ArrayList<>();
        for (Order order : orderMapper.getDonationDetail(cId, order1, order2, start, end)) {
            oList.add(order);
        }
        return oList;
    }

    public int getExpected(String cId) {
        return orderMapper.getExpected(cId);
    }

    public int getTotalDonation(String cId) {
        return orderMapper.getTotalDonation(cId);
    }

    public ArrayList<Order> getDonationRank(String cId) {
        ArrayList<Order> oList = new ArrayList<>();
        for (Order order : orderMapper.getDonationRank(cId)) {
            oList.add(order);
        }
        return oList;
    }

    public String[] splitString(String input) {
        String[] result = {"", ""};
        int index = input.lastIndexOf("_");

        result[0] = input.substring(0, index);
        result[1] = input.substring(index + 1);

        return result;
    }

    public int totRowCountMember(String id) {
        return orderMapper.totRowCountMember(id);
    }

    public int totRowCountCompany(String id) {
        return orderMapper.totRowCountCompany(id);
    }

    public ArrayList<String> aGetYearList() {
        return orderMapper.aGetYearList();
    }

    public ArrayList<Payment> aGetDonationList(String order1, String order2, Integer startCount, Integer endCount) {
        return orderMapper.aGetDonationList(order1, order2, startCount, endCount);
    }


    public ArrayList<Order> addGameInfo(ArrayList<Order> cartList) {
        for (int i = 0; i < cartList.size(); i++) {
            int gid = cartList.get(i).getGId();
            Game game = gameMapper.getGameForIndex(gid);
            game.setUploadImg("../images/" + gameMapper.getOneFile(gid).getUploadImg());

            cartList.get(i).setGameImg(game.getUploadImg());
            cartList.get(i).setGametitle(game.getName());
        }

        return cartList;
    }
    public int totRowCountAdmin() {
        return orderMapper.totRowCountAdmin();
    }
}

