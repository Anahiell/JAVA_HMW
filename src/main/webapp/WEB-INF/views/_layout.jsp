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
    <link rel="stylesheet" href="<%=contextPath%>/css/site.css"/>

</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><img src="<%=contextPath%>/img/prodaction.png" alt="LOGO" class="d-inline-block align-text-top"></a>
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
<article class="container">
    <!-- Content here -->
    <jsp:include page="<%=pageBody%>"/>
</article>
<div class="spacer"></div>
<footer class="footer">
    <img src="<%=contextPath%>/img/footer.jpg" alt="Logo" class="d-inline-block align-text-top">
    <p>&copy; 2024 Your Website. All rights reserved.</p>
    <img src="<%=contextPath%>/img/footer2.webp" alt="Logo" class="d-inline-block align-text-top">

</footer>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>
