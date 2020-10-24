<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Question details</h1>
    <!-- QUESTION -->
    <div class="question-details__question">
        <div class="question-details__question--row">
            <div class="question-details__votes">
                <button>+</button>
                <div class="question-details__vote-count">0<!-- Nombre de votes--></div>
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

        <!-- QUESTION COMMENTS LIST-->
        <ul class="question-details__comments-list">
            <c:forEach var="comment" items="${question.comments.comments}">
                <li class="question-details__comment">
                        ${comment.content} - ${comment.username} @ ${comment.createdOn.toString()}
                </li>
            </c:forEach>
            <!-- QUESTION FORM, ADD COMMENT-->
            <li class="question-details__comment">
                <form class="form-inline question__comment--form" action="${pageContext.request.contextPath}/submitComment.do" method="POST">
                    <input name="questionUUID" type="hidden" value=${question.uuid.asString()}>
                    <textarea class="form-control question__comment--textarea" type="text" placeholder="Add a comment" name="content" required></textarea>
                    <input class="form-btn question__comment--button" type="submit" value="Submit">
                </form>
            </li>
        </ul>
    </div>

    <!-- ANSWERS LIST-->
    <ul class="answers-list">
        <c:forEach var="answer" items="${question.answers.answers}">
            <li class="answers-list__list-element">
                <div class="answers-list__answer--row">
                    <div class="answer__votes">
                        <button>+</button>
                        <div class="answer__vote-count">0<!-- Nombre de votes--></div>
                        <button>-</button>
                    </div>
                    <div class="answer__content">
                        <div class="answer__description">
                                ${answer.content}
                        </div>
                        <div class="answer__author">
                                ${answer.username} on ${answer.createdOn.toString()}
                        </div>
                    </div>
                </div>

                <ul class="answer__comments-list">
                    <!-- FOREACH COMMENTAIRES -->
                    <c:forEach var="comment" items="${question.comments.comments}">
                        <li class="answer__comment">
                                ${comment.content} - ${comment.username} @ ${comment.createdOn.toString()}
                        </li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>

    <form class="form-register" action="${pageContext.request.contextPath}/submitAnswer.do" method="POST">
        <input id="questionUUID1" name="questionUUID" type="hidden" value=${question.uuid.asString()}>
        <p>Your Answer</p>
        <textarea class="form-control" type="text" placeholder="content" name="content" required></textarea>
        <input class="form-btn" type="submit" value="Submit">
    </form>
    <!-- TODO : Hide answers and comments forms if user is not authentified -->
</div>

<%@ include file="fragments/footer.jsp" %>
