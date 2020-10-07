<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 04.10.2020
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="menucontext.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.paymentError==true}">
    <div class="container">
        <div class="alert alert-danger alert-dismissible fade show">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Error!</strong> Not enough money. Please check your balance
        </div>
    </div>
</c:if>
<form action="/main?action=makePayment" method="post">
    <div class="container-fluid">
        <div class="row" style="background-color:#8fd19e;">
            <div class="col-sm-6">
                <select id="cards" name="card">
                    <c:forEach var="card" items="${user.balance.cards}">
                        <option value="${card.id}">${card.cardNumber}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-sm-6">
                <label for="recip">Recipient card:</label>
                <input type="text" class="form-control" id="recip" required="required" pattern="[0-9]{16}"
                       name="recCard">
                <br>
                <label for="sum">Sum: </label>
                <input type="text" class="form-control" id="sum" required="required" pattern="[0-9]+" name="sum">
                <br>
                <label for="desc">Description:</label>
                <input type="text" class="form-control" id="desc" name="description">
            </div>
        </div>
    </div>
    <input type="submit" value="Submit">
    <c:if test="${requestScope.paymentError==false}">
        <div class="container">
            <div class="alert alert-success alert-dismissible fade show">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Success!</strong> Operation done!!!
            </div>
        </div>
    </c:if>

</form>
</body>
</html>
