<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Navbar</title>
</head>
<body>
<div class="navbar">
    <c:choose>
        <c:when test="${currentUser != null}">
            <div>
                    ${currentUser.firstName} ${currentUser.lastName}
            </div>

            <div>
                <button onclick="window.location.href='/stack/questions';">Home</button>
                <form id="logoutForm" method="POST" action="logout.do">
                    <button class="as-link" type="submit">Logout</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <button onclick="window.location.href='/stack/questions';">Home</button>
                <button onclick="window.location.href='/stack/register';">Registration</button>
                <button onclick="window.location.href='/stack/login';">Sign-in</button>
            </div>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
