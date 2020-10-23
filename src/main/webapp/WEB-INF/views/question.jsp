<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Question details</h1>
    <div class="question-details__question">
        <div class="question-details__votes">
            <button>+</button>
            <div class="question-details__vote-count"><!-- Nombre de votes--></div>
            <button>-</button>
        </div>
        <div class="question-details__question__title">
            ${question.title}
        </div>
        <div class="question-details__question__description">
            ${question.description}
        </div>
        <div class="question-details__question__author">
            ${question.username} on ${question.createdOn}
        </div>

        <div class="question-details__question__comments">
            <!-- FOREACH COMMENTAIRES -->
        </div>
    </div>
    <div class="question-details__answers">
        <!-- FOREACH REPONSES -->
        <!-- FOREACH COMMENTAIRES -->
        <!-- END FOREACH REPONSES -->
    </div>
    <button class="navbar__link--dark"
            onclick="location.href='${pageContext.request.contextPath}/submitAnswer?uuid=${question.uuid.asString()}';">
        New Answer
    </button>
</div>

<%@ include file="fragments/footer.jsp" %>
