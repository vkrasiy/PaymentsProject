package org.payments.repository;

import org.payments.entities.Balance;

import java.util.Optional;

public interface BalanceRepository {
    Optional<Balance> getBalanceById(int id);
    void updateBalance(Balance balance);
    int getBalanceIdByCardNumber(String number);
    int createBalance();
}
