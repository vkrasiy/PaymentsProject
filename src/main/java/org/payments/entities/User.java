package org.payments.entities;


import java.util.Objects;

public class User{
    private int id;
    private String login;
    private String password;
    private String first_name;
    private String last_name;
    private String email;
    private int balanceId;
    private String phone;


    public void setBalanceId(int balanceId) {
        this.balanceId = balanceId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Builder newBuilder() {
        return new User().new Builder();
    }

    public class Builder{


        public Builder setId(int id) {
            User.this.id = id;
            return this;
        }

        public Builder setLogin(String login) {
            User.this.login = login;
            return this;
        }

        public Builder setPassword(String password) {
            User.this.password = password;
            return this;
        }

        public Builder setFirst_name(String first_name) {
            User.this.first_name = first_name;
            return this;
        }

        public Builder setLast_name(String last_name) {
            User.this.last_name = last_name;
            return this;
        }

        public Builder setEmail(String email) {
            User.this.email = email;
            return this;
        }

        public Builder setBalanceId(int balanceId) {
            User.this.balanceId = balanceId;
            return this;
        }

        public Builder setPhone(String phone) {
            User.this.phone = phone;
            return this;
        }

        public User build(){
            return User.this;
        }
    }

    public int getId() {
        return id;
    }




    @Override
    public String toString() {
        return "User{" +
                "userId='" + id + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", balanceId='" + balanceId + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(first_name, user.first_name) &&
                Objects.equals(last_name, user.last_name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(balanceId, user.balanceId) &&
                Objects.equals(phone, user.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, first_name, last_name, email, balanceId, phone);
    }

    public String getPhone() {
        return phone;
    }


    public String getLogin() {
        return login;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public int getBalanceId() {
        return balanceId;
    }


    public String getPassword() {
        return password;
    }
}
