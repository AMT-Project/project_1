<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Register"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Application statistics</h1>
    <div class="statistics">
        <div class="stat">
            <div class="circle">${usersCount}</div>
            <p class="stat__text">Registered users</p>
        </div>
        <div class="stat">
            <div class="circle">${questionsCount}</div>
            <p class="stat__text">Questions asked</p>
        </div>
        <div class="stat">
            <div class="circle">${answersCount}</div>
            <p class="stat__text">Answers provided</p>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>