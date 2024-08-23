<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String error = (String) request.getAttribute("error");
    List<?> database = (List<?>) request.getAttribute("databases");
%>
<h1>Работа с БД</h1>
<ul>
    <li>Устанавливаем СУБД</li>
    <li>Создаем БД и юзера для нее</li>
    <li>Добавляем зависимость(dependencys) к драйверу БД(конектора)</li>
    <li>Смотри комментарии в DbServlet</li>
</ul>
<% if(error == null) {%>
    <p>Работа с БД успешно</p>
<% } else { %>
<b>Возникла ошибка <%=error%></b>
<% } %>
<% if(database == null)
{%>
<p>данные не переданы</p>
<%} else {
    for (Object object : database) {%>
       <p><%=object.toString()%></p>

<% } } %>


