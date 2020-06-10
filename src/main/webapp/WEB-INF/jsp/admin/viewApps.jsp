<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header_admin.jspf" %>
<div class="view_content">
    <c:set var="title" value=""></c:set>
    <c:choose>
        <c:when test="${status eq 'opened' }">
            <c:set var="title" value="opened"></c:set>
        </c:when>
        <c:when test="${status eq 'all' }">
            <c:set var="title" value="all"></c:set>
        </c:when>
    </c:choose>

    <table>
        <caption>View <c:out value="${title } "/> applications</caption>
        <thead>
        <tr>
            <th>ID</th>
            <th>Message</th>
            <th>ID brigade</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="app" items="${apps }">
            <tr>
                <td>${app.id }</td>
                <td>${app.message }</td>
                <td>${app.brigadeId }</td>
                <td>${app.statusId }</td>

                <c:choose>
                    <c:when test="${status eq 'opened' }">

                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="changeStatus">
                            <input type="hidden" name="id" value="${app.id }">
                            <td>
                                <select name="statusId">
                                    <option value="1">done</option>
                                    <option value="2">rejected</option>
                                </select>
                            </td>
                            <td>
                                <input type="submit" value="Change status" class="button">
                            </td>
                        </form>


                    </c:when>
                    <%-- 	<c:when test="${status eq 'all' }">
                            <td>${app.statusId }</td>
                        </c:when>
                        --%>
                </c:choose>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
</body>
</html>