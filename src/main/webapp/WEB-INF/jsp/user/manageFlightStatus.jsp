<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header_client.jspf" %>
<div>
    <div class="view_content">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>From</th>
                <th>To</th>
                <th>Date</th>
                <th>Time</th>
                <th>Brigade id</th>
                <th>Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${flights }">
                <tr>
                    <td>${item.id }</td>
                    <td>${item.name }</td>
                    <td>${item.fromPort }</td>
                    <td>${item.toPort }</td>
                    <td>${item.dateFlight }</td>
                    <td>${item.timeFlight }</td>
                    <td>${item.brigadeId }</td>
                    <td>${item.statusId }</td>
                    <form action="controller" method="post">
                        <input type="hidden" name="command" value="changeFlightStatus">
                        <input type="hidden" name="id" value="${app.id }">
                        <td>
                            <select name="statusId">
                                <option value="1">done</option>
                                <option value="2">canceled</option>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Change status" class="button">
                        </td>
                    </form>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>