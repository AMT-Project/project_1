<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Question details</h1>
    <div class="question">
        <div class="question__title">
            ${question.title}
        </div>
        <div class="question__description">
            ${question.description}
        </div>
        <div class="question__author">
            ${question.username}
        </div>
    </div>
    <button class="navbar__link--dark" onclick="location.href='${pageContext.request.contextPath}/submitAnswer?uuid=${question.uuid.asString()}';">New Answer</button>
</div>

<%@ include file="fragments/footer.jsp" %>
