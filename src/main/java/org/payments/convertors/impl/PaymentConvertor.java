package org.payments.convertors.impl;

import org.payments.convertors.AbstractConvertor;
import org.payments.dtos.impl.CardDTO;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.entities.Card;
import org.payments.entities.Payment;
import org.payments.repository.CardRepository;
import org.payments.repository.impl.CardRepositoryImpl;

import java.util.Optional;

public class PaymentConvertor extends AbstractConvertor<Payment, PaymentDTO> {
    private final CardRepository cardRepository;
    private final CardConvertor cardConvertor;

    public PaymentConvertor(Class<Payment> paymentClass, Class<PaymentDTO> paymentDTOClass) {
        super(paymentClass, paymentDTOClass);
        cardRepository = new CardRepositoryImpl();
        cardConvertor = new CardConvertor(Card.class, CardDTO.class);
    }

    @Override
    public Payment toEntity(PaymentDTO paymentDTO) {
        Payment payment = Payment.newBuilder()
                .setAccepted(paymentDTO.isAccepted())
                .setAmount(paymentDTO.getAmount())
                .setAcceptingDate(paymentDTO.getAcceptingDate())
                .setId(paymentDTO.getId())
                .setDescription(paymentDTO.getDescription())
                .setRecipientCardNumber(paymentDTO.getRecipientCardNumber())
                .build();
        convertSpecificFieldsToEntity(paymentDTO, payment);
        return payment;
    }

    @Override
    public PaymentDTO toDto(Payment payment) {
        PaymentDTO paymentDTO = PaymentDTO.newBuilder()
                .setId(payment.getId())
                .setDescription(payment.getDescription())
                .setAmount(payment.getAmount())
                .setAccepted(payment.isAccepted())
                .setAcceptingDate(payment.getAcceptingDate())
                .setRecipientCardNumber(payment.getRecipientCardNumber())
                .build();
        convertSpecificFieldsToDto(payment, paymentDTO);
        return paymentDTO;
    }

    @Override
    public void convertSpecificFieldsToEntity(PaymentDTO source, Payment destination) {
        destination.setSenderCardId(source.getSenderCard().getId());
    }

    @Override
    public void convertSpecificFieldsToDto(Payment source, PaymentDTO destination) {
        destination.setSenderCard(cardConvertor
                .toDto(cardRepository.getCard(source.getSenderCardId())
                .orElseGet(Card::new)));
    }
}
