<%@ include file="/WEB-INF/jspf/page.jspf" %>
<%@ include file="/WEB-INF/jspf/taglib.jspf" %>

<html>
    <head>
        <title>
            ${title}
        </title>
        <meta charset=UTF-8"/>
        <link rel="stylesheet" href="css/loginStyle.css"/>
    </head>
    <body>

    <div id="wrapper">
        <section>

            <form class="box" action="controller" method="post">
                <h1>Login</h1>
                <input type="hidden" name="command" value="login">
                <input name="login" type="text" id="login"/>
                <input name="password" type="password" id="password"/>
                <input type="submit" value="Log In" class="button"/>
            </form>

        </section>
    </div>
</body>
</html>