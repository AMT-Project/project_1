<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Statistics"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Application statistics</h1>
    <div class="statistics">
        <div class="statistics__general">
            <div class="stat">
                <div class="circle">${usersCount}</div>
                <p class="stat__text">Registered users</p>
            </div>
            <div class="stat">
                <div class="circle">${questionsCount}</div>
                <p class="stat__text">Questions asked</p>
            </div>
            <div class="stat">
                <div class="circle">${answersCount}</div>
                <p class="stat__text">Answers provided</p>
            </div>
        </div>

        <div class="statistics__leaderboards">
            <c:forEach var="leaderboard" items="${leaderboards.leaderboards}">
                <div class="leaderboard__item">
                    <h2>${leaderboard.pointScale.pointScaleName}</h2>
                    <h5>${leaderboard.pointScale.pointScaleDesc}</h5>
                    <table class="leaderboard__table">
                        <tr class="leaderboard__table--tr-th">
                            <th class="leaderboard__table--th">Username</th>
                            <th class="leaderboard__table--th">Points</th>
                        </tr>
                        <c:forEach var="leaderboardEntry" items="${leaderboard.leaderboardEntries.leaderboardEntries}">
                            <tr class="leaderboard__table--tr-td">
                                <td class="leaderboard__table--td">${leaderboardEntry.username}</td>
                                <td class="leaderboard__table--td">${leaderboardEntry.pointsSum}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>