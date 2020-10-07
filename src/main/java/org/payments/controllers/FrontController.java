package org.payments.controllers;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.payments.controllers.comands.impl.CommandCaller;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;

@WebServlet()
public class FrontController extends HttpServlet {
    private  final  CommandCaller commandCaller = new CommandCaller();
    private static Logger logger = Logger.getLogger(FrontController.class.getName());

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        BasicConfigurator.configure();
        servletConfig.getServletContext()
                .setAttribute("loggedUsers", new HashSet<String>());
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String command  = httpServletRequest.getParameter("action");
        logger.info("post: "+command +" command!!!!!!!!");
        processRequest(httpServletRequest, httpServletResponse);
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String command  = httpServletRequest.getParameter("action");
        logger.info("get " + command +" command!!!!!!!!");
        processRequest(httpServletRequest, httpServletResponse);
    }

    private void processRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws ServletException, IOException{
        String command  = httpServletRequest.getParameter("action");
        String path = commandCaller.call(command.toUpperCase()).executeCommand(httpServletRequest, httpServletResponse);
        if(path.contains("redirect:")){
            httpServletRequest.getRequestDispatcher(path.replace("redirect:", ""))
                    .forward(httpServletRequest, httpServletResponse);
        }else {
            httpServletResponse.sendRedirect(path);
        }

    }
}
