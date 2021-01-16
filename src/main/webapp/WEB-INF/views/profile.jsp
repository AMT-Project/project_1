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

        <hr class="hr--solid">

        <div class="badges__wrapper">
            <h3>Badges received</h3>
            <div class="badges__row">
                <c:if test="${badges != null}" var="condition">
                    <c:forEach var="badge" items="${badges.badges}">
                        <div class="badge__item">
                            <h4 class="profile__stat__badge">${badge.badgeName}</h4>
                            <p class="profile__infos--text">${badge.badgeDesc}</p>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${!condition}">
                    <h4>No badges yet. Continue to use the app to recieve some !</h4>
                </c:if>
            </div>
        </div>

        <hr class="hr--solid">

        <div class="pointsscores__wrapper">
            <h3>Points scores on active point scales</h3>
            <div class="pointsscores__row">
                <c:if test="${pointsScores != null}" var="condition">
                    <c:forEach var="pointsScore" items="${pointsScores.pointsScores}">
                        <div class="pointsscore__item">
                            <table class="pointsscore__table">
                                <tr class="pointsscore__table--tr-th">
                                    <th class="pointsscore__table--th">Point scale</th>
                                    <th class="pointsscore__table--th">Points</th>
                                </tr>
                                <tr class="pointsscore__table--tr-td">
                                    <td class="pointsscore__table--td">${pointsScore.pointScaleName}</td>
                                    <td class="pointsscore__table--td">${pointsScore.score}</td>
                                </tr>
                            </table>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${!condition}">
                    <h4>No point scales yet. Continue to use the app and score some points !</h4>
                </c:if>
            </div>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>