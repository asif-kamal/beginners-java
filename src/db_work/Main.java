package db_work;

import org.postgresql.ds.PGSimpleDataSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
//        String username = JOptionPane.showInputDialog("Enter your DB username");
//        JPasswordField passwordField = new JPasswordField();
//        int okCxl = JOptionPane.showConfirmDialog(null, passwordField, "Enter your DB password", JOptionPane.OK_CANCEL_OPTION);
//        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? passwordField.getPassword() : null;
//
//        var dataSource = new PGSimpleDataSource();
//        dataSource.setDatabaseName("music");
//
//        try (Connection conn = dataSource.getConnection(username, String.valueOf(password))) {
//            System.out.println("Successfully connected to music database.");
//            Arrays.fill(password, ' ');
//        } catch (SQLException e) {
//            throw new RuntimeException();
//        }

        Properties props = new Properties();

        try {
            props.load(Files.newInputStream(Path.of("music.properties"),
                    StandardOpenOption.READ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        var dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName(props.getProperty("databaseName"));

        try (var connection = dataSource.getConnection(System.getenv("USER"),
                System.getenv("PASSWORD"))) {
            System.out.println("Connected to database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
