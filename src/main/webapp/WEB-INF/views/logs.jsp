<%@ page import="itstep.learning.servlets.LogServlet" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="container">
    <h1>Visit Log</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Visit Time</th>
            <th>Page URL</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<itstep.learning.servlets.LogServlet.Visit> visits = (List<LogServlet.Visit>) request.getAttribute("visits");
            if (visits != null && !visits.isEmpty()) {
                for (itstep.learning.servlets.LogServlet.Visit visit : visits) {
        %>
        <tr>
            <td><%= visit.getId() %></td>
            <td><%= visit.getVisitTime() %></td>
            <td><%= visit.getPageUrl() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3">No visits recorded.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>