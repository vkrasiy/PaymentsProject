package org.payments.services;

public interface ServiceFactory {
    UserService getUserService();
    PaymentService getPaymentService();
    CardService getCardService();
    BalanceService getBalanceService();
}
