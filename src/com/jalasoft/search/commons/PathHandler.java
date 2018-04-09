/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.commons;

import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * This class handles the path
 *
 * @author rafael
 * @version 1.0
 */
public class PathHandler {

    private String path;

    /**
     * Constructor: If the path is empty then the path is set with the current path
     *
     * @param path
     */
    public PathHandler(String path) {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Starting path validation");
        if (path.isEmpty()) {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "The path is empty. Default path was assigned");
            this.path = getCurrentPath();
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "The path assigned : " + path);
            this.path = path;
        }

    }

    /**
     * Constructor: The constructor without parameters get the current path
     */
    public PathHandler() {
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "PathHandler Constructor");
        this.path = getCurrentPath();
    }

    /**
     * Returns the current path
     *
     * @return String
     */
    private String getCurrentPath() {
        String path = Paths.get(".").toAbsolutePath().normalize().toString();
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Current path : " + path);
        return path;
    }

    /**
     * Verifies if the path is valid
     *
     * @return boolean
     */
    public boolean isValidPath() {

        try {

            Paths.get(this.path);
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Path setted :" + this.path);

        } catch (InvalidPathException | NullPointerException ex) {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "It is not valid path. method returns false");
            LogHandle.getInstance().WriteLog(LogHandle.DEBUG, ex.toString());
            return false;
        }

        return true;
    }
}
