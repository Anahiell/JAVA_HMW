package itstep.learning.data.dto;

import java.sql.ResultSet;
import java.util.Date;
import java.util.UUID;

public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Date deleteDt;

    public User() {
        setId(UUID.randomUUID());
    }
    public User(ResultSet rs) throws RuntimeException {
        try{
            setId(UUID.fromString(rs.getString("id")));
            setName(rs.getString("name"));
            setEmail(rs.getString("email"));
            setPassword(rs.getString("password"));
            setDeleteDt(new Date(rs.getLong("delete_dt")));
        }
        catch (Exception ex) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDeleteDt() {
        return deleteDt;
    }

    public void setDeleteDt(Date deleteDt) {
        this.deleteDt = deleteDt;
    }
}
