<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Profile"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>${user.firstName} ${user.lastName}'s profile</h1>
    <div class="profile">
        <div class="profile__row">
            <div class="profile__infos">
                <p class="profile__infos--text">Username : ${user.username}</p>
                <p class="profile__infos--text">First name : ${user.firstName}</p>
                <p class="profile__infos--text">Last name : ${user.lastName}</p>
                <p class="profile__infos--text">Email : ${user.email}</p>
            </div>

            <div class="stat">
                <c:if test="${userAnswerCount != null}" var="condition">
                    <div class="circle">${userAnswerCount}</div>
                    <p class="stat__text">Answers given</p>
                </c:if>
                <c:if test="${!condition}">
                    <div class="circle">0</div>
                    <p class="stat__text">Answers given</p>
                </c:if>
            </div>
        </div>
        <div class="profile__questions">
            <h4 class="profile__questions--title">Asked questions list :</h4>
            <c:forEach var="question" items="${userQuestions.questions}">
                <li class="questions-list__list-element"
                    onclick="location.href='${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}';">
                    <div class="question">
                        <div class="question__title">
                                ${question.title}
                        </div>
                        <div class="question__description">
                                ${question.description}
                        </div>
                    </div>
                </li>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>