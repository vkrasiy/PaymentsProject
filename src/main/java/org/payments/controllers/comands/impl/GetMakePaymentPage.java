package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetMakePaymentPage implements Command {
    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return "pages/makepayment.jsp";
    }
}
