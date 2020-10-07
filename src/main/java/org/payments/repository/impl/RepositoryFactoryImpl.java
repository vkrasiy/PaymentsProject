package org.payments.repository.impl;

import org.payments.repository.*;


public class RepositoryFactoryImpl implements RepositoryFactory {
    public RepositoryFactoryImpl(){

    }


    @Override
    public BalanceRepository getBalanceRepository() {
        return new BalanceRepositoryImpl();
    }

    @Override
    public CardRepository getCardRepository() {
        return new CardRepositoryImpl();
    }

    @Override
    public PaymentRepository getPaymentRepository() {
        return new PaymentRepositoryImpl();
    }

    @Override
    public TariffRepository getTariffRepository() {
        return new TariffRepositoryImpl();
    }

    @Override
    public UserRepository getUserRepository() {
        return new UserRepositoryImpl();
    }

}
