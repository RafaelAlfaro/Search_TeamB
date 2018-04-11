/*
 * Copyright (c) 2018 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.jalasoft.search.model;

import sun.security.smartcardio.SunPCSC;

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author sofia cespedes
 * @version 1.0
 */
public class Asset extends FactoryAsset {
    private String fileName;
    private String filePath;
    private String fileOwner;
    private String fileSize;
    private String fileDate;
    private char fileDateCriteria;

    /**
     * Constructor : The constructor initializes all the attributes
     */
    public Asset() {
        fileName = "";
        filePath = "";
        fileName = "";
        fileOwner = "";
        fileDateCriteria = ' ';
        fileSize = "";
        fileDate = "";
    }

    /**
     * Returns the file name
     *
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets name to the File Name field
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Returns the path
     *
     * @return String
     */
    public String getPath() {
        return filePath;
    }

    /**
     * Sets path value to the Path field
     *
     * @param filePath
     */
    public void setPath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the owner of the file
     *
     * @return String
     */
    public String getOwner() {
        return fileOwner;
    }

    /**
     * Sets owner value to the Owner field
     *
     * @param fileOwner
     */
    public void setOwner(String fileOwner) {
        this.fileOwner = fileOwner;
    }

    /**
     * Returns the size of the file
     *
     * @return String
     */
    public String getSize() {
        return fileSize;
    }

    /**
     * Sets size of the file
     *
     * @param fileSize
     */
    public void setSize(String fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * Sets the attribute value setFileDate
     *
     * @param fileDateCriteria
     */
    public void setFileDate(String fileDate, char fileDateCriteria) {
        this.fileDate = fileDate;
        this.fileDateCriteria = fileDateCriteria;
    }
}
