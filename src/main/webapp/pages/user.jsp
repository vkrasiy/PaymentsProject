<%@ page import="org.payments.dtos.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 03.10.2020
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="menucontext.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<br>
<div class="jumbotron text-center">
    <h1><fmt:message key="user.info"  bundle="${validation_text}"/></h1>

    <br>
    <h3>Id: ${user.id}</h3>
    <br>
    <h3>First name: ${user.first_name}</h3>
    <br>
    <h3>Last name: ${user.last_name}</h3>
    <br>
    <h3>Email: ${user.email}</h3>
    <br>
    <h3>Phone: ${user.phone}</h3>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <table class="center">
                    <h2>Cards:</h2>
                    <c:forEach var="i" items="${user.balance.cards}">
                        <tr>
                            <div class="card">
                                <form method="post" action="/main?action=getCard&card=${i.id}">
                                    <br>
                                    <h6> Card number: ${i.cardNumber}</h6>
                                    <br>
                                    <h6>Amount: ${i.amount}</h6>   <br>
                                    <h6>Active: ${i.active}</h6>    <br>
                                    <h6>Tariff: ${i.tariff.name}</h6>   <br> <br>
                                    <button type="submit" name="submit">View</button>
                                    <h6>------------------------------------------------</h6>
                                </form>
                            </div>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
    <div class="container addCard">
        <p>Add card <a href=cardAdd.jsp>Add card</a>.</p>
    </div>
</div>
</body>
</html>
