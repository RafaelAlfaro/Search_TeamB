/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */
package com.jalasoft.search.commons;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class handles the database connection
 *
 * @author rafael alfaro
 * @version 1.0
 */
public class DBConnection {
    private static DBConnection dbcon;
    private static Connection connection;
    private final static String databaseName = "searchCriteria.db";
    private final static String url = "jdbc:sqlite:";
    private final static String driver = "org.sqlite.JDBC";
    private final static String tableName = "criteria";

    /**
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private DBConnection() throws SQLException, ClassNotFoundException {
        init();
    }

    /**
     * Gets Table Name
     *
     * @return
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Initialize the data base if the database doesn't exist the method create ones.
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void init() throws ClassNotFoundException, SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS " + getTableName() + " (id integer PRIMARY KEY, criteria varchar(200));";
        loadDriver();
        loadConnection();
        Statement state = connection.createStatement();
        state.execute(sql);
    }

    /**
     * Gets the connection
     *
     * @return Connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * Loads the specified driver
     */
    private static void loadDriver() {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            errorHandler("Failed to load the driver " + driver, e);
        }
    }

    /**
     * Gets the formated URL to connect to the database
     *
     * @return String url formated
     */
    private static String getUrl() {
        return url + databaseName;
    }

    /**
     * Loads the connection into the right property
     */
    private static void loadConnection() {
        try {
            connection = DriverManager.getConnection(getUrl());
        } catch (SQLException e) {
            errorHandler("Failed to connect to the database " + getUrl(), e);
        }
    }

    /**
     * Gets the instance for the singleton
     *
     * @return dbcon
     * @throws SQLException           SQLException
     * @throws ClassNotFoundException ClassNotFoundException
     */
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
        if (dbcon == null) {
            dbcon = new DBConnection();
        }
        return dbcon;
    }

    /**
     * Closes the connection to the database
     **/

    public static void closeConnection() {
        if (connection == null) {
            errorHandler("No connection found", null);
        } else {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                errorHandler("Failed to close the connection", e);
            }
        }
    }

    /**
     * Handles errors
     *
     * @param message message to put into the logfile
     * @param e       Exception
     */
    private static void errorHandler(String message, Exception e) {
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, message);
        if (e != null) {
            LogHandle.getInstance().WriteLog(LogHandle.ERROR, e.getMessage());
        }
    }

}
