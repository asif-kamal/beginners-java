package db_work;

import java.sql.*;

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

    private static boolean printRecords(ResultSet rs) throws SQLException {
        boolean foundData = false;

        var meta = rs.getMetaData();

        System.out.println("==========================");

        for (int i = 1; i <= meta.getColumnCount(); i++) {
            System.out.printf("%-15s", meta.getColumnName(i).toUpperCase());
        }
        System.out.println();

        while (rs.next()) {
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.printf("%-15s", rs.getString(i));
            }
            System.out.println();
            foundData = true;
        }

        return foundData;
    }
}
