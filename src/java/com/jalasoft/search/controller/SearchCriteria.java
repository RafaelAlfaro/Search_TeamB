/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package java.com.jalasoft.search.controller;

/**
 * SearchCriteria : This class is a container class when will be stored the search criteria
 *
 * @author rafael alfaro
 * @version 1.0
 */

public class SearchCriteria {

    private String driver;
    private String searchPath;
    private String fileName;
    private String advanceSearch;
    private String contains;
    private boolean inTitle;
    private boolean insideFile;
    private String ownerFile;
    private boolean fileCreated;
    private boolean fileModified;
    private boolean fileAccessed;
    //    private Date initDate;
//    private Date finalDate;
    private boolean Filehidden;
    private boolean directory;
    private char sizeCriteria;
    private String sizeFile;
    private String sizeType;

    /**
     * Constructor : The constructor initializes all the attributes
     */
    SearchCriteria() {

        driver = "";
        searchPath = "";
        fileName = "";
        advanceSearch = "";
        contains = "";
        inTitle = true;
        insideFile = false;
        ownerFile = "";
        fileCreated = false;
        fileModified = false;
        fileAccessed = false;
        //initDate;
        //finalDate;
        Filehidden = false;
        directory = false;
        sizeCriteria = ' ';
        sizeFile = "";
        sizeType = "";
    }

    /**
     * Returns the attribute value Driver
     *
     * @return String
     */
    public String getDriver() {
        return driver;
    }

    /**
     * Sets the attribute value Driver
     *
     * @return String
     */
    public SearchCriteria setDriver(String driver) {
        this.driver = driver;
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
     * @return String
     */
    public SearchCriteria setSearchPath(String searchPath) {
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
     * @return String
     */
    public SearchCriteria setFileName(String fileName) {
        this.fileName = fileName;
        return this;
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
     * @return String
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

    public SearchCriteria setContains(String contains) {
        this.contains = contains;
        return this;
    }

    /**
     * Returns the attribute value inTitle
     *
     * @return boolean
     */
    public boolean getInTitle() {
        return inTitle;
    }

    public SearchCriteria setInTitle(boolean inTitle) {
        this.inTitle = inTitle;
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

    public SearchCriteria setOwnerFile(String ownerFile) {
        this.ownerFile = ownerFile;
        return this;
    }

    /**
     * Returns the attribute value fileCreated
     *
     * @return String
     */
    public boolean getFileCreated() {
        return fileCreated;
    }

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

    public SearchCriteria setFileAccessed(boolean fileAccessed) {
        this.fileAccessed = fileAccessed;
        return this;
    }

//    public Date getInitDate() {
//        return initDate;
//    }
//
//    public SearchCriteria setInitDate(Date initDate) {
//        this.initDate = initDate;
//        return this;
//    }
//
//    public Date getFinalDate() {
//        return finalDate;
//    }
//
//    public SearchCriteria setFinalDate(Date finalDate) {
//        this.finalDate = finalDate;
//        return this;
//    }

    /**
     * Returns the attribute value Filehidden
     *
     * @return boolean
     */
    public boolean getFilehidden() {
        return Filehidden;
    }

    public SearchCriteria setFilehidden(boolean filehidden) {
        this.Filehidden = filehidden;
        return this;
    }

    public boolean isDirectory() {
        return directory;
    }

    public SearchCriteria setDirectory(boolean directory) {
        this.directory = directory;
        return this;
    }

    /**
     * Returns the attribute value sizeCriteria
     *
     * @return char
     */
    public char getSizeCriteria() {
        return sizeCriteria;
    }

    public SearchCriteria setSizeCriteria(char sizeCriteria) {
        this.sizeCriteria = sizeCriteria;
        return this;
    }

    /**
     * Returns the attribute value sizeFile
     *
     * @return char
     */
    public String getSizeFile() {
        return sizeFile;
    }

    public SearchCriteria setSizeFile(String sizeFile) {
        this.sizeFile = sizeFile;
        return this;
    }

    /**
     * Returns the attribute value sizeType
     *
     * @return String
     */
    public String getSizeType() {
        return sizeType;
    }

    public SearchCriteria setSizeType(String sizeType) {
        this.sizeType = sizeType;
        return this;
    }

}
