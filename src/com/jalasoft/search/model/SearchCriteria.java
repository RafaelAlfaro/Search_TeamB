/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.model;

import com.google.gson.Gson;
import com.jalasoft.search.commons.LogHandle;

/**
 * SearchCriteria : This class is a container class when will be stored the search criteria
 *
 * @author rafael alfaro
 * @version 1.0
 */

public class SearchCriteria {

    private String criteriaName;
    // Attribute used on basic searchWithCriteria
    private String searchPath;
    private String fileName;

    // Attribute used on advanced search
    private String advanceSearch;
    private String contains;
    private String ownerFile;

    // Size Search
    private long sizeFile;
    private String measureUnit;
    private String sizeCriteria;

    private char dateCriteria;
    // Private boolean inTitle;
    private boolean insideFile;
    private boolean fileCreated;
    private boolean fileModified;
    private boolean fileAccessed;
    private boolean fileHidden;
    private boolean directory;
    private boolean advanceSearchStatus;
    private String startDate;
    private String endDate;

    /**
     * Constructor : The constructor initializes all the attributes
     */
    public SearchCriteria() {
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Creating SearchCriteria Object");
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Settings values by default");
        criteriaName = "";
        searchPath = "";
        fileName = "";
        advanceSearch = "";
        contains = "";
        sizeCriteria = "";
        dateCriteria = ' ';
        sizeFile = 0;
        measureUnit = "";
        ownerFile = "";
        startDate = "";
        endDate = "";
        insideFile = false;
        fileCreated = false;
        fileModified = false;
        fileAccessed = false;
        fileHidden = false;
        directory = false;
    }

    /**
     * Gets Criteria Name
     *
     * @return String Criteria Name
     */
    public String getCriteriaName() {
        return criteriaName;
    }

