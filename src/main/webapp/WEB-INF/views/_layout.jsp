<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="itstep.learning.data.dto.User" %>
<%@ page import="java.util.List" %>
<%
    String contextPath = request.getContextPath();
    String pageBody = (String) request.getAttribute("pageBody");
    if (pageBody == null) {
        pageBody = "wtf.jsp";
    }
    HttpSession ses = request.getSession(false);
    String role = (ses != null) ? (String) ses.getAttribute("role") : "guest";
    String username = (ses != null) ? (String) ses.getAttribute("username") : null;
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
            <div class="d-flex ms-auto">
                <% if ("user".equals(role)) { %>
                <span class="ms-3">Привет, <%= username %>!</span>
                <a href="<%=contextPath%>/logout" class="btn btn-outline-danger ms-3">Logout</a>
                <% } else { %>
                <% if ("admin".equals(role)) { %>
                <span class="ms-3">Привет, админ!</span>
                <a href="<%=contextPath%>/logout" class="btn btn-outline-danger ms-3">Logout</a>
                <% } else { %>
                <span class="ms-3">Привет, гость!</span>
                <button class="btn btn-outline-primary d-flex" data-bs-toggle="modal" data-bs-target="#signInModal">
                    <i class="bi bi-person-circle"></i> Sign In
                </button>
                <% } %>


                <% } %>
            </div>
        </div>
    </div>
</nav>

<!-- Sign In Modal -->
<div class="modal fade" id="signInModal" tabindex="-1" aria-labelledby="signInModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="signInModalLabel">Sign In</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Sign In Form -->
                <form id="signInForm" action="<%=contextPath%>/sign" method="post">
                    <div class="mb-3">
                        <label for="inputUserEmail" class="form-label">Email address</label>
                        <input name="inputUserEmail" type="email" class="form-control" id="inputUserEmail" placeholder="name@example.com">
                    </div>
                    <div class="mb-3">
                        <label for="inputUserPassword" class="form-label">Password</label>
                        <input name="inputUserPassword" type="password" class="form-control" id="inputUserPassword" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary">Sign In</button>
                </form>

                <!-- Sign Up Form (hidden by default) -->
                <form id="signUpForm" style="display: none;" action="<%=contextPath%>/user" method="post">
                    <div class="mb-3">
                        <span class="input-group-text" id="basic-addon1">Name</span>
                        <input name="userName" type="text" class="form-control" placeholder="Username" >
                    </div>
                    <div class="mb-3">
                        <span class="input-group-text" id="basic-addon2">Email</span>
                        <input name="userEmail" type="text" class="form-control" placeholder="Email" >
                    </div>
                    <div class="mb-3">
                        <span class="input-group-text" id="basic-addon3">Password</span>
                        <input name="userPassword" type="password" class="form-control"  >
                    </div>
                    <button type="submit" class="btn btn-success">Sign Up</button>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-link" id="toggleSignUp">Don't have an account? Sign Up</button>
            </div>
        </div>
    </div>
</div>

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
<script src="<%=contextPath%>/js/site.js"></script>
</body>
</html>
