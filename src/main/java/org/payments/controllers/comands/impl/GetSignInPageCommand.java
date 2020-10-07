package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSignInPageCommand implements Command {
    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return "pages/signIn.jsp";
    }
}
