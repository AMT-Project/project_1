<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Register"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>

<div class="content">
    <h1>Application statistics</h1>
    <div class="messages">
        <c:forEach var="error" items="${errors}">
            <div class="error">${error}</div>
        </c:forEach>
    </div>
    <div class="questions">
        <div class="question__title">
            Nombre de questions : ${question.count}
        </div>
        <div class="users__title">
            Nombre d'utilisateurs inscrits : ${users.count}
        </div>
    </div>

    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>
