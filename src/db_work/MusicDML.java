package db_work;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicDML {

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/music",
                System.getenv("USER"),
                System.getenv("PASSWORD"));
             Statement statement = conn.createStatement()
        ) {
            String artist = "Neil Young";
            String query = "SELECT * FROM artists WHERE artist_name='%s'".formatted(artist);
            boolean result = statement.execute(query);
            var rs = statement.getResultSet();
            boolean found = (rs!=null && rs.next());
            System.out.println("Result: " + result);
            System.out.println("Artist was " + (found? "found" : "not found"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
