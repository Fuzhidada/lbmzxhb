package com.example.work;

import java.sql.*;

public class OdbcConnection {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST=mis-scan)(PORT=1521)))(CONNECT_DATA=(SERVICE_NAME=odsorcl)))";
            String user = "cbsqry";
            String password = "cbsqry123";

            Class.forName("oracle.jdbc.OracleDriver");//加载数据驱动
            conn = DriverManager.getConnection(url, user, password);// 连接数据库

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("加载数据库驱动失败");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("连接数据库失败");
        }
        return conn;
    }

    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}