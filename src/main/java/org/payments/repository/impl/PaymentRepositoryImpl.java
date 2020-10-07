package org.payments.repository.impl;

import org.payments.dtos.impl.PaymentDTO;
import org.payments.entities.Payment;
import org.payments.repository.DBRepository;
import org.payments.repository.PaymentRepository;
import org.payments.util.EntityExtractor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentRepositoryImpl extends DBRepository implements PaymentRepository, EntityExtractor<Payment> {
    private final int pageTotal = 5;

    @Override
    public Optional<Payment> getPayment(int id) {
        Payment payment = null;
        String query = "select recipient_card_number, sender_card_id, amount, description, status_id, accepting_date, id from payments where id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    payment = extractEntity(resultSet);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.of(payment);
    }

    @Override
    public List<Optional<Payment>> getAllPaymentsByCard(String senderCardId) {
        List<Optional<Payment>> payments = new ArrayList<>();
        String query = "select  recipient_card_number, sender_card_id, amount, description, status_id, accepting_date, id from payments where sender_card_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, senderCardId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    payments.add(Optional.of(extractEntity(resultSet)));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return payments;
    }

    @Override
    public int addPayment(Payment payment) {
        int paymentId = 0;
        System.out.println(payment);
        String query = "insert into payments(recipient_card_number, sender_card_id, amount, description) values(?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, payment.getRecipientCardNumber());
            statement.setInt(2, payment.getSenderCardId());
            statement.setDouble(3, payment.getAmount());
            statement.setString(4, payment.getDescription());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                while (resultSet.next()) {
                    paymentId = resultSet.getInt(1);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paymentId;
    }

    @Override
    public void updatePayment(Payment payment) {

    }

    @Override
    public List<Payment> getAllPaymentByBalanceId(int balanceId, int start, String order) {
        List<Payment> payments = new ArrayList<>();
        String ordered = order.equals("default") ? "" : "order by " + order + " desc";
        String query = "select recipient_card_number, sender_card_id, payments.amount, description, status_id, accepting_date, payments.id  from payments " +
                "join cards c on c.id = payments.sender_card_id " +
                "join balance b on b.id = c.balance_id where balance_id=? " + ordered + " limit " + (start - 1) * pageTotal + ", " + pageTotal + ";";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, balanceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    payments.add(extractEntity(resultSet));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return payments;
    }


    @Override
    public int getCountOfPaymentPages(int balanceId) {
        int count = 0;
        String query = "select count(payments.id) from payments join cards c on c.id = payments.sender_card_id " +
                " join balance b on b.id = c.balance_id where balance_id=?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, balanceId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return count % pageTotal == 0 ? count / pageTotal : count / pageTotal + 1;
    }


    @Override
    public boolean checkBalanceForPayment(PaymentDTO payment) {
        boolean isEnough = false;
        double cost = payment.getSenderCard().getTariff().getCommission() > 0
                ? payment.getSenderCard().getTariff().getCommission() * payment.getAmount()
                : payment.getAmount();
        String query = "Select exists(select id from cards where id=? and amount>=?) from cards";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, payment.getSenderCard().getId());
            statement.setDouble(2, cost);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    isEnough = resultSet.getInt(1) > 0;
                    payment.setAmount(cost);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isEnough;
    }

    @Override
    public boolean confirmPayment(Payment payment) {
        boolean done = false;
        String queryConfirm = "update payments set status_id=2 where id=?";
        String queryUpdateCard = "update cards set amount=amount-? where id=?";
        try (PreparedStatement statementConfirm = connection.prepareStatement(queryConfirm);
             PreparedStatement statementUpCard = connection.prepareStatement(queryUpdateCard)) {
            connection.setAutoCommit(false);
            statementConfirm.setInt(1, payment.getId());
            statementUpCard.setDouble(1, payment.getAmount());
            statementUpCard.setInt(2, payment.getSenderCardId());
            statementConfirm.executeUpdate();
            statementUpCard.executeUpdate();
            connection.commit();
            done = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return done;
    }


    @Override
    public Payment extractEntity(ResultSet resultSet) throws SQLException {
        return Payment.newBuilder()
                .setRecipientCardNumber(resultSet.getString(1))
                .setSenderCardId(resultSet.getInt(2))
                .setAmount(resultSet.getDouble(3))
                .setDescription(resultSet.getString(4))
                .setAccepted(resultSet.getInt(5) > 0)
                .setAcceptingDate(LocalDateTime.of(resultSet.getDate(6).toLocalDate(),
                        resultSet.getTime(6).toLocalTime()))
                .setId(resultSet.getInt(7))
                .build();
    }
}
