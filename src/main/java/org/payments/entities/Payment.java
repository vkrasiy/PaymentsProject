package org.payments.entities;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private String recipientCardNumber;
    private int senderCardId;
    private double amount;
    private String description;
    private boolean isAccepted;
    private LocalDateTime acceptingDate;

    public static Builder newBuilder(){
        return new Payment().new Builder();
    }

    public class Builder{

        public Builder setId(int id) {
            Payment.this.id = id;
            return this;
        }

        public Builder setRecipientCardNumber(String recipientCardNumber) {
            Payment.this.recipientCardNumber = recipientCardNumber;
            return this;
        }

        public Builder setSenderCardId(int senderCardId) {
            Payment.this.senderCardId = senderCardId;
            return this;
        }

        public Builder setAmount(double amount) {
            Payment.this.amount = amount;
            return this;
        }

        public Builder setDescription(String description) {
            Payment.this.description = description;
            return this;
        }

        public Builder setAccepted(boolean accepted) {
            Payment.this.isAccepted = accepted;
            return this;
        }

        public Builder setAcceptingDate(LocalDateTime acceptingDate) {
            Payment.this.acceptingDate = acceptingDate;
            return this;
        }

        public Payment build(){
            return Payment.this;
        }
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", recipientCardNumber='" + recipientCardNumber + '\'' +
                ", senderCardId=" + senderCardId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", isAccepted=" + isAccepted +
                ", acceptingDate=" + acceptingDate +
                '}';
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

    public int getSenderCardId() {
        return senderCardId;
    }

    public void setSenderCardId(int senderCardId) {
        this.senderCardId = senderCardId;
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

    public void setAcceptingDate(LocalDateTime acceptingDate) {
        this.acceptingDate = acceptingDate;
    }
}
