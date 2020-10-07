package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.controllers.comands.DataExtractor;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.services.CardService;
import org.payments.services.UserService;
import org.payments.services.impl.CardServiceImpl;
import org.payments.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakePaymentCommand implements Command, DataExtractor<PaymentDTO> {
    private final UserService userService;
    private final CardService cardService;

    public MakePaymentCommand() {
        userService = new UserServiceImpl();
        cardService = new CardServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        PaymentDTO paymentDTO = userService.makePayment(extractData(httpServletRequest));
        if(paymentDTO!=null){
            httpServletRequest.getSession().setAttribute("payment", paymentDTO);
            return "redirect:pages/paymentadmit.jsp";
        }else {
            httpServletRequest.setAttribute("paymentError", true);
            return "pages/makepayment.jsp";
        }


    }

    @Override
    public PaymentDTO extractData(HttpServletRequest request) {
        System.out.println(Integer.parseInt(request.getParameter("card")));
        System.out.println(cardService.getCard(Integer.parseInt(request.getParameter("card"))));
        return PaymentDTO.newBuilder()
                .setAmount(Integer.parseInt(request.getParameter("sum")))
                .setSenderCard(cardService.getCard(Integer.parseInt(request.getParameter("card"))))
                .setRecipientCardNumber(request.getParameter("recCard"))
                .setDescription(request.getParameter("description"))
                .build();
    }
}
