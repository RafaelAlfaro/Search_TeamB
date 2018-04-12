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

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author sofia cespedes
 * @version 1.0
 */
public class FileSearch extends Asset {
    private String fileExtension;
    private String fileContent;

    /**
     * Constructor : The constructor initializes all the attributes
     */
    public FileSearch() {
        fileExtension = "";
        fileContent = "";
    }

    /**
     * Returns the extension of the file
     *
     * @return String
     */
    public String getExtension() {
        return fileExtension;
    }

    /**
     * Sets extension value to the Extension field
     *
     * @param fileExtension
     */
    public void setExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    /**
     * Returns the content of the file
     *
     * @return String
     */
    public String getContent() {
        return fileContent;
    }

    /**
     * Sets content value to the Content field
     *
     * @param fileContent
     */
    public void setContent(String fileContent) {
        this.fileContent = fileContent;
    }
}
