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
            String tableName = "artists";
            String columnName = "artist_name";
            String columnValue = "Bob Dylan";

            if(!executeSelect(statement, tableName, columnName, columnValue)) {
                insertArtistAlbum(statement, columnValue, columnValue);
            } else{
                //boolean deleted = deleteRecord(statement, tableName, columnName, columnValue);
                //System.out.println("Deleted record: " + deleted);
                updateRecord(statement, tableName, columnName, columnValue, columnName, columnValue.toUpperCase());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    private static boolean executeSelect(Statement stmt, String table, String columnName, String columnValue)
            throws SQLException {

        String query = "SELECT * FROM %s WHERE %s='%s'".formatted(table, columnName, columnValue);
        var rs = stmt.executeQuery(query);
        if (rs != null) {
            return printRecords(rs);
        }
        return false;

    }
    private static boolean insertRecord(Statement stmt, String tableName, String[] columnNames, String[] columnValues) throws SQLException {

        String colNames = String.join(", ", columnNames);
        String colValues = String.join("','", columnValues);
        String query = "INSERT INTO %s (%s) VALUES ('%s')".formatted(tableName, colNames, colValues);
        System.out.println(query);
        boolean insertResult = stmt.execute(query);
        int recordsInserted = stmt.getUpdateCount();
        if (recordsInserted > 0) {
            executeSelect(stmt, tableName, columnNames[0], columnValues[0]);
        }
        return recordsInserted > 0;
    }

    private static boolean deleteRecord(Statement stmt, String tableName, String columnName, String columnValue) throws SQLException {

        String query = "DELETE FROM %s WHERE %s='%s'".formatted(tableName, columnName, columnValue);
        System.out.println(query);
        stmt.execute(query);
        int recordsDeleted = stmt.getUpdateCount();
        if (recordsDeleted > 0) {
            executeSelect(stmt, tableName, columnName, columnValue);
        }
        return recordsDeleted > 0;
    }

    private static boolean updateRecord(Statement stmt, String tableName,
                                        String matchedColumn, String matchedValue,
                                        String updatedColumn, String updatedValue) throws SQLException {

        String query = "UPDATE %s SET %s='%s' WHERE %s='%s'".formatted(tableName, updatedColumn, updatedValue,
                matchedColumn, matchedValue);
        System.out.println(query);
        stmt.execute(query);
        int recordsUpdated = stmt.getUpdateCount();
        if (recordsUpdated > 0) {
            executeSelect(stmt, tableName, updatedColumn, updatedValue);
        }
        return recordsUpdated > 0;
    }

    private static void insertArtistAlbum(Statement stmt, String artistName, String albumName) throws SQLException {
        String artistInsert = "INSERT INTO artists (artist_name) VALUES (%s)".formatted(stmt.enquoteLiteral(artistName));
        System.out.println(artistInsert);
        stmt.execute(artistInsert, Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        int artistId = (rs != null && rs.next()) ? rs.getInt(1) : -1;
        String albumInsert = ("INSERT INTO albums (album_name, artist_id)" + " VALUES (%s, %d)")
                .formatted(stmt.enquoteLiteral(albumName), artistId);
        System.out.println(albumInsert);
        stmt.execute(albumInsert, Statement.RETURN_GENERATED_KEYS);
        rs = stmt.getGeneratedKeys();
        int albumId = (rs != null && rs.next()) ? rs.getInt(1) : -1;

        String[] songs = new String[]{
                "You're No Good",
                "Talkin' New York",
                "In My Time Of Dyin'",
                "Man Of Constant Sorrow",
                "Fixin' To Die",
                "Pretty Peggy-O",
                "Highway 51 Blues"
        };

        String songInsert = "INSERT INTO songs " + "(track_number, song_title, album_id) VALUES (%d, %s, %d)";

        for (int i = 0; i < songs.length; i++) {
            String songQuery = songInsert.formatted(i + 1, stmt.enquoteLiteral(songs[i]), albumId);
            System.out.println(songQuery);
            stmt.execute(songQuery);
        }
        executeSelect(stmt, "albumview", "album_name", "Bob Dylan");
    }
}
