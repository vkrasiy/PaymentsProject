<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 04.10.2020
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="menucontext.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="container">
    <h2>All payments</h2>
    <form method="post"
          action="/main?action=getPayments&id=${user.balance.id}&page=1">
        <select id="orders" name="order">
            <option value="accepting_date">By date</option>
            <option value="amount">By amount</option>
        </select>
        <input  type="submit" value="submit">
    </form>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th>From card</th>
            <th>Amount</th>
            <th>Recipient card</th>
            <th>Status</th>
            <th>Description</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${requestScope.payments}">
            <tr>
                <td>${p.senderCard.cardNumber}</td>
                <td>${p.amount}</td>
                <td>${p.recipientCardNumber}</td>
                <td>${p.accepted}</td>
                <td>${p.description}</td>
                <td>${p.acceptingDate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
    <h2>Pagination - Active State</h2>
    <p>Add class .active to let the user know which page he/she is on:</p>
    <ul class="pagination">
        <c:if test="${requestScope.pageCount<0}">
            <li class="page-item"><a class="page-link" href="#">Previous</a></li>
        </c:if>
        <c:forEach var="n" begin="1" end="${requestScope.pageCount}">
            <c:if test="${n==requestScope.page}">
                <li class="page-item active"><a class="page-link"
                                                href="/main?action=getPayments&id=${user.balance.id}&page=${n}&order=${requestScope.order}">${n}</a>
                </li>
            </c:if>
            <c:if test="${n!=requestScope.page}">
                <li class="page-item "><a class="page-link"
                                          href="/main?action=getPayments&id=${user.balance.id}&page=${n}&order=${requestScope.order}">${n}</a>
                </li>
            </c:if>
        </c:forEach>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
</div>
</body>
</html>
