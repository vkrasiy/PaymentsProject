package org.payments.services.impl;

import org.payments.convertors.impl.BalanceConvertor;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.entities.Balance;
import org.payments.repository.BalanceRepository;
import org.payments.repository.impl.BalanceRepositoryImpl;
import org.payments.services.BalanceService;

public class BalanceServiceImpl implements BalanceService {
    private final BalanceRepository balanceRepository;
    private final BalanceConvertor balanceConvertor;

    public BalanceServiceImpl() {
        this.balanceRepository = new BalanceRepositoryImpl();
        this.balanceConvertor = new BalanceConvertor(Balance.class, BalanceDTO.class);
    }

    @Override
    public BalanceDTO createBalance() {
        Balance balance = balanceRepository.getBalanceById(balanceRepository.createBalance())
                .orElseGet(null);
        return balance!=null ? balanceConvertor.toDto(balance) : null;
    }
}
