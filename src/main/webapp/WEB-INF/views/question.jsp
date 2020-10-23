<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Question details</h1>
    <div class="question-details__question">
        <div class="question-details__question--row">
            <div class="question-details__votes">
                <button>+</button>
                <div class="question-details__vote-count"><!-- Nombre de votes--></div>
                <button>-</button>
            </div>
            <div class="question-details__question--content">
                <div class="question-details__question__title">
                    ${question.title}
                </div>
                <div class="question-details__question__description">
                    ${question.description}
                </div>
                <div class="question-details__question__author">
                    ${question.username} on ${question.createdOn.toString()}
                </div>
            </div>
        </div>

        <ul class="question-details__comments-list">
            <!-- FOREACH COMMENTAIRES -->
            <c:forEach var="comment" items="${question.comments.comments}">
                <li class="question-details__comment">
                    <!-- FIXME : BUG les temps sont actualisés au refresh de la page -->
                        ${comment.content} - ${comment.username} @ ${comment.createdOn.toString()}
                </li>
            </c:forEach>
        </ul>
    </div>
    <div class="question-details__answers">
        <form class="form-register" action="${pageContext.request.contextPath}/submitAnswer.do" method="POST">
            <input id="questionUUID1" name="questionUUID" type="hidden" value=${question.uuid.asString()}>
            <p>Your Answer</p>
            <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
            <input class="form-btn" type="submit" value="Submit">
        </form>

        <c:forEach var="answer" items="${question.answers.answers}">
            ${answer.content} - ${answer.username} @ ${answer.createdOn.toString()}
        </c:forEach>

        <form class="form-register" action="${pageContext.request.contextPath}/submitComment.do" method="POST">
            <input id="questionUUID2" name="questionUUID" type="hidden" value=${question.uuid.asString()}>
            <p>Your Comment</p>
            <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
            <input class="form-btn" type="submit" value="Submit">
        </form>
        <!-- FOREACH REPONSES -->
        <!-- FOREACH COMMENTAIRES -->
        <!-- END FOREACH REPONSES -->
    </div>
</div>

<%@ include file="fragments/footer.jsp" %>
