package itstep.learning.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
public class HomeServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            //ViewData["fromServlet"] = "HomeServlet"
            req.setAttribute("fromServlet", "HomeServlet");
            req.setAttribute("pageBody", "index.jsp");
            String pageUrl = req.getRequestURI();
            Connection connection = null;
            Driver mysqlDriver = null;
            int maxLength = 2048; // или размер вашего столбца
            if (pageUrl.length() > maxLength) {
                pageUrl = pageUrl.substring(0, maxLength);
            }
            // Запись посещения в базу данных
            try {
                mysqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mysqlDriver);
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3308/JAVA_HMW?useUnicode=true&characterEncoding=utf8",
                        "admin",
                        "admin");
                String sql = "INSERT INTO page_visits (page_url) VALUES (?)";

                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, pageUrl);
                stmt.executeUpdate();
                stmt.close();
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            //return view("index.jsp")
            req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
        }
    }

