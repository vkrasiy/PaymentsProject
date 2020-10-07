<%@include file="menucontext.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/main?action=signUp" method="post">
    <div class="container" align="center">
        <h1>Register</h1>
        <p>Please fill in this form to create an account.</p>
        <hr>
        <br>
        <label for="login"><b><fmt:message key="reg.login"  bundle="${validation_text}"/></b></label>
        <br>
        <input type="text" placeholder="Enter login" name="login" id="login" required>
        <br>
        <label for="email"><b><fmt:message key="req.email"  bundle="${validation_text}"/></b></label>
        <br>
        <input type="text" placeholder="Enter Email" name="email" id="email" required>
        <br>
        <label for="firstName"><b><fmt:message key="reg.first_name"  bundle="${validation_text}"/></b></label>
        <br>
        <input type="text" placeholder="Enter first name" name="firstName" id="firstName" required>
        <br>
        <label for="lastName"><b><fmt:message key="reg.last_name"  bundle="${validation_text}"/></b></label>
        <br>
        <input type="text" placeholder="Enter last name" name="lastName" id="lastName" required>
        <br>
        <label for="phone"><b><fmt:message key="reg.phone"  bundle="${validation_text}"/></b></label>
        <br>
        <input type="text" placeholder="Enter phone" name="phone" id="phone" required>
        <br>
        <label for="password"><b><fmt:message key="reg.password"  bundle="${validation_text}"/></b></label>
        <br>
        <input type="password" placeholder="Enter Password" name="password" id="password" required>
        <br>
        <hr>
        <button type="submit" value="submit">Register</button>
    </div>

    <div class="container signin">
        <p><fmt:message key="account.have"  bundle="${validation_text}"/> <a href=signIn.jsp><fmt:message key="login"  bundle="${validation_text}"/></a>.</p>
    </div>
</form>
</body>
</html>
