package org.payments.repository.impl;


import org.payments.entities.Balance;
import org.payments.repository.BalanceRepository;
import org.payments.repository.DBRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class BalanceRepositoryImpl extends DBRepository implements BalanceRepository {
    @Override
    public Optional<Balance> getBalanceById(int id) {
        Balance balance = new Balance();
        String query = "select bonus_amount from balance where id=?;";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    balance.setBonus_amount(resultSet.getDouble(1));
                    balance.setId(id);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(balance);
    }



    @Override
    public void updateBalance(Balance balance) {
        String query = "UPDATE balance set bonus_amount=? where id=?;";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, balance.getBonus_amount());
            statement.setInt(2, balance.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getBalanceIdByCardNumber(String number){
        int balanceId = 0;
        String query = "select balance_id from cards where card_number=?;";
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, number);
            try(ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()){
                    balanceId = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return balanceId;
    }

    @Override
    public int createBalance() {
        int isCreated = 0;
        String query = "insert into balance() values();";
        try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.executeUpdate();
            try(ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()){
                    isCreated = resultSet.getInt(1);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isCreated;
    }
}
