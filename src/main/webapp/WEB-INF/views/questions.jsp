<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Questions"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>
<div class="content">
    <h1>List of questions</h1>
    <ul>
        <c:forEach items="${questions}" var="question">
            <li>"${question.question} - ${question.author}"</li>
        </c:forEach>
    </ul>
    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>