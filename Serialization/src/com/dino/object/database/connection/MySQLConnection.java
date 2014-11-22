/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dino.object.database.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author dmunoz
 */
public class MySQLConnection {

    private static String driver = "org.gjt.mm.mysql.Driver";
    private static String url = "jdbc:mysql://localhost/ejemplo";
    private static String username = "root";
    private static String password = "admin";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        return conn;
    }

}
