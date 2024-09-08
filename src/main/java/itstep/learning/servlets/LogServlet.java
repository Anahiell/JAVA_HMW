package itstep.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.data.dal.PageVisitDao;
import itstep.learning.data.dto.PageVisit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
//@WebServlet("/logs")
public class LogServlet extends HttpServlet {
    private final PageVisitDao pageVisitDao;

    @Inject
    public LogServlet(PageVisitDao pageVisitDao) {
        this.pageVisitDao = pageVisitDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        pageVisitDao.installTable();
        List<PageVisit> visits = pageVisitDao.getAllVisits();

        req.setAttribute("visits", visits);
        req.setAttribute("pageBody", "logs.jsp");
        req.getRequestDispatcher("/WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}
