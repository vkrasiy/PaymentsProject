package org.payments.repository.impl;

import org.payments.entities.User;
import org.payments.repository.DBRepository;
import org.payments.repository.UserRepository;
import org.payments.util.EntityExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;


public class UserRepositoryImpl extends DBRepository implements UserRepository, EntityExtractor<User> {
    @Override
    public Optional<User> getUser(int id) {
        User user = null;
        String query = "select first_name, last_name, email, phone_number, account_status_id, balance_id, id from users where isAdmin=0 and id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                user = extractEntity(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(user);
    }

    @Override
    public int addUser(User user) {
        int userId = 0;
        System.out.println(user);
        String query = "Insert into users( login, password, first_name, last_name, email, phone_number, balance_id) values( ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirst_name());
            statement.setString(4, user.getLast_name());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getBalanceId());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    userId = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userId;
    }

    @Override
    public void deleteUser(String userId) {
        String query = "delete from users where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String query = "update users set login=?, password=?, first_name=?, last_name=?, email=?, phone_number=?, balance_id=? where id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirst_name());
            statement.setString(4, user.getLast_name());
            statement.setString(5, user.getEmail());
            statement.setString(6, user.getPhone());
            statement.setInt(7, user.getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User getUser(String login, String pass, boolean isAdmin) {
        User user = null;
        System.out.println(login + " : " +pass +" : "+isAdmin);
        String query = "select first_name, last_name, email, phone_number, account_status_id, balance_id, id from users where  login=? and password=? and isAdmin=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(3, isAdmin ? 1 : 0);
            statement.setString(2, pass);
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                user = extractEntity(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean existsUserWithSuchLogin(String login) {
        boolean exists = false;
        String query = "select exists(select * from users where login=?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    exists = resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return exists;
    }

    @Override
    public User extractEntity(ResultSet resultSet) throws SQLException {
        User user = null;
        while (resultSet.next()) {
            user = User.newBuilder()
                    .setFirst_name(resultSet.getString(1))
                    .setLast_name(resultSet.getString(2))
                    .setEmail(resultSet.getString(3))
                    .setLogin(resultSet.getString(4))
                    .setPhone(resultSet.getString(5))
                    .setBalanceId(resultSet.getInt(6))
                    .setId(resultSet.getInt(7)).build();

        }
        return user;
    }
}
