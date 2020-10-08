package org.payments.controllers.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        boolean loggedIn = request.getSession().getAttribute("user") != null
                && request.getSession().getAttribute("role") != null;
        boolean tryingToEnter = "main".equalsIgnoreCase(request.getParameter("action"))
                || ("signIn".equalsIgnoreCase(request.getParameter("action")))
                || "signUp".equalsIgnoreCase(request.getParameter("action"))
                || "signinpage".equalsIgnoreCase(request.getParameter("action"))
                || "changeLang".equalsIgnoreCase(request.getParameter("action"));

        boolean safePages =  (request.getRequestURI().contains("index.jsp")
                || request.getRequestURI().contains("signIn.jsp")
                || request.getRequestURI().contains("main.jsp"));
        System.out.println("role " + request.getSession().getAttribute("role") + " user " + request.getSession().getAttribute("user"));
        if (loggedIn || safePages || tryingToEnter) {
            System.out.println(loggedIn +  " do chain " +safePages +"   " + tryingToEnter );

            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
