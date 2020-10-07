<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 03.10.2020
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="pages/menucontext.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="" class="form-signin">
    <h1 class="text-center"><fmt:message key="main.payments"  bundle="${validation_text}"/></h1>
    <a href="/main?action=main" class="btn btn-lg btn-outline-primary form-signin"><fmt:message key="main.makePayment"  bundle="${validation_text}"/></a>
</form>
</body>
</html>