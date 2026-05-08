package db_work;

import org.postgresql.ds.PGSimpleDataSource;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter your DB username");
        JPasswordField passwordField = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, passwordField, "Enter your DB password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? passwordField.getPassword() : null;

        var dataSource = new PGSimpleDataSource();
        dataSource.setDatabaseName("music");

        try (Connection conn = dataSource.getConnection(username, String.valueOf(password))) {
            System.out.println("Successfully connected to music database.");
            Arrays.fill(password, ' ');
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }
}
