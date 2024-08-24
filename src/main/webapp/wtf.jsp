
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>Something Wrong</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .background-image {
            background-size: cover;
            background-position: center;
            height: 50%; /* Занимает половину высоты экрана */
            width: 50%; /* Занимает половину ширины экрана */
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .background-image img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain; /* Сохраняет пропорции картинки */
        }
        .btn {
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 18px;
            border-radius: 5px;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="background-image">
    <img src="<%=contextPath%>/wtf.jsp" alt="Something Wrong Image"/>
</div>
<form action="" method="get">
    <button type="submit" class="btn">Вернуться</button>
</form>
</body>
</html>
