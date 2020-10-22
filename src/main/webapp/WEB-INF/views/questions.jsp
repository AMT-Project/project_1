<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.stack.application.question.QuestionsDTO"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>List of questions</h1>
    <ul class="questions-list">
        <c:forEach var="question" items="${questions.questions}">
            <li class="questions-list__list-element"
                onclick="location.href='${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}';">
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
            </li>
        </c:forEach>
    </ul>
</div>

<%@ include file="fragments/footer.jsp" %>