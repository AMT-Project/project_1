<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>List of questions</h1>
    <ul class="questions-list">
        <c:forEach var="question" items="${questions.questions}">
            <li class="questions-list__list-element"
                onclick="location.href='${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}';">
                <div class="questions-list__question">
                    <div class="questions-list__question__title">
                            ${question.title}
                    </div>
                    <div class="questions-list__question__description">
                            ${question.description}
                    </div>
                    <div class="questions-list__question__author">
                            by ${question.username} @ ${question.createdOn}
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>

<%@ include file="fragments/footer.jsp" %>