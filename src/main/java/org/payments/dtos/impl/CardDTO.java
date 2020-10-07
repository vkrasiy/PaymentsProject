package org.payments.dtos.impl;

public class CardDTO {
    private int id;
    private double amount;
    private String cardNumber;
    private boolean isActive;
    private TariffDTO tariff;
    public CardDTO(){}

    public static Builder newBuilder(){
        return new CardDTO().new Builder();
    }

    public class Builder{

        public Builder setId(int id) {
            CardDTO.this.id = id;
            return this;
        }

        public Builder  setAmount(double amount) {
            CardDTO.this.amount = amount;
            return this;
        }

        public Builder setCardNumber(String cardNumber) {
            CardDTO.this.cardNumber = cardNumber;
            return this;
        }

        public Builder setActive(boolean active) {
            CardDTO.this.isActive = active;
            return this;
        }

        public Builder setTariff(TariffDTO tariff) {
            CardDTO.this.tariff = tariff;
            return this;
        }

        public CardDTO build(){
            return CardDTO.this;
        }
    }

    @Override
    public String toString() {
        return "CardDTO{" +
                "id=" + id +
                ", amount=" + amount +
                ", cardNumber='" + cardNumber + '\'' +
                ", isActive=" + isActive +
                ", tariff=" + tariff +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public TariffDTO getTariff() {
        return tariff;
    }

    public void setTariff(TariffDTO tariff) {
        this.tariff = tariff;
    }
}
