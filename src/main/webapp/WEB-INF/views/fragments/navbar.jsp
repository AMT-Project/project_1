<%--
  Created by IntelliJ IDEA.
  User: ludovicbonzon
  Date: 24.09.20
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>navbar</title>
</head>
<body>
<div class="navbar">
    <c:choose>
        <c:when test="${currentUser != null}">
            <div>
                <form id="logoutForm" method="POST" action="logout.do">
                    <button class="as-link" type="submit">Logout</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <button onclick="window.location.href='/stack/';">Home</button>
                <button onclick="window.location.href='/stack/register';">Registration</button>
                <button onclick="window.location.href='/stack/login';">Sign-in</button>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
