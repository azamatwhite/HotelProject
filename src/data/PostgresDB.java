package data;

import java.sql.Connection;
import java.sql.DriverManager;

public class PostgresDB {
public static Connection getConnection(){
   try {
       Class.forName("org.postgresql.Driver");

       return DriverManager.getConnection("jdbc:postgresql://localhost:5432/hoteldb", "postgres", "root");

   } catch (Exception e) {
    System.out.println("Error: Cannot get connection with DB");

            System.out.println(e.getMessage());

            throw new RuntimeException(e);

        }

    }
}
