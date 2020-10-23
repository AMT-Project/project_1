<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Question details</h1>
    <div class="question">
        <div class="question__title">
            ${question.title}
        </div>
        <div class="question__description">
            ${question.description}
        </div>
        <div class="question__author">
            ${question.username}
        </div>
    </div>
    <form class="form-register" action="${pageContext.request.contextPath}/submitAnswer.do" method="POST">
        <input id="questionUuid1" name="questionUuid" type="hidden" value=${question.uuid.asString()}>
        <p>Your Answer</p>
        <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
        <input class="form-btn" type="submit" value="Submit">
    </form>
    <form class="form-register" action="${pageContext.request.contextPath}/submitComment.do" method="POST">
        <input id="questionUuid2" name="questionUuid" type="hidden" value=${question.uuid.asString()}>
        <p>Your Comment</p>
        <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
        <input class="form-btn" type="submit" value="Submit">
    </form>
</div>

<%@ include file="fragments/footer.jsp" %>
