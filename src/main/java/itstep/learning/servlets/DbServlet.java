package itstep.learning.servlets;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import itstep.learning.data.dal.PageVisitDao;
import itstep.learning.data.dal.UserDao;
import itstep.learning.data.dto.PageVisit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Singleton
//@WebServlet("/db")
public class DbServlet extends HttpServlet {
    private final Connection connection;
    private final UserDao userDao;
    private final PageVisitDao pageVisitDao;

    @Inject
    public DbServlet(Connection connection, UserDao userDao, PageVisitDao pageVisitDao) {
        this.connection = connection;
        this.userDao = userDao;
        this.pageVisitDao = pageVisitDao;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> databases = new ArrayList<String>();
        try {
            String sql = "SHOW DATABASES";
            Statement stmt = connection.createStatement(); // SqlCommand
            ResultSet res = stmt.executeQuery(sql);  // SqlDateReader

            while (res.next()) {
                databases.add(res.getString(1));  //!! нолмерация у JDBC начинается с 1

            }
            res.close();
            stmt.close();
            //connection.close();  //TomCat сам закроет
        } catch (SQLException e) {
            req.setAttribute("dbError", e.getMessage());
        }
        try {
            pageVisitDao.installTable();
            userDao.installTable();
            databases.add("Install Users OK");
        } catch (Exception ex) {
            req.setAttribute("Error", ex.getMessage());
        }

        req.setAttribute("databases", databases);
        req.setAttribute("pageBody", "db.jsp");

        String pageUrl = req.getRequestURI();
        PageVisit pageVisit = new PageVisit(0, pageUrl, new Timestamp(System.currentTimeMillis()));
        pageVisitDao.logVisit(pageVisit);

        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}