package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.util.impl.ResourceManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class GetMainCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

        if (httpServletRequest.getSession().getAttribute("locale") == null)
        {
            ResourceManager resourceManager = ResourceManager.getInstance();
            Locale localeEN = new Locale("en", "US");
            resourceManager.changeResource(localeEN);
            httpServletRequest.getSession().setAttribute("locale", localeEN);
        }
        return "pages/main.jsp";
    }
}
