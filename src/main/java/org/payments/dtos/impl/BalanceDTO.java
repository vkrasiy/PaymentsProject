package org.payments.dtos.impl;

import java.util.ArrayList;
import java.util.List;

public class BalanceDTO {
    private int id;
    private double bonus_amount;
    private List<CardDTO> cards;

    public static Builder newBuilder(){
        return new BalanceDTO().new Builder();
    }

    public class Builder{
        public Builder setId(int id) {
            BalanceDTO.this.id = id;
            return this;
        }

        public Builder setBonus_amount(double bonus_amount) {
            BalanceDTO.this.bonus_amount = bonus_amount;
            return this;
        }

        public BalanceDTO build(){
            return BalanceDTO.this;
        }
    }


    public BalanceDTO() {
        this.cards = new ArrayList<>();
    }

    public void addCard(CardDTO card){
        cards.add(card);
    }

    public void addAllCard(List<CardDTO> cardsToAdd){
        cards.addAll(cardsToAdd);
    }


    public List<CardDTO> getCards() {
        return cards;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BalanceDTO{" +
                "id=" + id +
                ", bonus_amount=" + bonus_amount +
                ", cards=" + cards +
                '}';
    }
}
