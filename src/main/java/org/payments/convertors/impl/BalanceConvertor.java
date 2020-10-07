package org.payments.convertors.impl;


import org.payments.convertors.AbstractConvertor;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.dtos.impl.CardDTO;
import org.payments.entities.Balance;
import org.payments.entities.Card;
import org.payments.repository.BalanceRepository;
import org.payments.repository.CardRepository;
import org.payments.repository.impl.BalanceRepositoryImpl;
import org.payments.repository.impl.CardRepositoryImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BalanceConvertor extends AbstractConvertor<Balance, BalanceDTO> {

    private final CardRepository cardRepository;
    private final BalanceRepository balanceRepository;
    private final CardConvertor cardConvertor;

    public BalanceConvertor(Class<Balance> balanceClass, Class<BalanceDTO> balanceDTOClass) {
        super(balanceClass, balanceDTOClass);
        cardRepository = new CardRepositoryImpl();
        balanceRepository = new BalanceRepositoryImpl();
        cardConvertor = new CardConvertor(Card.class, CardDTO.class);
    }


    @Override
    public BalanceDTO toDto(Balance balance) {

        BalanceDTO balanceDTO = BalanceDTO.newBuilder()
                .setBonus_amount(balance.getBonus_amount())
                .setId(balance.getId())
                .build();
        convertSpecificFieldsToDto(balance, balanceDTO);
        return balanceDTO;
    }

    @Override
    public void convertSpecificFieldsToDto(Balance source, BalanceDTO destination) {
        List<Optional<Card>> cards = cardRepository.getCardsByBalanceId(source.getId());
        destination.addAllCard(cardConvertor.toDto(cards.stream().map(card -> card.isPresent() ? card.get(): new Card()).collect(Collectors.toList())));
    }

}
