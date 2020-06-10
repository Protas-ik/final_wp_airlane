<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>


<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<script type="text/javascript">

    function show(state, id) {

        document.getElementById('window').style.display = state;
        document.getElementById('wrap').style.display = state;

    }

</script>
<%@ include file="/WEB-INF/jspf/header_admin.jspf" %>
<div onclick="show('none',-1)" id="wrap"></div>
<div id="window"></div>
<div class="main_content">
    <div class="view_content">
        <table>
            <caption>Employees</caption>
            <thead>
            <tr>
                <td>ID</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Profession</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${employees }">
                <tr>
                    <td>${item.id }</td>
                    <td contenteditable="true">${item.firstName }</td>
                    <td contenteditable="true">${item.lastName }</td>
                    <td contenteditable="true">${item.professionId }</td>
                    <td>
                        <form action="controller" method="post">
                            <input type="hidden" name="command" value="removeEmployee">
                            <input type="hidden" name="id" value="${item.id}">
                            <input type="submit" value="remove employee" class="button">

                        </form>
                    </td>
                    <td>
                        <form action="#">
                            <button type="button" onclick="show('block', '3' ) " class="button"> edit emp</button>
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
                <div>First name</div>
                <div>Last name</div>
                <div>Spec</div>
            </div>


            <form action="controller" method="post">
                <div class="inputs">
                    <input type="hidden" name="command" value="addEmployee">
                    <div>
                        <input type="text" name="firstName">
                    </div>
                    <div>
                        <input type="text" name="lastName">
                    </div>
                    <div>
                        <select name="profession">
                            <c:set var="k" value="0"/>
                            <c:forEach var="prof" items="${profs }">
                                <option value="${k}">${prof.name}</option>
                                <c:set var="k" value="${k+1}"/>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <input type="submit" value="Add Employee" class="button">

            </form>
        </div>
    </div>
</div>
</body>
</html>