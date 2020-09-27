<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register Account</title>
</head>
<body>

<div id="navbar">
    <%@ include file="fragments/navbar.jsp" %>
</div>

<h1>Register Account</h1>
    <form action="${pageContext.request.contextPath}/register.do" method="post">
        <p>Username</p>
        <input type="text" placeholder="username" name="userName" required/>
        <p>First name</p>
        <input type="text" placeholder="firstname" name="firstName" required/>
        <p>Last name</p>
        <input type="text" placeholder="lastname" name="lastName" required/>
        <p>Email</p>
        <input type="text" placeholder="email" name="email" required/>
        <p>Password</p>
        <input type="password" placeholder="password" name="password" required/>
        <input type="submit" value="Register">
    </form>
</body>
</html>
