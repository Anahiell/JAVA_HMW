package itstep.learning.data.dal;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import itstep.learning.services.hash.HashService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
//    public boolean signupUser(User user) {
//        String sql = "INSERT INTO users (id,name,email,avatar,deleteDt,password) " +
//                "VALUES (?,?,?,?,NULL,?)";
//        try (PreparedStatement prep = connection.prepareStatement(sql)) {
//            UUID id = user.getId();
//            if (id != null) {
//                user.setId(UUID.randomUUID());
//            }
//            prep.setString(1, user.getId().toString());
//            prep.setString(2, user.getName());
//            prep.setString(3, user.getEmail());
//            prep.setString(4, hashService.diggest(user.getPassword()));
//            prep.executeUpdate();
//            return true;
//        } catch (Exception ex) {
//            logger.warning(ex.getMessage());
//            return false;
//        }
//    }
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
}
