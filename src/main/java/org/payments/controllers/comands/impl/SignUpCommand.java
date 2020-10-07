package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.controllers.comands.DataExtractor;
import org.payments.dtos.UserDTO;
import org.payments.services.UserService;
import org.payments.services.impl.UserServiceImpl;
import org.payments.util.impl.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class SignUpCommand implements Command, DataExtractor<UserDTO> {
    private UserService userService;

    public SignUpCommand() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserDTO userDTO = extractData(httpServletRequest);
        if (!SessionUtil.checkUserIsLogged(httpServletRequest, userDTO.getLogin())) {
            UserDTO user = userService.signUp(userDTO, httpServletRequest.getParameter("password"));
            httpServletRequest.getSession().setAttribute("role", "user");
            httpServletRequest.getSession().setAttribute("login", userDTO.getLogin());
            httpServletRequest.getSession().setAttribute("user", user);
        }
        return "pages/user.jsp";
    }

    @Override
    public UserDTO extractData(HttpServletRequest httpServletRequest) {
        String login = httpServletRequest.getParameter("login");
        String password = httpServletRequest.getParameter("password");
        String email = httpServletRequest.getParameter("email");
        String firstName = httpServletRequest.getParameter("firstName");
        String lastName = httpServletRequest.getParameter("lastName");
        String phone = httpServletRequest.getParameter("phone");
        UserDTO userDTO = null;
        if (Objects.nonNull(login) && Objects.nonNull(phone)
                && Objects.nonNull(password) && Objects.nonNull(email)
                && Objects.nonNull(firstName) && Objects.nonNull(lastName)) {
             userDTO= UserDTO.newBuilder()
                    .setLogin(login)
                    .setEmail(email)
                    .setFirst_name(firstName)
                    .setLast_name(lastName)
                    .setLogin(lastName)
                    .setPhone(phone).build();
        }
        return userDTO;
    }
}
