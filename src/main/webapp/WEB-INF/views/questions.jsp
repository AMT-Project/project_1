<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>List of questions</h1>
    <ul class="questions-list">
        <c:forEach var="question" items="${questions.questions}">
            <li class="questions-list__list-element"
                onclick="location.href='${pageContext.request.contextPath}/question?uuid=${question.uuid.asString()}';">
                <div class="questions-list__question">
                    <div class="questions-list__question__title">
                            ${question.title}
                    </div>
                    <div class="questions-list__question__description">
                            ${question.description}
                    </div>
                    <div class="questions-list__question__author">
                        by ${question.username} @ ${question.createdOn}
                    </div>
                </div>
            </li>
        </c:forEach>
    </ul>

    <nav>
        <ul class="pagination">
            <c:if test="${currentPage != 1}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/questions?currentPage=${currentPage-1}">Previous</a>
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
                                                 href="${pageContext.request.contextPath}/questions?currentPage=${i}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="${pageContext.request.contextPath}/questions?currentPage=${currentPage+1}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<%@ include file="fragments/footer.jsp" %>