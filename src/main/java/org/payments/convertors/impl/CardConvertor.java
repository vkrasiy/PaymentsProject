package org.payments.convertors.impl;

import org.payments.convertors.AbstractConvertor;
import org.payments.dtos.impl.CardDTO;
import org.payments.dtos.impl.TariffDTO;
import org.payments.entities.Card;
import org.payments.entities.Tariff;
import org.payments.repository.BalanceRepository;
import org.payments.repository.CardRepository;
import org.payments.repository.TariffRepository;
import org.payments.repository.impl.BalanceRepositoryImpl;
import org.payments.repository.impl.CardRepositoryImpl;
import org.payments.repository.impl.TariffRepositoryImpl;

import java.util.Optional;


public class CardConvertor extends AbstractConvertor<Card, CardDTO> {
    private final CardRepository cardRepository;
    private final BalanceRepository balanceRepository;
    private final TariffRepository tariffRepository;
    private final TariffConvertor tariffConvertor;


    public CardConvertor(Class<Card> cardClass, Class<CardDTO> cardDTOClass) {
        super(cardClass, cardDTOClass);
        cardRepository = new CardRepositoryImpl();
        balanceRepository = new BalanceRepositoryImpl();
        tariffRepository = new TariffRepositoryImpl();
        tariffConvertor = new TariffConvertor(Tariff.class, TariffDTO.class);
    }

    @Override
    public CardDTO toDto(Card card) {
        CardDTO cardDTO = CardDTO.newBuilder()
                .setCardNumber(card.getCardNumber())
                .setActive(card.isActive())
                .setId(card.getId())
                .setAmount(card.getAmount())
                .build();
        convertSpecificFieldsToDto(card, cardDTO);
        return cardDTO;
    }


    @Override
    public Card toEntity(CardDTO cardDTO) {
        Card card = Card.newBuilder()
                .setId(cardDTO.getId())
                .setCardNumber(cardDTO.getCardNumber())
                .setActive(cardDTO.isActive())
                .setAmount(cardDTO.getAmount())
                .setTariffId(cardDTO.getTariff().getId())
                .build();
        convertSpecificFieldsToEntity(cardDTO, card);
        return card;
    }

    @Override
    public void convertSpecificFieldsToEntity(CardDTO source, Card destination) {
        destination.setBalanceId(balanceRepository.getBalanceIdByCardNumber(source.getCardNumber()));
        destination.setCVV(cardRepository.getCVVByCardNumber(source.getCardNumber()));
        destination.setMM_YY(cardRepository.getMM_YYByCardNumber(source.getCardNumber()));
    }

    @Override
    public void convertSpecificFieldsToDto(Card source, CardDTO destination) {
        Optional<Tariff> tariff = tariffRepository.getTariffById(source.getTariffId());
        destination.setTariff(tariffConvertor.toDto(tariff.isPresent() ? tariff.get() : new Tariff()));
    }
}
