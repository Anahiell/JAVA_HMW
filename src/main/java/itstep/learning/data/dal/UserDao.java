package itstep.learning.data.dal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import itstep.learning.data.dto.User;
import itstep.learning.services.hash.HashService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton
public class UserDao {
    private final Connection connection;
    private final Logger logger;
    private final HashService hashService;

    @Inject
    UserDao(Connection connection, Logger logger, @Named("Md5") HashService hashService) {
        this.connection = connection;
        this.logger = logger;
        this.hashService = hashService;
    }

    public boolean signupUser(User user) {
        if (isEmailTaken(user.getEmail())) {
            logger.warning("Email already taken: " + user.getEmail());
            return false; // Email уже существует
        }

        String sql = "INSERT INTO users (id, name, email, deleteDt, password) VALUES (?, ?, ?,NULL, ?)";
        try (PreparedStatement prep = connection.prepareStatement(sql)) {
            if (user.getId() == null) {
                user.setId(UUID.randomUUID());
            }
            prep.setString(1, user.getId().toString());
            prep.setString(2, user.getName());
            prep.setString(3, user.getEmail());
            prep.setString(4, hashService.diggest(user.getPasswordHash()));
            prep.executeUpdate();
            return true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error during user signup", ex);
            return false;
        }
    }
    public void installTable()
    {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id         CHAR(36)        PRIMARY KEY DEFAULT( UUID() )," +
                "name       VARCHAR(256)    NOT NULL," +
                "email      VARCHAR(256)    NOT NULL," +
                "deleteDt   TIMESTAMP       NULL," +
                "password   CHAR(32)        NOT NULL)" +
                "ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
        try
                (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void dropTable(){
        String sql = "DROP TABLE IF EXISTS users";
        try(Statement stmt = connection.createStatement())
        {
            stmt.executeUpdate(sql);
        }catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT id, name, email, deleteDt, password FROM users";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                User user = new User(rs);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }
    private boolean isEmailTaken(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement prep = connection.prepareStatement(sql)) {
            prep.setString(1, email);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException ex) {
            logger.warning("Error checking email existence: " + ex.getMessage());
        }
        return false;
    }
    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT password FROM users WHERE email = ? AND deleteDt IS NULL";
        try (PreparedStatement prep = connection.prepareStatement(sql)) {
            prep.setString(1, email);
            ResultSet rs = prep.executeQuery();
            if (rs.next()) {
                String storedPasswordHash = rs.getString("password");
                return hashService.check(password, storedPasswordHash);
            }
        } catch (SQLException ex) {
            logger.warning("Error during user authentication: " + ex.getMessage());
        }
        return false; // Возвращаем false, если пользователь не найден или пароли не совпадают
    }

    public void deleteUserByEmail(String email) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Пользователь с email " + email + " не найден.");
            }
        }
    }
    public void deleteUser(UUID userId) throws SQLException {
        // Найти email по UUID
        String email = getEmailByUserId(userId);

        // Удалить пользователя по email
        deleteUserByEmail(email);
    }
    public String getEmailByUserId(UUID userId) throws SQLException {
        String sql = "SELECT email FROM users WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userId.toString());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                } else {
                    throw new SQLException("Пользователь с ID " + userId + " не найден.");
                }
            }
        }
    }
    }

