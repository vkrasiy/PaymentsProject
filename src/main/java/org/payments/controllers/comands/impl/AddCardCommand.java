package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.controllers.comands.DataExtractor;
import org.payments.dtos.UserDTO;
import org.payments.dtos.impl.BalanceDTO;
import org.payments.services.UserService;
import org.payments.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCardCommand implements Command, DataExtractor<BalanceDTO> {
    private final UserService userService;

    public AddCardCommand() {
        userService = new UserServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if(userService.addCardToUserBalance(extractData(httpServletRequest),
                Integer.parseInt(httpServletRequest.getParameter("tariff")))){
        }
        return "pages/user.jsp";
    }

    @Override
    public BalanceDTO extractData(HttpServletRequest request) {
        return ((UserDTO)request.getSession().getAttribute("user")).getBalance();
    }
}
