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

            <div class="profile__stat">
                <div class="profile__stat__item">
                    <c:if test="${userQuestionsCount != null}" var="condition">
                        <div class="circle">${userQuestionsCount}</div>
                        <p class="profile__stat__text">Questions asked</p>
                    </c:if>
                    <c:if test="${!condition}">
                        <div class="circle">0</div>
                        <p class="profile__stat__text">Questions asked</p>
                    </c:if>
                </div>
                <div class="profile__stat__item">
                    <c:if test="${userAnswersCount != null}" var="condition">
                        <div class="circle">${userAnswersCount}</div>
                        <p class="profile__stat__text">Answers given</p>
                    </c:if>
                    <c:if test="${!condition}">
                        <div class="circle">0</div>
                        <p class="profile__stat__text">Answers given</p>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="profile__questions">
            <h4 class="profile__questions--title">Asked questions list :</h4>
            <ul class="profile__questions-list">
                <c:forEach var="question" items="${userQuestions.questions}">
                    <li class="profile__questions-list__element"
                        onclick="location.href='${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}';">
                        <div class="profile__questions-list__question">
                            <div class="profile__questions-list__question__title">
                                    ${question.title}
                            </div>
                            <div class="profile__questions-list__question__description">
                                    ${question.description}
                            </div>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>