package db_work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicDML {

    public static void main(String[] args) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/music",
                System.getenv("USER"),
                System.getenv("PASSWORD"));
             Statement statement = conn.createStatement()
        ) {
            String artist = "Elf";
            String query = "SELECT * FROM artists WHERE artist_name='%s'".formatted(artist);
            boolean result = statement.execute(query);
            System.out.println("Result: " + result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
