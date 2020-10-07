package org.payments.repository;

public interface RepositoryFactory {
    BalanceRepository getBalanceRepository();
    CardRepository getCardRepository();
    PaymentRepository getPaymentRepository();
    TariffRepository getTariffRepository();
    UserRepository getUserRepository();


}
