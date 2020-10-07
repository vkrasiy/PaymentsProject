package org.payments.dtos.impl;

import java.time.LocalDateTime;

public class PaymentDTO {
    private int id;
    private String recipientCardNumber;
    private CardDTO senderCard;
    private double amount;
    private String description;
    private boolean isAccepted;
    private LocalDateTime acceptingDate;

    public static Builder newBuilder(){
        return new PaymentDTO().new Builder();
    }


    public class Builder{

        public Builder setId(int id) {
            PaymentDTO.this.id = id;
            return this;
        }

        public Builder setRecipientCardNumber(String recipientCardNumber) {
            PaymentDTO.this.recipientCardNumber = recipientCardNumber;
            return this;
        }

        public Builder setSenderCard(CardDTO senderCard) {
            PaymentDTO.this.senderCard = senderCard;
            return this;
        }

        public Builder setAmount(double amount) {
            PaymentDTO.this.amount = amount;
            return this;
        }

        public Builder setDescription(String description) {
            PaymentDTO.this.description = description;
            return this;
        }

        public Builder setAccepted(boolean accepted) {
            PaymentDTO.this.isAccepted = accepted;
            return this;
        }

        public Builder setAcceptingDate(LocalDateTime acceptingDate) {
            PaymentDTO.this.acceptingDate = acceptingDate;
            return this;
        }

        public PaymentDTO build(){
            return PaymentDTO.this;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipientCardNumber() {
        return recipientCardNumber;
    }

    public void setRecipientCardNumber(String recipientCardNumber) {
        this.recipientCardNumber = recipientCardNumber;
    }

    public CardDTO getSenderCard() {
        return senderCard;
    }

    public void setSenderCard(CardDTO senderCard) {
        this.senderCard = senderCard;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public LocalDateTime getAcceptingDate() {
        return acceptingDate;
    }

    public PaymentDTO(){
        
    }

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "id=" + id +
                ", recipientCardNumber='" + recipientCardNumber + '\'' +
                ", senderCard=" + senderCard +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", isAccepted=" + isAccepted +
                ", acceptingDate=" + acceptingDate +
                '}';
    }

}
