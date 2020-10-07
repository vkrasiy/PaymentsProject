package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.dtos.UserDTO;
import org.payments.services.UserService;
import org.payments.services.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserCommand implements Command {
    private final UserService userService;

    public GetUserCommand() {
        userService = new UserServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
         UserDTO userDTO = userService.getUser(Integer.parseInt(httpServletRequest.getParameter("id")));
         httpServletRequest.getSession().setAttribute("user", userDTO);
         return "pages/user.jsp";
    }
}
