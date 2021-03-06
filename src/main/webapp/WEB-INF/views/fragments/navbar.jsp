<div class="navbar">
    <c:choose>
        <c:when test="${currentUser != null}">
            <div class="navbar__buttons">
                <button class="navbar__link--dark" onclick="window.location.href='/stack/';">Home</button>
                <button class="navbar__link--dark" onclick="window.location.href='/stack/submitQuestion';">New
                    Question
                </button>
            </div>

            <div class="navbar__user-interaction">
                <button id="profile" class="navbar__link--light-lowercase"
                        onclick="window.location.href='/stack/profile';">${currentUser.firstName} ${currentUser.lastName}</button>
                <form id="logoutForm" method="POST" action="logout.do">
                    <button class="navbar__link--dark" type="submit">Logout</button>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <button class="navbar__link--dark" onclick="window.location.href='/stack/';">Home</button>
            <div class="navbar__user-interaction">
                <button class="navbar__link--dark" onclick="window.location.href='/stack/login';">Sign-in</button>
                <button class="navbar__link--dark" onclick="window.location.href='/stack/register';">Registration</button>
            </div>
        </c:otherwise>
    </c:choose>
</div>

