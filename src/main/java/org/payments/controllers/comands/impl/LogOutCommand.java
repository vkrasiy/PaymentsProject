package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.util.impl.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements Command {
    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        httpServletRequest.getSession().setAttribute("user", null);
        httpServletRequest.getSession().removeAttribute("role");
        SessionUtil.removeUserFromSession(httpServletRequest.getSession());
        return "/index.jsp";
    }


}
