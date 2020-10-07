package org.payments.repository.impl;

import org.payments.entities.Card;
import org.payments.repository.CardRepository;
import org.payments.repository.DBRepository;
import org.payments.util.EntityExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepositoryImpl extends DBRepository implements CardRepository, EntityExtractor<Card> {

    @Override
    public Optional<Card> getCard(int id) {
        Card card = null;
        String query = "Select balance_id,  tarrif_id, amount, card_number, CVV, MM_YY, card_status_id, id from cards where id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    card = extractEntity(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(card);
    }

    @Override
    public List<Optional<Card>> getCardsByBalanceId(int id) {
        List<Optional<Card>> cards = new ArrayList<>();
        String query = "Select  balance_id, tarrif_id, amount, card_number, CVV, MM_YY, card_status_id, id from cards where balance_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cards.add(Optional.of(extractEntity(resultSet)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    @Override
    public int addNewCard(Card card) {
        int cardId = 0;

        String query = "insert into cards(balance_id, amount, tarrif_id, card_number, CVV, MM_YY) values(?,?,?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, card.getBalanceId());
            statement.setDouble(2, card.getAmount());
            statement.setInt(3, card.getTariffId());
            statement.setString(4, card.getCardNumber());
            statement.setString(5, card.getCVV());
            statement.setString(6, card.getMM_YY());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    cardId = resultSet.getInt(1);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(cardId);
        return cardId;
    }

    @Override
    public boolean updateCardBalance(Card card) {
        System.out.println(card);
        String query = "Update cards set amount=amount+? where id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, card.getAmount());
            statement.setInt(2, card.getId());
            statement.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void deleteCard(Card card) {
        String query = "delete from users where id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, card.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean existsCardWithSuchNumber(String cardNumber) {
        boolean doesExist = false;
        String query = "select exists(select * from users where login=?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    doesExist = resultSet.getInt(1) > 0;
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return doesExist;
    }

    @Override
    public int getCardIdByNumber(String cardNumber) {
        int balanceId = 0;
        String query = "select id from cards where card_number=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    balanceId = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return balanceId;
    }

    @Override
    public String getCVVByCardNumber(String cardNumber) {
        String CVV = null;
        String query = "select CVV from cards where card_number=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CVV = resultSet.getString(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return CVV;
    }

    @Override
    public String getMM_YYByCardNumber(String cardNumber) {
        String MM_YY = null;
        String query = "select MM_YY from cards where card_number=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, cardNumber);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    MM_YY = resultSet.getString(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return MM_YY;
    }


    @Override
    public Card extractEntity(ResultSet resultSet) throws SQLException {
        return Card.newBuilder()
                .setBalanceId(resultSet.getInt(1))
                .setAmount(resultSet.getDouble(3))
                .setTariffId(resultSet.getInt(2))
                .setCardNumber(resultSet.getString(4))
                .setCVV(resultSet.getString(5))
                .setMM_YY(resultSet.getString(6))
                .setActive(resultSet.getInt(7) > 0)
                .setId(resultSet.getInt(8))
                .build();


    }
}
