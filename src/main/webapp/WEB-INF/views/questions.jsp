<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.stack.application.question.QuestionsDTO"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>
<div class="content">
    <h1>List of questions</h1>
    <ul class="questions-list">
        <c:forEach var="question" items="${questions.questions}">
            <li class="questions-list__list-element">
                <div class="question">
                    <div class="question__title">
                            ${question.title}
                    </div>
                    <div class="question__description">
                            ${question.description}
                    </div>
                    <div class="question__author">
                            ${question.author}
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>
    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>