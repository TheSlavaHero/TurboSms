<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>New Task</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <form role="form" class="form-horizontal" action="/contact/add" method="post">
                        <h3>New Task</h3>
                        <select class="selectpicker form-control form-group" name="group">
                            <option value="-1">Default</option>
                            <c:forEach items="${groups}" var="group">
                                <option value="${group.id}">${group.name}</option>
                            </c:forEach>
                        </select>
                        <input class="form-control form-group" type="text" name="name" placeholder="Name">
                        <input class="form-control form-group" type="text" name="surname" placeholder="Message to send">
                        <input class="form-control form-group" type="text" name="phone" placeholder="Phone">
                        <input class="form-control form-group" type="datetime-local" name="email" placeholder="E-mail">
                    <input type="submit" class="btn btn-primary" value="Add">
            </form>
        </div>

        <script>
            $('.selectpicker').selectpicker();
        </script>
    </body>
</html>