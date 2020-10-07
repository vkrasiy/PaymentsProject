package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.util.impl.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ChangeLangCommand implements Command {
    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        final Locale locale = new Locale(request.getParameter("lang"), request.getParameter("country"));
        ResourceManager resourceManager = ResourceManager.getInstance();
        resourceManager.changeResource(locale);
        request.getSession(false).setAttribute("locale", locale);
        return request.getSession().getAttribute("role") != null
                ? "pages/user.jsp" : "pages/main.jsp";
    }
}
