/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */
package com.jalasoft.search.commons;

import java.util.Hashtable;

/**
 * This Class help to convert between digital information units.
 * Digital Units supported:
 * b -> byte
 * Kb -> Kilobytes
 * Mb -> Megabytes
 * Gb -> Gigabytes
 * Tb -> Terabytes
 * x % 1024 ->
 * |b|Kb|Mb|Gb|Tb|
 * <- x * 1024
 *
 * @author rafael alfaro
 * @version 1.0
 */

public class DigitalUnitConverter {
    private Hashtable<String, Integer> digitalInformationUnit;

    /**
     * The constructor will initialize a hashtable with the digital information units.
     */
    public DigitalUnitConverter() {
        digitalInformationUnit = new Hashtable<>();
        if (digitalInformationUnit.isEmpty()) {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Load information related to digital " +
                    "Information Unit");
            digitalInformationUnit.put("Bytes", 1);
            digitalInformationUnit.put("KB", 2);
            digitalInformationUnit.put("MB", 3);
            digitalInformationUnit.put("GB", 4);
            digitalInformationUnit.put("TB", 5);
        }
    }

    /**
     * Gets the relative position in the hashtable with the digital information units
     *
     * @param unitSource : initial position
     * @param unitTo     : final position
     * @return position number to calculate
     */
    private int getNumber(String unitSource, String unitTo) {
        return Math.abs(digitalInformationUnit.get(unitSource) - digitalInformationUnit.get(unitTo));
    }

    /**
     * Calculates from right to left multiply each position by 1024.
     * Position: |b|Kb|Mb|Gb|Tb
     * E.g.:
     * There are two position from Gb to Kb:
     * 50Gb to Kb = 50 * 1024 ^ 2 => 52428800 Kb
     *
     * @param sizeFile: Size to calculate
     * @param pow:      1024 ^ pow
     * @return Value converted
     */
    private long calculateToLeft(long sizeFile, int pow) {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Calculates from right to left multiply each " +
                "position by 1024");
        long siteFileLeft = (long) (sizeFile * Math.pow(1024, pow));

        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Value calculated is : " + siteFileLeft);
        return siteFileLeft;
    }

    /**
     * Calculates from left to right dividing each position by 1024.
     * Position: |b|Kb|Mb|Gb|Tb
     * E.g.:
     * There are two position from Gb to Kb:
     * 50Kb to Gb = (50 / 1024) / 1024 => 0.0000476837 Gb
     *
     * @param sizeFile:        Size to calculate
     * @param divisionsNumber: 1024 ^ pow
     * @return Value converted
     */
    private long calculateToRight(long sizeFile, int divisionsNumber) {
        long result = sizeFile; //The "result" variable contains the conversion calculated
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Calculates from left to right dividing each " +
                "position by 1024");
        for (int divisionCounter = 0; divisionCounter < divisionsNumber; divisionCounter++) {
            result = result / 1024;
        }
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Value returned" + result);
        return result;
    }

    /**
     * Calculates Digital Information Unit
     *
     * @param sizeFile   : Size to convert
     * @param unitSource : Initial digital unit (|b|Kb|Mb|Gb|Tb)
     * @param unitTo     : Final digital unit (|b|Kb|Mb|Gb|Tb)
     * @return Value calculated
     */
    private long calculateDigitalInformationUnit(long sizeFile, String unitSource, String unitTo) {
        long result = sizeFile; //The "result" variable contains the conversion calculated
        if (digitalInformationUnit.get(unitSource) > digitalInformationUnit.get(unitTo)) {
            result = calculateToLeft(sizeFile, getNumber(unitSource, unitTo));
        } else if (digitalInformationUnit.get(unitSource) < digitalInformationUnit.get(unitTo)) {
            result = calculateToRight(sizeFile, getNumber(unitSource, unitTo));
        }

        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "sizeFile : " + sizeFile);
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "sizeFile : " + unitSource);
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "sizeFile : " + unitTo);
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "sizeFile : " + result);
        return result;
    }

    /**
     * Calculates Digital Information Unit
     *
     * @param sizeToConvert : Size to convert
     * @param initialUnit   : Initial digital unit (|b|Kb|Mb|Gb|Tb)
     * @param finalUnit     : Final digital unit (|b|Kb|Mb|Gb|Tb)
     * @return Value calculated
     */
    public long convertTo(long sizeToConvert, String initialUnit, String finalUnit) {
        if (digitalInformationUnit.containsKey(initialUnit) && digitalInformationUnit.containsKey(finalUnit)) {
            return calculateDigitalInformationUnit(sizeToConvert, initialUnit, finalUnit);
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.ERROR, "Digital Information Unit is not known");
        }
        return -1;
    }
}
