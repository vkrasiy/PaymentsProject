package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.controllers.comands.DataExtractor;
import org.payments.dtos.impl.CardDTO;
import org.payments.services.CardService;
import org.payments.services.impl.CardServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetCardCommand implements Command, DataExtractor<CardDTO> {
    private CardService cardService;

    public GetCardCommand() {
        cardService = new CardServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletRequest.getSession().setAttribute("card", extractData(httpServletRequest));
        return "pages/card.jsp";
    }

    @Override
    public CardDTO extractData(HttpServletRequest httpServletRequest){
         return cardService.getCard(Integer.parseInt(httpServletRequest.getParameter("card")));
    }
}
