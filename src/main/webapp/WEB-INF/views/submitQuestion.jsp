<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="pageTitle" scope="session" value="Submit Question"/>

<div id="navbar">
    <%@ include file="fragments/header.jsp" %>
</div>

<div class="content">
    <h1>Submit Question</h1>
    <form class="form-register" action="${pageContext.request.contextPath}/submitQuestion.do" method="POST">
        <p>Title</p>
        <input class="form-control" type="text" placeholder="title" name="title" required/>
        <p>Description</p>
        <input class="form-control" type="text" placeholder="description" name="description" required/>
        <input class="form-btn" type="submit" value="Submit">
    </form>

    <div id="footer">
        <%@ include file="fragments/footer.jsp" %>
    </div>
</div>