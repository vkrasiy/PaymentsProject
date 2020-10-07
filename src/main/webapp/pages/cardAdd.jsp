<%--
  Created by IntelliJ IDEA.
  User: Віталік
  Date: 03.10.2020
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="menucontext.jsp" %>

<html>
<head>
    <title>Title</title>
    <div class="jumbotron text-center">
        <form action="/main?id=${user.id}&action=addCard" method="post">
            <label for="tariffs">Choose a car:</label>
            <select id="tariffs" name="tariff">
                <option value="1">Tariff 1</option>
                <option value="2">Tariff 2</option>
                <option value="3">Tariff 3</option>
                <option value="4">Tariff 4</option>
            </select>

            <input type="submit" value="Submit">
        </form>
        <br>

    </div>
</head>
<body>
<div>
    <h1>User Info:</h1>
    <br>
    <h3>${user.id}</h3>
    <br>
    <h3>${user.first_name} ${user.last_name}</h3>
    <br>
    <h3>${user.email}</h3>
    <br>
    <h3>${user.phone}</h3>
</div>
</body>
</html>
