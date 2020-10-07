<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 04.10.2020
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="user" scope="session" class="org.payments.dtos.UserDTO" type="org.payments.dtos.UserDTO"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="validation_text" var="validation_text" scope="session"/>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <title>Title</title>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">

        <ul class="navbar-nav">
            <c:if test="${sessionScope.role!=null&&sessionScope.user!=null}">
                <li class="nav-item ">
                    <a class="nav-link" href="/main?action=getUser&id=${user.id}"><fmt:message key="navbar.main"  bundle="${validation_text}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main?action=getPayments&id=${user.balance.id}&page=1&order=default"><fmt:message key="user.info"  bundle="${validation_text}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main?action=makePaymentPage">Make Payment</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main?action=logOut">Log out</a>
                </li>

            </c:if>
            <c:if test="${sessionScope.role==null||sessionScope.user==null}">
                <li class="nav-item">
                    <a class="nav-link" href="/"><fmt:message key="navbar.main"  bundle="${validation_text}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main?action=signInPage"><fmt:message key="navbar.login"  bundle="${validation_text}"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/main?action=main"><fmt:message key="navbar.reg"  bundle="${validation_text}"/></a>
                </li>
            </c:if>
            <form method="post">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="reset" data-toggle="dropdown">
                        <fmt:message key="navbar.choose_lang"  bundle="${validation_text}"/>
                    </a>
                    <div class="dropdown-menu ">
                        <a class="dropdown-item" href="/main?action=changeLang&lang=en&country=US"><fmt:message key="navbar.lang_en"  bundle="${validation_text}"/></a>
                        <a class="dropdown-item" href="/main?action=changeLang&lang=uk&country=UK"><fmt:message key="navbar.lang_ua"  bundle="${validation_text}"/><a>
                    </div>
                </li>
            </form>
        </ul>
    </nav>
</head>
<body>

</body>
</html>
