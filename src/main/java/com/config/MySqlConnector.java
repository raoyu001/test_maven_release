
package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector{


    public static ThreadLocal<Connection> connectionMap = new ThreadLocal<>();


    public static final String url = "jdbc:mysql://localhost:3306/javademo?user=root&password=root&useUnicode=true&characterEncoding=UTF8";
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection init(){
        try {
            Connection connection = DriverManager.getConnection(url);
            connectionMap.set(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connectionMap.get();
    }

    public Connection getConnection(){
        Connection connection = connectionMap.get();
        if(connection == null){
            return init();
        }
        return connection;
    }

    public static void main(String[] args) {

    }
}

