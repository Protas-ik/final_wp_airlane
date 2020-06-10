<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header_admin.jspf" %>
<div>
    <a href="controller?command=viewEmployees"> View Employees</a>
</div>
<div>
    <a href="controller?command=viewApplications"> View all applications</a>
</div>
<div>
    <a href="controller?command=viewOpenedApps"> View new applications</a>
</div>
<div>
    <a href="controller?command=viewFlights">View and edit flights</a>
</div>
</body>
</html>