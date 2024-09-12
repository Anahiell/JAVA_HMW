package itstep.learning.data.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String passwordHash;
    private Date deleteDt;

    public User() {
        setId(UUID.randomUUID());
    }
    public User(ResultSet rs) throws RuntimeException {
        try{
            setId(UUID.fromString(rs.getString("id")));
            setName(rs.getString("name"));
            setEmail(rs.getString("email"));
            setPasswordHash(rs.getString("password"));
            Timestamp timestamp = rs.getTimestamp("deleteDt");
            setDeleteDt(timestamp != null ? new Date(timestamp.getTime()) : null);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;

    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Date getDeleteDt() {
        return deleteDt;
    }

    public void setDeleteDt(Date deleteDt) {
        this.deleteDt = deleteDt;
    }
}
