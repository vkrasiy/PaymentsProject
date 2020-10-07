package org.payments.controllers.listener;

import org.payments.util.impl.ResourceManager;
import org.payments.util.impl.SessionUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        if (session.getAttribute("locale") == null)
        {
            ResourceManager resourceManager = ResourceManager.getInstance();
            Locale localeEN = new Locale("en", "US");
            resourceManager.changeResource(localeEN);
            session.setAttribute("locale", localeEN);
            session.getAttribute("locale");
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        SessionUtil.removeUserFromSession(httpSessionEvent.getSession());
    }
}
