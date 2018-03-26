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
 * <Class description goes here>
 *
 * @author rafael
 * @version 1.0
 */
public class PathHandler {

    private String path;

    /**
     * Constructor: if the path is empty. The path is set with current path
     *
     * @param path
     */
    public PathHandler(String path) {
        if (path.isEmpty()) {
            this.path = getCurrentPath();
        } else {
            this.path = path;
        }

    }

    /**
     * Constructor: The constructor without parameters get the current path
     */
    public PathHandler() {
        this.path = getCurrentPath();
    }

    /**
     * Returns the current path
     *
     * @return String
     */
    private String getCurrentPath() {
        return Paths.get(".").toAbsolutePath().normalize().toString();
    }

    /**
     * Verifies if the path is valid
     *
     * @return boolean
     */
    public boolean isValidPath() {

        try {

            Paths.get(this.path);

        } catch (InvalidPathException | NullPointerException ex) {
            return false;
        }

        return true;
    }
}
