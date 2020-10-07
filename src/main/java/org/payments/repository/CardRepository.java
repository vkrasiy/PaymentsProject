package org.payments.repository;

import org.payments.entities.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Optional<Card> getCard(int id);
    List<Optional<Card>> getCardsByBalanceId(int id);
    int addNewCard(Card card);
    boolean updateCardBalance(Card card);
    void deleteCard(Card card);
    boolean existsCardWithSuchNumber(String cardNumber);
    int getCardIdByNumber(String cardNumber);
    String getCVVByCardNumber(String cardNumber);
    String getMM_YYByCardNumber(String cardNumber);
}
