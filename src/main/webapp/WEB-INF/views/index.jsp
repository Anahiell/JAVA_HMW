<%@ page import="itstep.learning.data.dto.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contextPath = request.getContextPath();
String basicPassword = (String) request.getAttribute("basicPassword");
    String fasol = (String) request.getAttribute("fasol");
    String fileName = (String) request.getAttribute("fileName");
    String otp = (String) request.getAttribute("otp");
    List<User> users = (List<User>) request.getAttribute("users");

    HttpSession ses = request.getSession(false);
    String role = (ses != null) ? (String) session.getAttribute("role") : "";

%>
<style>
    .image-container {
        text-align: center; /* Центрируем картинки */
        margin-top: 2rem; /* Отступ сверху */
    }

    .image-container img {
        display: block; /* Обеспечиваем, чтобы каждая картинка была на отдельной строке */
        margin: 10px auto; /* Центрируем изображение и задаем отступ */
        max-width: 100%; /* Картинка будет адаптироваться под ширину экрана */
        height: auto; /* Сохраняем пропорции */
    }
</style>
<% if ("admin".equals(role)) { %>
<div class="forAdmin">
    <h2>Список пользователей:</h2>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Email</th>
            <th>Пароль (хеш)</th>
            <th>Действия</th>
        </tr>
        </thead>
        <tbody>
        <% if (users != null) {
            for (User user : users) { %>
        <tr>
            <td><%= user.getId() %></td>
            <td><%= user.getName() %></td>
            <td><%= user.getEmail() %></td>
            <td><%= user.getPasswordHash() %></td>
            <td>
                <!-- Кнопка удаления -->
                <form action="<%=contextPath%>/user" method="get" style="display:inline;">
                    <input type="hidden" name="userId" value="<%= user.getId() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Удалить</button>
                </form>
            </td>
        </tr>
        <% } } %>
        </tbody>
    </table>
    <form id="signup-form" action="<%=contextPath%>/user" method="post">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Name</span>
            <input name="userName" type="text" class="form-control" placeholder="Username" >
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon2">Email</span>
            <input name="userEmail" type="text" class="form-control" placeholder="Email" >
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon3">Password</span>
            <input name="userPassword" type="password" class="form-control" >
        </div>
        <button class="btn btn-primary">Sign Up</button>
    </form>
</div>
<% } else %><%{%>
    <h3>Whis is site!!!!</h3>
<%} %>
<div class="img-container">
    <img src="https://www.pngkey.com/png/detail/964-9648243_gta-sticker-mission-passed-respect-transparent.png" alt="Gta Sticker - Mission Passed Respect Transparent@pngkey.com">
    <img src="<%=contextPath%>/img/complete.webp" alt="complete">
</div>


