package itstep.learning.servlets;

import com.google.inject.Singleton;

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
//@WebServlet("/db")
public class DbServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //подключение к БД
        //JDBC (~ADO/PDO) - Java Data Base Connected книфицированная технология доступа к БД
        //подключение - инструкция - результат
        Connection connection = null;
        Driver mysqlDriver = null;
        try {
            mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3308/JAVA_HMW?useUnicode=true&characterEncoding=utf8",
                    "admin",
                    "admin"
            );
            String sql = "SHOW DATABASES";
            Statement stmt = connection.createStatement(); // SqlCommand
            ResultSet res = stmt.executeQuery(sql);  // SqlDateReader
            List<String> databases = new ArrayList<String>();
            while (res.next()) {
                databases.add(res.getString(1));  //!! нолмерация у JDBC начинается с 1

            }
            req.setAttribute("databases", databases);
            res.close();
            stmt.close();
            //connection.close();  //TomCat сам закроет
        } catch (SQLException e) {
            req.setAttribute("Error Data Base", e.getMessage());
        }


        req.setAttribute("pageBody", "db.jsp");
        String pageUrl = req.getRequestURI();
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
        req.getRequestDispatcher("WEB-INF/views/_layout.jsp").forward(req, resp);
    }
}