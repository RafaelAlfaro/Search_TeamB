
/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.commons;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This Class contains the tests for the methods in DigitalUnitConverter.
 *
 * @author rafael alfaro
 * @version 1.0
 */
public class TestDigitalUnitConverter {

    @Test
    public void createPointWithoutParameters() {
        DigitalUnitConverter digitalUnitConverter = new DigitalUnitConverter();
        assertTrue(digitalUnitConverter instanceof DigitalUnitConverter);
    }

    @Test
    public void convertFromTbToGb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(51200, (int) converter.convertTo(50, "Tb", "Gb"));
    }

    @Test
    public void convertFromGbToMb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(5120, (int) converter.convertTo(5, "Gb", "Mb"));
    }

    @Test
    public void convertFromMbToKb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(5120, (int) converter.convertTo(5, "Mb", "Kb"));
    }

    @Test
    public void convertFromKbToBytes() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(5120, (int) converter.convertTo(5, "Kb", "byte"));
    }


    @Test
    public void convertFromGbToKb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(1048576, (int) converter.convertTo(1, "Gb", "Kb"));
    }

    @Test
    public void convertFromMbToByte() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(5242880, (int) converter.convertTo(5, "Mb", "byte"));
    }


    @Test
    public void convertFromGbToTb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(1, (int) converter.convertTo(1024, "Gb", "Tb"));
    }

    @Test
    public void convertFromMbToGb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(1, (int) converter.convertTo(1024, "Mb", "Gb"));
    }

    @Test
    public void convertFromKbToMb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(1024, (int) converter.convertTo(1048576, "Kb", "Mb"));
    }

    @Test
    public void convertFromBytesToKb() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(1024, (int) converter.convertTo(1048576, "byte", "Kb"));
    }

    @Test
    public void convertFromMbToBytesErrorUnitNotDefined() {
        DigitalUnitConverter converter = new DigitalUnitConverter();
        assertEquals(-1, (int) converter.convertTo(1, "Mb", "bytes"));
    }

}
