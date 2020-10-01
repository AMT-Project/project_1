<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Register"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>

<div class="content">
    <h1>Register Account</h1>
    <div class="messages">
        <c:forEach var="error" items="${errors}">
            <div class="error">${error}</div>
        </c:forEach>
    </div>
    <form class="form-register" action="${pageContext.request.contextPath}/register.do" method="POST">
        <p>Username</p>
        <input class="form-control" type="text" placeholder="username" name="username"/>
        <p>First name</p>
        <input class="form-control" type="text" placeholder="firstname" name="firstName"/>
        <p>Last name</p>
        <input class="form-control" type="text" placeholder="lastname" name="lastName"/>
        <p>Email</p>
        <input class="form-control" type="text" placeholder="email" name="email"/>
        <p>Password</p>
        <input class="form-control" type="password" placeholder="password" name="password"/>
        <input class="form-btn" type="submit" value="Register">
    </form>

    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>