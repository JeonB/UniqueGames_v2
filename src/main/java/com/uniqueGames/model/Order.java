package com.uniqueGames.model;

import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Setter
@Getter
public class Order {
    // Field
    String orderDate, method, paymentStatus, gametitle, gameImg, userId, mId, cId, amountStr;
    int id, gId, amount, rno;

    // Constructor
    public Order() {
    }

    public Order(String mId, String cId, int gId, int amount) {
        this.mId = mId;
        this.cId = cId;
        this.gId = gId;
        this.amount = amount;
    }

    // Method
    public String getAmountStr() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(amount);
    }

}
