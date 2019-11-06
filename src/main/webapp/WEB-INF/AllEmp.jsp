
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Test</title>
</head>
<body>
    <table style="background-color: azure;text-align: center;border: aqua;" border="1" cellspacing="2" cellpadding="1" >
        <tr>
            <th>工号</th>
            <th>姓名</th>
            <th>部门号</th>
        </tr>
        <c:forEach items="${emp }" var="e">
            <tr>
                <td>${e.eid }</td>
                <td>${e.ename }</td>
                <td>${e.d_fk }</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
