package org.payments.entities;

public class Tariff {
    private int id;
    private String name;
    private double commission;

    public static Builder newBuilder(){
        return new Tariff().new Builder();
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", commission=" + commission +
                '}';
    }

    public class Builder{

        public Builder setId(int id) {
            Tariff.this.id = id;
            return this;
        }

        public Builder setName(String name) {
            Tariff.this.name = name;
            return this;
        }

        public Builder setCommission(double commission) {
            Tariff.this.commission = commission;
            return this;
        }

        public Tariff build(){
            return Tariff.this;
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
