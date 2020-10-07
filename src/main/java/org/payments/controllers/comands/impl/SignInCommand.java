package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.dtos.UserDTO;
import org.payments.services.UserService;
import org.payments.services.impl.UserServiceImpl;
import org.payments.util.impl.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements Command {
    private UserService userService;

    public SignInCommand() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        if (!SessionUtil.checkUserIsLogged(httpServletRequest, login)) {
            int role = 0; //TODO check the role
            UserDTO userDTO = userService.signIn(login, password, role > 0);
            httpServletRequest.getSession().setAttribute("role", "user");
            httpServletRequest.getSession().setAttribute("user", userDTO);
            return "pages/user.jsp";
        }
        return "loginError.jsp";
    }

}
