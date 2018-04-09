/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */
package com.jalasoft.search.commons;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * This class handles the logs of application. We will handle 6 log levels
 * <p>
 * Levels:
 * DEBUG = 1
 * INFO = 2
 * WARN = 3
 * ERROR = 4
 * TRACE = 5
 * FATAL = 6
 * <p>
 * Format:
 * LogHandle.getInstance().WriteLog(*LogLevel*, *message*);
 * E.g.:
 * LogHandle.getInstance().WriteLog(LogHandle.INFO, "info Message!");
 *
 * @author rafael
 * @version 1.0
 */

public class LogHandle {
    public static final int DEBUG = 1;
    public static final int INFO = 2;
    public static final int WARN = 3;
    public static final int ERROR = 4;
    public static final int TRACE = 5;
    public static final int FATAL = 6;

    private static final int callingClass = 2;

    private static Logger logger = Logger.getLogger(LogHandle.class);
    private static LogHandle log4j = null;

    /**
     * Gets the class name of method that invoked
     *
     * @return Class Name
     */
    private static String getClassName() {
        return new Exception().getStackTrace()[callingClass].getClassName();
    }

    /**
     * Returns the object instance if it was created. if not,  Create the object.
     *
     * @return LogHandle
     */
    public static LogHandle getInstance() {
        if (null == log4j) {
            SetConf();
            log4j = new LogHandle();
        }
        return log4j;
    }

    /**
     * Reads configuration options from an InputStream
     */
    private static void SetConf() {
        try {
            PropertyConfigurator.configure("resources/log4j.properties");
        } catch (Exception e) {
            throw new RuntimeException("Unable to load logging property for Class... ");
        }
        logger.info("Log4j Property File for Class Loaded Properly..");
    }

    /**
     * Writes the logs using the following format
     * E.g.
     * LogHandle.getInstance().WriteLog(LogHandle.TRACE, "Trace Message!");
     * LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Debug Message!");
     * LogHandle.getInstance().WriteLog(LogHandle.INFO, "Info Message!");
     * LogHandle.getInstance().WriteLog(LogHandle.WARN, "Warn Message!");
     * LogHandle.getInstance().WriteLog(LogHandle.ERROR, "Error Message!");
     * LogHandle.getInstance().WriteLog(LogHandle.FATAL, "Fatal Message!");
     * LogHandle.getInstance().WriteLog(14, "Fatal Message!");
     *
     * @param logLevel
     * @param logContent
     */
    public void WriteLog(int logLevel, String logContent) {
        logger = Logger.getLogger(getClassName());
        switch (logLevel) {
            case DEBUG:
                logger.debug(logContent);
                break;
            case INFO:
                logger.info(logContent);
                break;
            case WARN:
                logger.warn(logContent);
                break;
            case ERROR:
                logger.error(logContent);
                break;
            case TRACE:
                logger.trace(logContent);
                break;
            case FATAL:
                logger.fatal(logContent);
                break;
            default:
                String errorMsg = "Log level was not defined";
                System.out.println(errorMsg);
                logger.error(errorMsg);
                break;
        }
    }
}
