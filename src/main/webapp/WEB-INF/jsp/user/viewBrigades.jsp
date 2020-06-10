<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header_client.jspf" %>
<div class="view_content">
    <table>
        <caption>Brigades</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Description</th>
            <th>Creator ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="brigade" items="${brigades }">
            <tr>
                <td><a href="controller?command=brigadeInf&id=${brigade.id }">${brigade.id }</a></td>
                <td>${brigade.name }</td>
                <td>${brigade.userId }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="add_content">
    <p>Add new brigade</p>
    <p>Input description about brigade</p>
    <form action="controller" method="post">
        <input type="hidden" name="command" value="addBrigade">
        <input type="text" name="name">
        <input type="hidden" name="userId" value="${user.id }">
        <input type="submit" value="Add Brigade" class="button">
    </form>
</div>
</body>
</html>