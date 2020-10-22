<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Login"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>
<div class="content">
    <h1>Login Account</h1>

    <div class="messages">
        <c:forEach var="error" items="${errors}">
            <div class="error">${error}</div>
        </c:forEach>
    </div>
    <form class="form-login" action="${pageContext.request.contextPath}/login.do" method="POST">
        <p>Username</p>
        <input class="form-control" type="text" placeholder="username" name="username"/>
        <p>Password</p>
        <input class="form-control" type="password" placeholder="password" name="password"/>
        <input class="form-btn" type="submit" value="Login">
    </form>

</div>

<div id="footer">
    <%@ include file="fragments/footer.jsp" %>
</div>
