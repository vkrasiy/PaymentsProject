package org.payments.services;

import org.payments.dtos.impl.CardDTO;

public interface CardService {
    CardDTO createCardForUser(int balanceID, int tariffId);
    boolean increaseAmount(CardDTO cardDTO);
    CardDTO getCard(int id);

}
