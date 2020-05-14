package com.jdbc.day1;

import java.sql.*;

public class InsertAndDeleteTest {

    static String URL = "jdbc:oracle:thin:@54.198.155.113:1521:xe";
    static String username = "hr";
    static String password = "hr";

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection(URL, username, password);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String INSERT_QUERY = "INSERT INTO employees VALUES(223, 'Hasan', 'Mammadov', 'hasan@cybertek.com', " +
                "               '777-777-7777', SYSDATE, 'SDET', 15000, 0, NULL, NULL)";
        String DELETE_QUERY = "DELETE FROM employees WHERE employee_id = 223";

        ResultSet resultSet = statement.executeQuery("DELETE FROM employees WHERE employee_id = 223");

//        try (Connection connection = DriverManager.getConnection(URL, username, password);
//             Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//             ResultSet resultSet = statement.executeQuery("DELETE FROM employees WHERE employee_id = 223");
//        ) {
//
//        }

        resultSet.close();
        statement.close();
        connection.close();


    }
}
