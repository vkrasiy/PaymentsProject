package org.payments.util.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import java.util.HashSet;

public class SessionUtil {
    public static boolean checkUserIsLogged(HttpServletRequest request, String userName) {
        HashSet<String> loggedUsers = (HashSet<String>) request.getSession().getServletContext()
                .getAttribute("loggedUsers");
        if (loggedUsers.stream().anyMatch(userName::equals)) {
            return true;
        }
        loggedUsers.add(userName);
        request.getSession().getServletContext()
                .setAttribute("loggedUsers", loggedUsers);
        return false;
    }

    public static void removeUserFromSession(HttpSession httpSession){
        HashSet<String> loggedUsers = (HashSet<String>) httpSession
                .getServletContext()
                .getAttribute("loggedUsers");
        String userName = (String) httpSession.getAttribute("userName");
        loggedUsers.remove(userName);
        httpSession.setAttribute("loggedUsers", loggedUsers);
    }
}
