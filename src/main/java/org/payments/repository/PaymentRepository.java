package org.payments.repository;

import org.payments.dtos.UserDTO;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.entities.Balance;
import org.payments.entities.Payment;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PaymentRepository {
    Optional<Payment> getPayment(int id);
    List<Optional<Payment>> getAllPaymentsByCard(String senderCardId);
    int addPayment(Payment payment);
    void updatePayment(Payment payment);
    int getCountOfPaymentPages(int balanceId);
    List<Payment> getAllPaymentByBalanceId(int balanceId, int start, String order);
    boolean checkBalanceForPayment(PaymentDTO paymentDTO);
    boolean confirmPayment(Payment payment);
}
