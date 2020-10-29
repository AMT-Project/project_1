<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Question details</h1>
    <!-- QUESTION -->
    <div class="question-details__question">
        <div class="question-details__question--row">
            <!-- TODO : VOTES -->
            <!-- TODO : Nice to have - Mettre dans un fragment -->
            <c:choose>
                <c:when test="${currentUser != null}">
                    <form action="vote.do" method="post">
                        <input name="voteType" type="hidden" value="up"/>
                        <input name="questionUuid" type="hidden" value="${question.uuid.asString()}"/>
                        <input name="redirectUuid" type="hidden" value="${question.uuid.asString()}"/>
                        <button name="upvoteBtn" class="form-btn" type="submit">+</button>
                    </form>
                    <div class="question-details__vote-count">${question.votes.count}</div>
                    <form action="vote.do" method="post">
                        <input name="voteType" type="hidden" value="down"/>
                        <input name="questionUuid" type="hidden" value="${question.uuid.asString()}"/>
                        <input name="redirectUuid" type="hidden" value="${question.uuid.asString()}"/>
                        <button name="upvoteBtn" class="form-btn" type="submit">-</button>
                    </form>
                </c:when>
                <c:otherwise>
                    <div class="question-details__vote-count">${question.votes.count}</div>
                </c:otherwise>
            </c:choose>
            <div class="question-details__question--content">
                <div class="question-details__question__title">
                    ${question.title}
                </div>
                <div class="question-details__question__description">
                    ${question.description}
                </div>
                <div class="question-details__question__author">
                    ${question.username} on ${question.printLocalDateTime()}
                </div>
            </div>
        </div>

        <!-- QUESTION COMMENTS LIST -->
        <ul class="question-details__comments-list">
            <c:forEach var="comment" items="${question.comments.comments}">
                <li class="question-details__comment">
                        ${comment.content} - ${comment.username} on ${comment.printLocalDateTime()}
                </li>
            </c:forEach>

            <!-- QUESTION FORM, ADD COMMENT -->

            <li class="question-details__comment">
                <c:choose>
                    <c:when test="${currentUser != null}">
                        <form class="form-inline comment__form"
                              action="${pageContext.request.contextPath}/submitComment.do"
                              method="POST">
                            <input name="questionUUID" type="hidden" value="${question.uuid.asString()}"/>
                            <input name="redirectUuid" type="hidden" value="${question.uuid.asString()}"/>
                            <textarea class="form-control comment__textarea" type="text" placeholder="Write a comment"
                                      name="content" required></textarea>
                            <input class="form-btn comment__button" type="submit" value="Submit">
                        </form>
                    </c:when>
                    <c:otherwise>
                        <p class="comment-forms--not-logged-in">You must be logged in to be able to comment a
                            question</p>
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
                        <c:choose>
                            <c:when test="${currentUser != null}">
                                <form action="vote.do" method="post">
                                    <input name="voteType" type="hidden" value="up"/>
                                    <input name="answerUuid" type="hidden" value="${answer.uuid.asString()}"/>
                                    <input name="redirectUuid" type="hidden" value="${question.uuid.asString()}"/>
                                    <button name="upvoteBtn" type="submit">+</button>
                                </form>
                                <div class="question-details__vote-count">${answer.votes.count}</div>
                                <form action="vote.do" method="post">
                                    <input name="voteType" type="hidden" value="down"/>
                                    <input name="answerUuid" type="hidden" value="${answer.uuid.asString()}"/>
                                    <input name="redirectUuid" type="hidden" value="${question.uuid.asString()}"/>
                                    <button name="upvoteBtn" type="submit">-</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <div class="question-details__vote-count">${question.votes.count}</div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="answer__content">
                        <div class="answer__description">
                                ${answer.content}
                        </div>
                        <div class="answer__author">
                                ${answer.username} on ${answer.printLocalDateTime()}
                        </div>
                    </div>
                </div>

                <ul class="answer__comments-list">
                    <!-- ANSWER COMMENTS LIST -->
                    <c:forEach var="comment" items="${answer.comments.comments}">
                        <li class="answer__comment">
                                ${comment.content} - ${comment.username} on ${comment.printLocalDateTime()}
                                    ${answer.uuid.asString()}
                        </li>
                    </c:forEach>
                    <!-- ANSWER FORM, ADD COMMENT -->
                    <li class="answer__comment">
                        <c:choose>
                            <c:when test="${currentUser != null}">
                                <form class="form-inline comment__form"
                                      action="${pageContext.request.contextPath}/submitComment.do" method="POST">
                                    <input name="answerUUID" type="hidden" value="${answer.uuid.asString()}">
                                    <input name="redirectUuid" type="hidden" value="${question.uuid.asString()}">
                                    <textarea id="commentAnswer" class="form-control comment__textarea" type="text"
                                              placeholder="Write a comment"
                                              name="content" required></textarea>
                                    <input id="submitCommentAnswer" class="form-btn comment__button" type="submit"
                                           value="Submit"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <p class="comment-forms--not-logged-in">You must be logged in to be able to comment an
                                    answer</p>
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
                <input name="questionUUID" type="hidden" value="${question.uuid.asString()}">
                <p>Reply with an answer</p>
                <textarea class="form-control" type="text" placeholder="Write your answer" name="content"
                          required></textarea>
                <input id="submitAnswer" class="form-btn" type="submit" value="Submit">
            </form>
        </c:when>
        <c:otherwise>
            <p class="answer-forms--not-logged-in">You must be logged in to be able to reply to this question</p>
        </c:otherwise>
    </c:choose>
    <!-- TODO : Nice to have, factorize forms in fragment -->

    <nav>
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}&amp;currentPage=${currentPage-1}">Previous</a>
                </li>
            </c:if>

            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <li class="page-item active"><a class="page-link">
                                ${i} <span class="sr-only">(current)</span></a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link"
                                                 href="${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}&amp;currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}&amp;currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<%@ include file="fragments/footer.jsp" %>
