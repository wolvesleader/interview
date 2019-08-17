package com.quincy.java.kaoshi.utils;

import java.sql.*;

/**
 * author:quincy
 * Date:2019-08-12
 */
public class JdbcUtils {

        private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

        private static final String URL = "jdbc:mysql://localhost:3306/exam";
        private static final String USER = "root";
        private static final String PASSWORD = "java";

        static {
            try {
                Class.forName(DRIVERCLASS);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        public static Connection getConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
        //4.关闭连接close（ResultSet rs,Statemment st，Connection connection）
        public static void close(ResultSet resultset, Statement statement, Connection connection) {
            if (resultset != null) {
                try {
                    resultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        public static void close(Statement statement, Connection connection) {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



}
