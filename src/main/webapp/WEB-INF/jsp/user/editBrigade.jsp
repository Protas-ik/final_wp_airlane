<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header_client.jspf" %>

<div class="view_content">
    Edit Brigade
    <table>
        <caption>Employees in brigade ${brigade.id }</caption>
        <thead>
        <tr>
            <td>ID</td>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Profession</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="item" items="${empsInBrigade }">
            <tr>
                <td>${item.id }</td>
                <td> ${item.firstName } </td>
                <td> ${item.lastName } </td>
                <td> ${item.professionId } </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="add_content">
    <div>
        <p>Add employee into brigade</p>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="addEmpIntoBrigade">
            <input type="hidden" name="brigadeId" value="${brigade.id }">

            <select name="idEmp">
                <c:forEach var="item" items="${ freeEmps}">
                    <option value="${item.id }">${item.firstName } ${item.lastName } ${item.professionId } </option>
                </c:forEach>
            </select>
            <input type="submit" value="Add emp" class="button">
        </form>
    </div>

    <div>
        <p>Create application for admin</p>
        <form action="controller" method="post">
            <input type="hidden" name="command" value="createApp">
            <input type="hidden" name="brigadeId" value="${brigade.id }">
            <input type="text" name="message">
            <input type="submit" value="Send" class="button">
        </form>
    </div>
</div>

</body>
</html>