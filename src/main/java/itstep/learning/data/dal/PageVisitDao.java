package itstep.learning.data.dal;

import com.google.inject.Inject;
import itstep.learning.data.dto.PageVisit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PageVisitDao {
    private final Connection connection;

    @Inject
    public PageVisitDao(Connection connection) {
        this.connection = connection;
    }

    public void logVisit(PageVisit visit) {
        String sql = "INSERT INTO page_visits (page_url, visit_date) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, visit.getPageUrl());
            stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));  // текущее время
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void installTable() {
        String sql = "CREATE TABLE IF NOT EXISTS page_visits (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "page_url VARCHAR(2048) NOT NULL," +
                "visit_date TIMESTAMP NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<PageVisit> getAllVisits() {
        List<PageVisit> visits = new ArrayList<>();
        String sql = "SELECT * FROM page_visits ORDER BY visit_date DESC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String pageUrl = rs.getString("page_url");
                Timestamp visitDate = rs.getTimestamp("visit_date");
                visits.add(new PageVisit(id, pageUrl, visitDate));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return visits;
    }
}
