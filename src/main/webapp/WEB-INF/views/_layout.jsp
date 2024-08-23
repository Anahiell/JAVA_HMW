<%@ page contentType="text/html;charset=UTF-8" %>
<%
    String contextPath = request.getContextPath();
    String pageBody = (String) request.getAttribute("pageBody");
    if (pageBody == null) {
        pageBody = "wtf.jsp";
    }


%>
<html>
<head>
    <title>Java Web Home Work</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
    <style>
        /* Общий стиль для всей страницы, чтобы футер оставался внизу */
        html, body {
            height: 100%;
            margin: 0;
        }
        /* Контейнер для основного содержимого */
        .content-wrapper {
            min-height: 100%;
            display: flex;
            flex-direction: column;
        }
        /* Основной контент страницы */
        .main-content {
            flex: 1;
        }
        /* Стиль для футера */
        .footer {
            background-color: #f8f9fa;
            padding: 1rem;
            text-align: center;
            position: relative;
            bottom: 0;
            width: 100%;
        }
        .footer img {
            position: absolute;
            left: 10px;
            bottom: 10px;
            width: 50px; /* Размер изображения можно настроить */
            height: auto;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=contextPath%>">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=contextPath%>/db">Data Base</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<%=contextPath%>/logs">Logs</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="container">
    <!-- Content here -->
    <jsp:include page="<%=pageBody%>"/>
</div>

<footer class="footer">
    <img src="" alt="Logo" style="float: left; width: 50px; height: auto;">
    <p>&copy; 2024 Your Website. All rights reserved.</p>
</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