    /**
     * Sets criteria name
     *
     * @param criteriaName
     */
    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }


    /**
     * Returns the attribute value StartDate
     *
     * @return String
     */
    public String getStartDateCriteria() {
        return startDate;
    }

    /**
     * Sets the attribute value startDate
     *
     * @return SearchCriteria
     */
    public SearchCriteria setStartDateCriteria(String startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Returns the attribute value EndDate
     *
     * @return String
     */
    public String getEndDateCriteria() {
        return endDate;
    }

    /**
     * Sets the attribute value startDate
     *
     * @return SearchCriteria
     */
    public SearchCriteria setEndDateCriteria(String endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Returns the attribute value advanceSearchStatus
     *
     * @return String
     */
    public boolean getAdvanceSearchStatus() {
        return this.advanceSearchStatus;
    }

    /**
     * Sets the attribute value Driver
     *
     * @return SearchCriteria
     */
    public SearchCriteria setAdvanceSearchStatus(boolean advanceSearchStatus) {
        this.advanceSearchStatus = advanceSearchStatus;
        return this;
    }

    /**
     * Returns the attribute value searchPath
     *
     * @return String
     */
    public String getSearchPath() {
        return searchPath;
    }

    /**
     * Sets the attribute value searchPath
     *
     * @return SearchCriteria
     */
    public SearchCriteria setSearchPath(String searchPath) {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Set searchPath : " + searchPath);
        this.searchPath = searchPath;
        return this;
    }

    /**
     * Returns the attribute value fileName
     *
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the attribute value fileName
     *
     * @return SearchCriteria
     */
    public void setFileName(String fileName) {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Set fileName : " + fileName);
        this.fileName = fileName;
    }

    /**
     * Returns the attribute value advanceSearch
     *
     * @return String
     */
    public String getAdvanceSearch() {
        return advanceSearch;
    }

    /**
     * Sets the attribute value fileName
     *
     * @return SearchCriteria
     */
    public SearchCriteria setAdvanceSearch(String advanceSearch) {
        this.advanceSearch = advanceSearch;
        return this;
    }

    /**
     * Returns the attribute value contains
     *
     * @return String
     */
    public String getContains() {
        return contains;
    }

    /**
     * Sets the attribute value contains
     *
     * @return SearchCriteria
     */
    public SearchCriteria setContains(String contains) {
        this.contains = contains;
        return this;
    }

    /**
     * Returns the attribute value insideFile
     *
     * @return boolean
     */
    public boolean getInsideFile() {
        return insideFile;
    }

    /**
     * Sets the attribute value insideFile
     *
     * @return SearchCriteria
     */
    public SearchCriteria setInsideFile(boolean insideFile) {
        this.insideFile = insideFile;
        return this;
    }

    /**
     * Returns the attribute value ownerFile
     *
     * @return String
     */
    public String getOwnerFile() {
        return ownerFile;
    }

    /**
     * Sets the attribute value ownerFile
     *
     * @return SearchCriteria
     */
    public SearchCriteria setOwnerFile(String ownerFile) {
        this.ownerFile = ownerFile;
        return this;
    }

    /**
     * Returns the attribute value fileCreated
     *
     * @return boolean
     */
    public boolean getFileCreated() {
        return fileCreated;
    }

    /**
     * Sets the attribute value fileCreated
     *
     * @return SearchCriteria
     */
    public SearchCriteria setFileCreated(boolean fileCreated) {
        this.fileCreated = fileCreated;
        return this;
    }

    /**
     * Returns the attribute value fileModified
     *
     * @return boolean
     */
    public boolean getFileModified() {
        return fileModified;
    }

    /**
     * Sets the attribute value setFileModified
     *
     * @return SearchCriteria
     */
    public SearchCriteria setFileModified(boolean fileModified) {
        this.fileModified = fileModified;
        return this;
    }

    /**
     * Returns the attribute value fileAccessed
     *
     * @return boolean
     */
    public boolean getFileAccessed() {
        return fileAccessed;
    }

    /**
     * Sets the attribute value fileAccessed
     *
     * @return SearchCriteria
     */
    public SearchCriteria setFileAccessed(boolean fileAccessed) {
        this.fileAccessed = fileAccessed;
        return this;
    }

    /**
     * Returns the attribute value fileHidden
     *
     * @return boolean
     */
    public boolean getFileHidden() {
        return fileHidden;
    }

    /**
     * Returns the attribute value dateCriteria
     *
     * @return String
     */
    public char getDateCriteria() {
        return dateCriteria;
    }

    /**
     * Sets the attribute value dateCriteria
     *
     * @return SearchCriteria
     */
    public SearchCriteria setDateCriteria(char dateCriteria) {
        this.dateCriteria = dateCriteria;
        return this;
    }

    /**
     * Sets the attribute value setFileHidden
     *
     * @return SearchCriteria
     */
    public SearchCriteria setFileHidden(boolean fileHidden) {
        this.fileHidden = fileHidden;
        return this;
    }

    /**
     * Returns the attribute value directory
     *
     * @return boolean
     */
    public boolean getDirectory() {
        return directory;
    }

    /**
     * Sets the attribute value directory
     *
     * @return SearchCriteria
     */
    public SearchCriteria setDirectory(boolean directory) {
        this.directory = directory;
        return this;
    }

    /**
     * Returns the attribute value sizeCriteria
     *
     * @return char
     */
    public String getSizeCriteria() {
        return sizeCriteria;
    }

    /**
     * Sets the attribute value sizeCriteria
     *
     * @return SearchCriteria
     */
    public SearchCriteria setSizeCriteria(String sizeCriteria) {
        this.sizeCriteria = sizeCriteria;
        return this;
    }

    /**
     * Returns the attribute value sizeFile
     *
     * @return String
     */
    public long getSizeFile() {
        return sizeFile;
    }

    /**
     * Sets the attribute value setSizeFile
     *
     * @return SearchCriteria
     */
    public SearchCriteria setSizeFile(long sizeFile) {
        this.sizeFile = sizeFile;
        return this;
    }

    /**
     * Returns the attribute value measureUnit
     *
     * @return String
     */
    public String getMeasureUnit() {
        return measureUnit;
    }

    /**
     * Sets the attribute value measureUnit
     *
     * @return SearchCriteria
     */
    public SearchCriteria setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
        return this;
    }

    /**
     * Returns the Search Criteria in a String
     *
     * @return String
     */
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
