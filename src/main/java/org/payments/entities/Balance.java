package org.payments.entities;

import java.util.ArrayList;
import java.util.List;

public class Balance {
    private int id;
    private double bonus_amount;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBonus_amount() {
        return bonus_amount;
    }

    public void setBonus_amount(double bonus_amount) {
        this.bonus_amount = bonus_amount;
    }
}
