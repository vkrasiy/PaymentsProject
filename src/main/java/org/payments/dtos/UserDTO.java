package org.payments.dtos;

import org.payments.dtos.impl.BalanceDTO;
import org.payments.entities.User;

import java.util.Objects;

public class UserDTO {
    private int id;
    private String login;
    private String first_name;
    private String last_name;
    private String email;
    private BalanceDTO balance;
    private String phone;

    public UserDTO(){

    }

    public static Builder newBuilder() {
        return new UserDTO().new Builder();
    }

    public class Builder{


        public Builder setId(int id) {
            UserDTO.this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            UserDTO.this.login = login;
            return this;
        }

        public Builder setFirst_name(String first_name) {
            UserDTO.this.first_name = first_name;
            return this;
        }

        public Builder setLast_name(String last_name) {
            UserDTO.this.last_name = last_name;
            return this;
        }

        public Builder setEmail(String email) {
            UserDTO.this.email = email;
            return this;
        }

        public Builder setBalanceId(BalanceDTO balanceId) {
            UserDTO.this.balance = balanceId;
            return this;
        }

        public Builder setPhone(String phone) {
            UserDTO.this.phone = phone;
            return this;
        }

        public UserDTO build(){
            return UserDTO.this;
        }
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id &&
                Objects.equals(login, userDTO.login) &&
                Objects.equals(first_name, userDTO.first_name) &&
                Objects.equals(last_name, userDTO.last_name) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(balance, userDTO.balance) &&
                Objects.equals(phone, userDTO.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, first_name, last_name, email, balance, phone);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BalanceDTO getBalance() {
        return balance;
    }

    public void setBalance(BalanceDTO balance) {
        this.balance = balance;
    }
}
