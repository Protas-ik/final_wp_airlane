<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<%@ include file="/WEB-INF/jspf/header_admin.jspf" %>
<script>
    function editFlight(idName, idPort) {
        var name = document.getElementById(idName).innerHTML;
        var port = document.getElementById(idPort).innerHTML;
        alert(name);
        alert(port);
    }

</script>
<div class="main_content">
    <div id="viewFlight" class="view_content">

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
            <%--@elvariable id="flights" type="java.util.List"--%>
            <%--@elvariable id="item" type="wp.epam.protas.airline.entity.Flight"--%>
            <c:forEach var="item" items="${flights }">
                <tr>
                    <td>${item.id }</td>
                    <td id="name${item.id}" contenteditable="true">${item.name }</td>
                    <td id="fromPort${item.id}" contenteditable="true">${item.fromPort }</td>
                    <td>${item.toPort }</td>
                    <td contenteditable="true">${item.dateFlight }</td>
                    <td>${item.timeFlight }</td>
                    <td>${item.brigadeId }</td>
                    <td>${item.statusId }</td>

                    <td>
                        <form action="controller">
                            <input type="hidden" name="command" value="updateFlight">
                            <input type="hidden" name="id" value="${item.id }">
                            <button type="button" onclick="editFlight('name${item.id}', 'fromPort${item.id}')">Edit
                                flight
                            </button>
                                <%--<input type="submit" value="Update flight" class="button">--%>
                        </form>
                    </td>
                    <td>
                        <form action="controller">
                            <input type="hidden" name="command" value="deleteFlight">
                            <input type="hidden" name="id" value="${item.id }">
                            <input type="submit" value="Delete flight" class="button">
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="add_content">


        <div>
            <div class="labels">
                <div>Name</div>
                <div>From</div>
                <div>To</div>
                <div>Date</div>
                <div>Time</div>
                <div>Brigade id</div>

            </div>
            <form action="controller" method="post">
                <div class="inputs">
                    <input type="hidden" name="command" value="addFlight">
                    <div>
                        <input type="text" name="name" required="required">
                    </div>
                    <div><input type="text" name="fromPort" required="required"></div>
                    <div><input type="text" name="toPort" required="required"></div>
                    <div><input type="date" name="date" required="required"></div>
                    <div><input type="time" name="time" required="required"></div>
                    <div><input type="text" name="brigadeId" required="required"></div>

                </div>
                <input type="submit" value="Add flight" class="button">
            </form>
        </div>
    </div>
</div>
</body>
</html>