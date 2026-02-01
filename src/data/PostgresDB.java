package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class PostgresDB {
    private static PostgresDB instance;
    private Connection connection;

    private PostgresDB() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db", "postgres", "root");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
    }

    public static PostgresDB getInstance() {
        if (instance == null) {
            instance = new PostgresDB();
        } else {
            try {
                if (instance.getConnection().isClosed()) {
                    instance = new PostgresDB();
                }
            } catch (SQLException e) {
                instance = new PostgresDB();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
