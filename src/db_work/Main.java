package db_work;

import org.postgresql.ds.PGSimpleDataSource;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String username = JOptionPane.showInputDialog("Enter your DB username");
        JPasswordField passwordField = new JPasswordField();
        int okCxl = JOptionPane.showConfirmDialog(null, passwordField, "Enter your DB password", JOptionPane.OK_CANCEL_OPTION);
        final char[] password = (okCxl == JOptionPane.OK_OPTION) ? passwordField.getPassword() : null;

        var dataSource = new PGSimpleDataSource();

    }
}
