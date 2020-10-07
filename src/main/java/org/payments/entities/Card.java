package org.payments.entities;

public class Card {
    private int id;
    private int balanceId;
    private double amount;
    private String cardNumber;
    private boolean isActive;
    private String CVV;
    private String MM_YY;
    private int tariffId;

    public static Builder newBuilder(){
        return new Card().new Builder();
    }

    public class Builder{

        public Builder setId(int id) {
            Card.this.id = id;
            return this;
        }

        public Builder setBalanceId(int balanceId) {
            Card.this.balanceId = balanceId;
            return this;
        }

        public Builder setAmount(double amount) {
            Card.this.amount = amount;
            return this;
        }

        public Builder setCardNumber(String cardNumber) {
            Card.this.cardNumber = cardNumber;
            return this;
        }

        public Builder setActive(boolean active) {
            Card.this.isActive = active;
            return this;
        }

        public Builder setCVV(String CVV) {
            Card.this.CVV = CVV;
            return this;
        }

        public Builder setMM_YY(String MM_YY) {
            Card.this.MM_YY = MM_YY;
            return this;
        }

        public Builder setTariffId(int tariffId) {
            Card.this.tariffId = tariffId;
            return this;
        }

        public Card build(){
            return Card.this;
        }
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getMM_YY() {
        return MM_YY;
    }

    public void setMM_YY(String MM_YY) {
        this.MM_YY = MM_YY;
    }

    public int getTariffId() {
        return tariffId;
    }

    public void setTariffId(int tariffId) {
        this.tariffId = tariffId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", balanceId=" + balanceId +
                ", amount=" + amount +
                ", cardNumber='" + cardNumber + '\'' +
                ", isActive=" + isActive +
                ", CVV='" + CVV + '\'' +
                ", MM_YY='" + MM_YY + '\'' +
                ", tariffId=" + tariffId +
                '}';
    }
}
