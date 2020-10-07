package org.payments.dtos.impl;

import org.payments.entities.Tariff;

public class TariffDTO {
    private int id;
    private String name;
    private double commission;

    @Override
    public String toString() {
        return "TariffDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commission=" + commission +
                '}';
    }

    public static Builder newBuilder(){
        return new TariffDTO().new Builder();
    }

    public class Builder{

        public Builder setId(int id) {
            TariffDTO.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            TariffDTO.this.name = name;
            return this;
        }

        public Builder setCommission(double commission) {
            TariffDTO.this.commission = commission;
            return this;
        }

        public TariffDTO build(){
            return TariffDTO.this;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
