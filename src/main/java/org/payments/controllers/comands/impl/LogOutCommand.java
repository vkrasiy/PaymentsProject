package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.util.impl.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        SessionUtil.removeUserFromSession(httpServletRequest.getSession());
        httpServletRequest.getSession().setAttribute("role", null);
        httpServletRequest.getSession().setAttribute("user", null);
        return "/index.jsp";
    }


}
