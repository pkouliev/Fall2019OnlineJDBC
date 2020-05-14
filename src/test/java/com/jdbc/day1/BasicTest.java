package com.jdbc.day1;

import java.sql.*;

public class BasicTest {

    public static void main(String[] args) throws SQLException {
        String URL = "jdbc:oracle:thin:@54.198.155.113:1521:xe";
        String username = "hr";
        String password = "hr";

        // to establish connection with a database
        Connection connection = DriverManager.getConnection(URL, username, password);

        // ResultSet.TYPE_SCROLL_INSENSITIVE
        // The constant indicating the type for a <code>ResultSet</code> object
        //     * that is scrollable but generally not sensitive to changes to the data
        //     * that underlies the <code>ResultSet</code>.
        //ResultSet.CONCUR_READ_ONLY : The constant indicating the concurrency mode for a ResultSet</code> object
        // that may NOT be updated.
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        // in executeQuery method we provide query as a parameter
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

        // resultSet.next() - returns true until it reaches last raw.
        // and jumps to next row if there is some raw with data
//        while (resultSet.next()) {
//            // get data from some column (2nd in this case) for every row
//            System.out.println(resultSet.getString(2));
//        }

        resultSet.beforeFirst();

        DatabaseMetaData databaseMetaData = connection.getMetaData();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        while (resultSet.next()) {
            // System.out.println(resultSet.getString("salary"));
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " "
                    + resultSet.getString(3));
        }

        System.out.println("JDBC driver: " + databaseMetaData.getDriverName());
        System.out.println("JDBC driver version: " + databaseMetaData.getDriverVersion());
        System.out.println("Database name: " + databaseMetaData.getDatabaseProductName());
        System.out.println("Database version: " + databaseMetaData.getDatabaseProductVersion());

        System.out.println("Number of columns: " + resultSetMetaData.getColumnCount());
        System.out.println("Label of 1st column: " + resultSetMetaData.getColumnLabel(1));
        System.out.println("Data type of first column: " + resultSetMetaData.getColumnTypeName(1));

        System.out.println("##################################################################");

        resultSet.beforeFirst();

        // this loop will loop through columns
        for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
            System.out.printf("%-15s", resultSetMetaData.getColumnName(columnIndex));
            /**
             * The % means that what follows is an argument that will be formatted.
             * 15 fills the string up to a length of 15 characters (adding spaces at the end).
             * Finally the s means, that you are formatting a string.
             */
        }
        System.out.println("");

        // iterate raws
        while (resultSet.next()) {
            // iterate columns
            for (int columnIndex = 1; columnIndex <= resultSetMetaData.getColumnCount(); columnIndex++) {
                System.out.printf("%-15s", resultSet.getString(columnIndex));
            }
            System.out.println("");
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
