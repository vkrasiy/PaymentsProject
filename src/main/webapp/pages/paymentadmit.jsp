<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 04.10.2020
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="menucontext.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="payment" class="org.payments.dtos.impl.PaymentDTO" scope="session"/>

<html>
<head>
    <title>Title</title>
    <div class="container" style="background-color: #8fd19e">
        <h2>Payment</h2>
        <form action="/main?action=admitPayment&user=${user.id}&payment=${payment.id}" method="post">
            <div class="card">

                <div class="card-body">
                    From card: ${payment.senderCard.cardNumber}
                </div>
                <div class="card-body">
                    To card: ${payment.recipientCardNumber}
                </div>
                <div class="card-body">
                    Date: ${payment.acceptingDate}
                </div>
                <div class="card-body">
                    Description: ${payment.description}
                </div>
                <div class="card-body">
                    Amount: ${payment.amount}
                </div>
            </div>
            <input type="submit" value="Submit">
        </form>
    </div>
</head>
<body>

</body>
</html>
