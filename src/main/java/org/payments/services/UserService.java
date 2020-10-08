package org.payments.services;

import org.payments.dtos.impl.BalanceDTO;
import org.payments.dtos.impl.CardDTO;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.dtos.UserDTO;
import org.payments.util.impl.UserNotFoundException;

import java.util.List;

public interface UserService {
    UserDTO getUser(int id);
    UserDTO signIn(String login, String password, boolean isAdmin) throws UserNotFoundException;
    UserDTO signUp(UserDTO userDTO, String password);
    List<PaymentDTO> getAllPaymentsByUser(UserDTO userDTO, int start, int total, String order);
    PaymentDTO makePayment(PaymentDTO paymentDTO);
    boolean addCardToUserBalance(BalanceDTO balanceDTO, int tariffId) ;
    boolean topUpAccount(CardDTO cardDTO, double sumToUpdate);
}
