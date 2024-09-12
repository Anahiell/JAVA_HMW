
<%@ page import="itstep.learning.servlets.LogServlet" %>
<%@ page import="java.util.List" %>
<%@ page import="itstep.learning.data.dto.PageVisit" %>
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
            List<PageVisit> visits = (List<PageVisit>) request.getAttribute("visits");
            if (visits != null && !visits.isEmpty()) {
                for (PageVisit visit : visits) {
        %>
        <tr>
            <td><%= visit.getId() %></td>
            <td><%= visit.getVisitDate() %></td>
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