package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.controllers.comands.DataExtractor;
import org.payments.dtos.UserDTO;
import org.payments.services.PaymentService;
import org.payments.services.UserService;
import org.payments.services.impl.PaymentServiceImpl;
import org.payments.services.impl.UserServiceImpl;
import org.payments.util.impl.PaymentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdmitPaymentCommand implements Command, DataExtractor<UserDTO> {
    private final PaymentService paymentService;
    private final UserService userService;

    public AdmitPaymentCommand() {
        paymentService = new PaymentServiceImpl();
        userService = new UserServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            paymentService.confirmPayment(Integer.parseInt(httpServletRequest.getParameter("payment")));
            httpServletRequest.getSession().setAttribute("user", extractData(httpServletRequest));
            return "pages/user.jsp";
        } catch (PaymentException e) {
            return "pages/error.jsp";
        }
    }


    @Override
    public UserDTO extractData(HttpServletRequest request) {

        return userService.getUser(Integer.parseInt(request.getParameter("user")));
    }
}
