/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */
package com.jalasoft.search.commons;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * <Class description goes here>
 *
 * @author rafael
 * @version 1.0
 */
public class TestPathHandler {

    @Test
    public void createPointWithoutParameters() {
        PathHandler path = new PathHandler();
        assertTrue(path instanceof PathHandler);
    }

    @Test
    public void createPointWithParameters() {
        PathHandler path = new PathHandler("c:\\");
        assertTrue(path instanceof PathHandler);
    }

    @Test
    public void isValidPath() {
        PathHandler path = new PathHandler("c:/test");
        assertTrue(path.isValidPath());
    }

    @Test
    public void isNotValidPathWithColon() {
        String stringPath = "c:/te:t";
        PathHandler path = new PathHandler(stringPath);
        assertFalse(path.isValidPath());
    }

    @Test
    public void isNotValidPathWithQuestionMark() {
        PathHandler path = new PathHandler("c:/te?t");
        assertFalse(path.isValidPath());
    }


    @Test
    public void isNotValidPathWithStart() {
        PathHandler path = new PathHandler("c/te*t");
        assertFalse(path.isValidPath());
    }

    @Test
    public void isValidFileName() {
        PathHandler path = new PathHandler("good.txt");
        assertTrue(path.isValidPath());
    }

    @Test
    public void isNotValidFileNameWithPipe() {
        String stringPath = "not|good.txt";
        PathHandler path = new PathHandler(stringPath);
        assertFalse(path.isValidPath());
    }

    @Test
    public void isNotValidFileNameWithColon() {
        String stringPath = "c:/te:t";
        PathHandler path = new PathHandler(stringPath);
        assertFalse(path.isValidPath());
    }

}
