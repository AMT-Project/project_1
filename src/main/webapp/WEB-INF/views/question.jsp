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

        <!-- QUESTION COMMENTS LIST -->
        <ul class="question-details__comments-list">
            <c:forEach var="comment" items="${question.comments.comments}">
                <li class="question-details__comment">
                        ${comment.content} - ${comment.username} @ ${comment.createdOn.toString()}
                </li>
            </c:forEach>

            <!-- QUESTION FORM, ADD COMMENT -->

            <li class="question-details__comment">
                <c:choose>
                    <c:when test="${currentUser != null}">
                        <form class="form-inline comment__form"
                              action="${pageContext.request.contextPath}/submitComment.do"
                              method="POST">
                            <input name="questionUUID" type="hidden" value=${question.uuid.asString()}>
                            <textarea class="form-control comment__textarea" type="text" placeholder="Write a comment"
                                      name="content" required></textarea>
                            <input class="form-btn comment__button" type="submit" value="Submit">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="comment-forms--not-logged-in">You must be logged in to be able to comment a question</p>
                    </c:otherwise>
                </c:choose>
            </li>

        </ul>
    </div>

    <!-- ANSWERS LIST -->
    <ul class="answers-list">
        <c:forEach var="answer" items="${question.answers.answers}">
            <!-- ANSWER -->
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
                    <!-- ANSWER COMMENTS LIST -->
                    <c:forEach var="comment" items="${answer.comments.comments}">
                        <li class="answer__comment">
                                ${comment.content} - ${comment.username} @ ${comment.createdOn.toString()}
                        </li>
                    </c:forEach>
                    <!-- ANSWER FORM, ADD COMMENT -->
                    <li class="answer__comment">
                        <c:choose>
                            <c:when test="${currentUser != null}">
                                <form class="form-inline comment__form"
                                      action="${pageContext.request.contextPath}/submitComment.do" method="POST">
                                    <input name="answerUUID" type="hidden" value=${answer.uuid.asString()}>
                                    <textarea class="form-control comment__textarea" type="text"
                                              placeholder="Write a comment"
                                              name="content" required></textarea>
                                    <input class="form-btn comment__button" type="submit" value="Submit">
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p class="comment-forms--not-logged-in">You must be logged in to be able to comment an answer</p>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </li>
        </c:forEach>
    </ul>

    <!-- ANSWER FORM, ADD REPLY -->
    <c:choose>
        <c:when test="${currentUser != null}">
            <form class="form-answer" action="${pageContext.request.contextPath}/submitAnswer.do" method="POST">
                <input name="questionUUID" type="hidden" value=${question.uuid.asString()}>
                <p>Reply with an answer</p>
                <textarea class="form-control" type="text" placeholder="Write your answer" name="content"
                          required></textarea>
                <input class="form-btn" type="submit" value="Submit">
            </form>
        </c:when>
        <c:otherwise>
            <p class="answer-forms--not-logged-in">You must be logged in to be able to reply to this question</p>
        </c:otherwise>
    </c:choose>
    <!-- TODO : Nice to have, factorize forms in fragment -->
</div>

<%@ include file="fragments/footer.jsp" %>
