<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 03.10.2020
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="menucontext.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container align-content-center" >
    <form action="/main?action=signIn" method="post">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" required>
        <br>
        <label for="password">Password</label>
        <input type="password" id="password" name="password">
        <br>
        <input type="submit" value="Submit">
    </form>
</div>


</body>
</html>
