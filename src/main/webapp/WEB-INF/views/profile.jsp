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
            <div class="profile__update">
                <div class="messages">
                    <c:forEach var="error" items="${errors}">
                        <div class="error">${error}</div>
                    </c:forEach>
                </div>
                <form class="form-profile" action="${pageContext.request.contextPath}/updateProfile.do" method="POST">
                    <input class="form-control" type="text" placeholder="new username" name="username"/>
                    <input class="form-control" type="text" placeholder="new email" name="email"/>
                    <input class="form-control" type="text" placeholder="new firstname" name="firstName"/>
                    <input class="form-control" type="text" placeholder="new lastname" name="lastName"/>
                    <input class="form-control" type="password" placeholder="old password" name="oldPassword"/>
                    <input class="form-control" type="password" placeholder="new password" name="newPassword"/>
                    <input class="form-control" type="password" placeholder="repeat password" name="repeatPassword"/>
                    <input class="form-btn" type="submit" value="Update">
                </form>
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

        <div class="badges__wrapper">
            <h3>Badges received</h3>
            <div class="badges__row">

                <c:forEach var="badge" items="${badges.badges}">
                    <div class="badge__item">
                        <h4 class="profile__stat__badge">${badge.badgeName}</h4>
                        <p class="profile__infos--text">${badge.badgeDesc}</p>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="pointscales__row">

        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>