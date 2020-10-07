package org.payments.controllers.comands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
