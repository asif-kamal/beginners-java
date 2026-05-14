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
                System.out.println("Maybe we should add this record");
                insertRecord(statement, tableName, new String[]{columnName}, new String[]{columnValue});
            } else{
                deleteRecord(statement, tableName, columnName, columnValue);
            }
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
        int recordsDeleted = stmt.getUpdateCount();
        if (recordsDeleted > 0) {
            executeSelect(stmt, tableName, columnName, columnValue);
        }
        return recordsDeleted > 0;
    }
}
