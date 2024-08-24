package itstep.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/logs")
public class LogServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3308/JAVA_HMW?useUnicode=true&characterEncoding=utf8";
    private static final String DB_USER = "admin";
    private static final String DB_PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        Driver mysqlDriver = null;
        List<Visit> visits = new ArrayList<>();

        String pageUrl = req.getRequestURI();

        int maxLength = 2048; // или размер вашего столбца
        if (pageUrl.length() > maxLength) {
            pageUrl = pageUrl.substring(0, maxLength);
        }
        // Запись посещения в базу данных
        try {
            mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "INSERT INTO page_visits (page_url) VALUES (?)";

            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pageUrl);
            stmt.executeUpdate();
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            mysqlDriver = new com.mysql.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "select * from page_visits";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String time = rs.getString("visit_time");
                String url = rs.getString("page_url");
                visits.add(new Visit(id, time, url));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Устанавливаем атрибуты для передачи в _layout.jsp
        req.setAttribute("visits", visits);
        req.setAttribute("pageBody", "logs.jsp");
        req.getRequestDispatcher("/WEB-INF/views/_layout.jsp").forward(req, resp);
    }

    public static class Visit {
        private final int id;
        private final String visitTime;
        private final String pageUrl;

        public Visit(int id, String visitTime, String pageUrl) {
            this.id = id;
            this.visitTime = visitTime;
            this.pageUrl = pageUrl;
        }

        public int getId() {
            return id;
        }

        public String getVisitTime() {
            return visitTime;
        }

        public String getPageUrl() {
            return pageUrl;
        }
    }
}
