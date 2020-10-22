<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<jsp:useBean id="question" scope="request" type="ch.heigvd.amt.stack.application.question.QuestionsDTO.QuestionDTO"/>

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
    <button class="navbar__link" onclick="window.location.href='/stack/submitAnswer';">New Answer</button>
</div>

<%@ include file="fragments/footer.jsp" %>
