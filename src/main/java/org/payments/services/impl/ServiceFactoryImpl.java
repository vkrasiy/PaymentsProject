package org.payments.services.impl;

import org.payments.services.*;

public class ServiceFactoryImpl implements ServiceFactory {

    @Override
    public UserService getUserService() {
        return new UserServiceImpl();
    }

    @Override
    public PaymentService getPaymentService() {
        return new PaymentServiceImpl();
    }

    @Override
    public CardService getCardService() {
        return new CardServiceImpl();
    }

    @Override
    public BalanceService getBalanceService() {
        return new BalanceServiceImpl();
    }
}
