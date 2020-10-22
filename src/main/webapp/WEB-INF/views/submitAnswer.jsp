<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Submit Answer"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>

<div class="content">
    <h1>Submit Answer</h1>
    <form class="form-register" action="${pageContext.request.contextPath}/submitAnswer.do" method="POST">
        <p>Your Answer</p>
        <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
        <input class="form-btn" type="submit" value="Submit">
    </form>

    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>