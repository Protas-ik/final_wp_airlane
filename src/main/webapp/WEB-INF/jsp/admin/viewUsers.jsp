<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>
<%@ page import="wp.epam.protas.airline.entity.User" %>

<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<jsp:useBean id="user" type="wp.epam.protas.airline.entity.User" scope="session"/>
<body>
<%@ include file="/WEB-INF/jspf/header_admin.jspf" %>
<div>
    <table>
        <caption>Users</caption>
        <tr>
            <th>ID</th>
            <th>Login</th>


        </tr>
    </table>
</div>
</body>
</html>