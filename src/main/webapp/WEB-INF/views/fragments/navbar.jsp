<div class="navbar">
    <c:choose>
        <c:when test="${currentUser != null}">
            <div class="navbar__buttons">
                <button class="navbar__link" onclick="window.location.href='/stack/questions';">Home</button>
            </div>

            <div class="navbar__user-interaction">
                <div class="navbar__user-info">
                        ${currentUser.firstName} ${currentUser.lastName}
                </div>

                <button class="navbar__link" onclick="window.location.href='/stack/submitQuestion';">New Question</button>

                <form id="logoutForm" method="POST" action="logout.do">
                    <button class="navbar__link" type="submit">Logout</button>
                </form>
            </div>

        </c:when>
        <c:otherwise>
            <button class="navbar__link" onclick="window.location.href='/stack/questions';">Home</button>
            <div class="navbar__user-interaction">
                <button class="navbar__link" onclick="window.location.href='/stack/login';">Sign-in</button>
                <button class="navbar__link" onclick="window.location.href='/stack/register';">Registration</button>
            </div>
        </c:otherwise>
    </c:choose>
</div>
