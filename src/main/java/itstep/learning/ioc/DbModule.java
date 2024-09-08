package itstep.learning.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DbModule extends AbstractModule {
    private Connection currentConnection;
    private Driver mysqlDriver = null;

    @Provides
    private Connection getConnection() {
        if (currentConnection == null) {
            Map<String, String> ini = new HashMap<>();

            try (InputStream iniStream = this.getClass().getClassLoader().getResourceAsStream("db.ini")) {
                String iniContent = readStream(iniStream);
                String[] lines = iniContent.split("\n");
                for (String line : lines) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        ini.put(parts[0].trim(), parts[1].trim());
                    }
                }
            } catch (IOException ex) {
                System.err.println("Cold not load db ini file" + ex.getMessage());
            }
            try {
                mysqlDriver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(mysqlDriver);
                currentConnection = DriverManager.getConnection(
                        ini.get("connectionString"),
                        ini.get("user"),
                        ini.get("password")
                );
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }


        }
        return currentConnection;
    }

    private String readStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuilder = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 16];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            byteBuilder.write(buffer, 0, length);
        }
        return byteBuilder.toString(StandardCharsets.UTF_8.name());
    }

    public void close() {
        try {
            if (currentConnection != null) {
                currentConnection.close();
            }
            if (mysqlDriver != null) {
                DriverManager.deregisterDriver(mysqlDriver);
            }
        } catch (Exception ignore) {
        }
    }
}
