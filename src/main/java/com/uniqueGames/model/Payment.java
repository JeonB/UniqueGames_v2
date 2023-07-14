package com.uniqueGames.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.text.DecimalFormat;

@Getter
@Setter
@Data
public class Payment {
    int rno, amount;
    String company, game, amountStr;

    public String getAmountStr() {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(amount);
    }
}
