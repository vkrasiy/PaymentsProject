package org.payments.controllers.comands;

import javax.servlet.http.HttpServletRequest;

public interface DataExtractor<T> {
     T extractData(HttpServletRequest request);
}
