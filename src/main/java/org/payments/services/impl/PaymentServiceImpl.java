package org.payments.services.impl;

import org.payments.convertors.impl.PaymentConvertor;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.entities.Payment;
import org.payments.repository.PaymentRepository;
import org.payments.repository.impl.PaymentRepositoryImpl;
import org.payments.services.PaymentService;
import org.payments.util.impl.PaymentException;

import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentConvertor paymentConvertor;

    public PaymentServiceImpl() {
        this.paymentRepository = new PaymentRepositoryImpl();
        paymentConvertor = new PaymentConvertor(Payment.class, PaymentDTO.class);
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentToMake) {
        PaymentDTO paymentDTO = null;
        int createdPayment = paymentRepository.addPayment(paymentConvertor.toEntity(paymentToMake));
        if (createdPayment != 0) {
            paymentDTO = paymentRepository.getPayment(createdPayment).map(paymentConvertor::toDto).orElse(null);
        }
        return paymentDTO;
    }

    @Override
    public int getPageNumOfPayments(int balanceId) {
        return paymentRepository.getCountOfPaymentPages(balanceId);
    }

    @Override
    public boolean confirmPayment(int paymentId) throws PaymentException {
        Payment payment  = paymentRepository.getPayment(paymentId).orElseThrow(() -> new PaymentException("can't confirm operation"));
        return paymentRepository.confirmPayment(payment);
    }

    public PaymentDTO getPayment(int id){
        return paymentRepository.getPayment(id).map(paymentConvertor::toDto).orElse(null);
    }

    @Override
    public List<PaymentDTO> getAllPayments(int balanceId, int start, String order) {
        List<Payment> allPayments = paymentRepository.getAllPaymentByBalanceId(balanceId, start, order);
        return allPayments.isEmpty() ? new ArrayList<>() : paymentConvertor.toDto(allPayments);
    }

    @Override
    public boolean checkBalanceBeforeAdmitting(PaymentDTO paymentDTO) {
        return paymentRepository.checkBalanceForPayment(paymentDTO);
    }


}
