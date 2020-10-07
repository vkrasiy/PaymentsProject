package org.payments.controllers.comands.impl;

import org.payments.controllers.comands.Command;
import org.payments.controllers.comands.DataExtractor;
import org.payments.dtos.impl.PaymentDTO;
import org.payments.services.ServiceFactory;
import org.payments.services.impl.ServiceFactoryImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetPaymentsCommand implements Command, DataExtractor<String> {
    private ServiceFactory serviceFactory;


    public GetPaymentsCommand() {
        serviceFactory = new ServiceFactoryImpl();
    }

    @Override
    public String executeCommand(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        int balanceId = Integer.parseInt(httpServletRequest.getParameter("id"));
        httpServletRequest.setAttribute("order", httpServletRequest.getParameter("order"));
        List<PaymentDTO> payments = serviceFactory.getPaymentService().getAllPayments(balanceId, getPage(httpServletRequest), extractData(httpServletRequest));
        payments.stream().forEach(System.out::println);
        httpServletRequest.setAttribute("payments", payments);
        httpServletRequest.getServletContext().setAttribute("payments", payments);
        httpServletRequest.setAttribute("pageCount", serviceFactory.getPaymentService().getPageNumOfPayments(balanceId));
        return "redirect:pages/payments.jsp";
    }

    private int getPage(HttpServletRequest httpServletRequest) {
        int pageNum = 1;
        if (httpServletRequest.getParameter("page") != null) {
            pageNum = Math.max(Integer.parseInt(httpServletRequest.getParameter("page")), 1);
            httpServletRequest.setAttribute("page", pageNum);
        }
        return pageNum;
    }

    @Override
    public String extractData(HttpServletRequest httpServletRequest){
        String order =  httpServletRequest.getParameter("order");
        httpServletRequest.setAttribute("order", order);
        return order;
    }
}
