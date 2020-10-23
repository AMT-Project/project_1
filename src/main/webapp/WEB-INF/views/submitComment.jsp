<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Submit Comment"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Submit Comment</h1>
    <form class="form-register" action="${pageContext.request.contextPath}/submitComment.do" method="POST">
        <input id="questionUuid" name="questionUuid" type="hidden" value="${questionUuid}">
        <input id="answerUuid" name="answerUuid" type="hidden" value="${answerUuid}">
        <p>Your Comment</p>
        <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
        <input class="form-btn" type="submit" value="Submit">
    </form>
</div>

<%@ include file="fragments/footer.jsp" %>