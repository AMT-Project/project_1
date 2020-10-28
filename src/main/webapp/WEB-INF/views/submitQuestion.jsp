<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Submit Question"/>

<%@ include file="fragments/header.jsp" %>

<div class="content">
    <h1>Submit Question</h1>
    <form class="form-register" action="${pageContext.request.contextPath}/submitQuestion.do" method="POST">
        <p>Title</p>
        <input class="form-control" type="text" placeholder="title" name="title" required/>
        <p>Description</p>
        <textarea class="form-control" type="text" placeholder="description" name="description" required></textarea>
        <input class="form-btn" type="submit" value="Submit">
    </form>
</div>

<%@ include file="fragments/footer.jsp" %>