package org.payments.util;

public class CardNotFoundException extends Exception{
    @Override
    public String getMessage() {
        return "Card cannot be added";
    }

}
