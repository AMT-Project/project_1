<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<jsp:useBean scope="request" id="questions" type="ch.heigvd.amt.stack.application.question.QuestionsDTO"/>
<jsp:useBean scope="request" id="persons" type="ch.heigvd.amt.stack.application.identitymgmt.authenticate.PersonsDTO"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>
<div class="content">
    <h1>List of questions</h1>
    <ul class="questions-list">
        <c:forEach var="question" items="${questions.questions}">
            <c:forEach var="person" items="${persons.persons}">
                <li class="questions-list__list-element">
                    <div class="question">
                        <div class="question__title">
                                ${question.title}
                        </div>
                        <div class="question__description">
                                ${question.description}
                        </div>
                        <div class="question__author">
                            <c:if test="${person.uuid.toString() == question.author.asString()}">
                                ${person.username}
                            </c:if>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </c:forEach>
    </ul>
    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>