package org.payments.services;

import org.payments.dtos.UserDTO;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.util.impl.PaymentException;

import java.util.List;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);
    List<PaymentDTO> getAllPayments(int balanceId, int start, String order);
    boolean checkBalanceBeforeAdmitting(PaymentDTO paymentDTO);
    int getPageNumOfPayments(int balanceId);
    boolean confirmPayment(int paymentId) throws PaymentException;
    public PaymentDTO getPayment(int id);
}
