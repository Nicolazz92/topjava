<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="index.html">Home</a></h2>
<h2>Meal list</h2>

<table border="1">
    <tr style="font-style: italic">
        <td>Дата</td>
        <td>Описание</td>
        <td>Калории</td>
    </tr>
    <c:forEach items="${requestScope.fullList}" var="current">
        <tr style="color:
        <c:if test="${current.isExceed()}">red</c:if>
        <c:if test="${!current.isExceed()}">green</c:if>">

            <td><c:out value="${fn:replace(current.getDateTime(), 'T', ' ')}"/></td>
            <td><c:out value="${current.getDescription()}"/></td>
            <td><c:out value="${current.getCalories()}"/></td>
            <td>
                <a href="delete?id=${current.getId()}">Delete</a>
            </td>

        </tr>
    </c:forEach>

    <form action="<c:url value="/add"/>" method="POST">
        <td colspan="1">
            <label>
                <input name="localdate" type="datetime-local">
            </label>
        </td>
        <td colspan="1">
            <label>
                <input name="description" type="text">
            </label>
        </td>
        <td colspan="1">
            <label>
                <input name="calories" type="number">
            </label>
        </td>
        <td colspan="2">
            <input type="submit" value="Отправить">
        </td>
    </form>

</table>
</body>
</html>
