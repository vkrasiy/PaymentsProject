package org.payments.services.impl;

import org.payments.convertors.impl.CardConvertor;
import org.payments.dtos.impl.CardDTO;
import org.payments.entities.Card;
import org.payments.repository.CardRepository;
import org.payments.repository.impl.CardRepositoryImpl;
import org.payments.services.CardService;

import java.security.SecureRandom;
import java.time.LocalDate;

public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardConvertor cardConvertor;

    public CardServiceImpl() {
        this.cardRepository = new CardRepositoryImpl();
        this.cardConvertor = new CardConvertor(Card.class, CardDTO.class);
    }

    @Override
    public CardDTO createCardForUser(int balanceID, int tariffId) {
        CardDTO cardDTO = null;
        Card generatedCard = generateNewCard(balanceID, tariffId);
        int cardIdToGet = cardRepository.addNewCard(generatedCard);
        if (cardIdToGet != 0) {
            cardDTO = cardRepository.getCard(cardIdToGet).map(cardConvertor::toDto).orElse(null);
        }
        System.out.println(cardDTO);
        return cardDTO;
    }

    @Override
    public boolean increaseAmount(CardDTO cardDTO) {
        return cardRepository.updateCardBalance(cardConvertor.toEntity(cardDTO));
    }

    @Override
    public CardDTO getCard(int id) {
        return cardRepository.getCard(id).map(cardConvertor::toDto)
                .orElse(null);
    }

    private Card generateNewCard(int balanceID, int tariffId) {
        //cardRepository.existsCardWithSuchNumber(cardNumber);
        String cardNumber = generateCardNumber();
        return Card.newBuilder()
                .setTariffId(tariffId)
                .setCardNumber(cardNumber)
                .setCVV(generateCardCVV())
                .setMM_YY(generateMM_YY())
                .setBalanceId(balanceID)
                .build();
    }


    //TODO change algorithm
    private String generateCardNumber() {
        SecureRandom random = new SecureRandom();
        StringBuilder cardBuilder = new StringBuilder();
        int seed = 1000;
        int limit = 9999;
        random.setSeed(seed);
        cardBuilder.append(random.nextInt(limit) + " ")
                .append(random.nextInt(limit) + " ")
                .append(random.nextInt(limit) + " ")
                .append(random.nextInt(limit));
        return cardBuilder.toString();
    }

    private String generateCardCVV() {
        SecureRandom random = new SecureRandom();
        StringBuilder cardCVV = new StringBuilder();
        int seed = 100;
        int limit = 9999;
        random.setSeed(seed);
        cardCVV.append(random.nextInt(limit));
        return cardCVV.toString();
    }

    private String generateMM_YY() {
        LocalDate finishDate = LocalDate.now().plusYears(5);
        return finishDate.getYear() + "|" + finishDate.getMonthValue();
    }
}
