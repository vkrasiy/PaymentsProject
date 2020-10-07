<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 04.10.2020
  Time: 0:26
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="menucontext.jsp" %>
<jsp:useBean id="card" scope="session" class="org.payments.dtos.impl.CardDTO" type="org.payments.dtos.impl.CardDTO"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
<div class="row-cols-2">
    <div class="col">
        <form method="post">
            <div class="container">
                <h2>Card info</h2>
                <div class="card">
                    <div class="card-body">
                        Card number: ${card.cardNumber}<br>
                        Amount: ${card.amount}<br>
                        Tariff: ${card.tariff.name} with commission ${card.tariff.commission}%
                        Active: ${card.active}
                    </div>
                    <input type="button" class="btn btn-danger">Danger</input>
                    <input type="button" class="btn btn-dark">Dark</input>
                </div>
            </div>
        </form>
    </div>
    <div class="col">
        <form method="post" action="/main?action=topUpAccount&card=${card.id}">
            <label for="sum">Sum:</label>
            <input name="sum" type="text" class="form-control"  id="sum">
            <button name="submit" type="submit"> Increase balance</button>
        </form>
    </div>
</div>
</body>
</html>
