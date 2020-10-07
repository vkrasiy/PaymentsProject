package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.dtos.impl.CardDTO;
import org.payments.services.CardService;
import org.payments.services.UserService;
import org.payments.services.impl.CardServiceImpl;
import org.payments.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TopUpAccountCommand implements Command {
    private final UserService userService;
    private final CardService cardService;

    public TopUpAccountCommand() {
        userService = new UserServiceImpl();
        cardService = new CardServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CardDTO cardDTO = cardService.getCard(Integer.parseInt(httpServletRequest.getParameter("card")));
        double sum = Double.parseDouble(httpServletRequest.getParameter("sum"));
        userService.topUpAccount(cardDTO, sum);
        httpServletRequest.getSession().setAttribute("card", cardDTO);
        return "pages/card.jsp";
    }
}
