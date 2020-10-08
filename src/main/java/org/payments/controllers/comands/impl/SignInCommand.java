package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.dtos.UserDTO;
import org.payments.services.UserService;
import org.payments.services.impl.UserServiceImpl;
import org.payments.util.impl.SessionUtil;
import org.payments.util.impl.UserNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

public class SignInCommand implements Command {
    private UserService userService;

    public SignInCommand() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        System.out.println(login + "----");
        if (!SessionUtil.checkUserIsLogged(httpServletRequest, login)) {
            //TODO check the role
            //System.out.println("SIGNED IN");
            try {
                UserDTO userDTO = userService.signIn(login, password, false);
                SessionUtil.logIn(httpServletRequest, login);
                httpServletRequest.getSession().setAttribute("role", "user");
                httpServletRequest.getSession().setAttribute("user", userDTO);
                return "pages/user.jsp";
            } catch (UserNotFoundException e) {
                e.printStackTrace();
            }
        }
        return "loginError.jsp";
    }


}
