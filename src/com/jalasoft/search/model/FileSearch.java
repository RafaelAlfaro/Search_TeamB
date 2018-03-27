package com.jalasoft.search.model;

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author sofia cespedes
 * @version 1.0
 *
 */

public class FileSearch {
    private String fileName;
    private String path;
    private String owner;
    private String extension;
    private String size;

    /**
     * Returns the file name
     *
     * @return String
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Set name to the File Name field
     *
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
        return path;
    }

    /**
     * Set path value to the Path field
     *
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Returns the owner of the file
     *
     * @return String
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Set owner value to the Owner field
     *
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Returns the extension of the file
     *
     * @return String
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Set extension value to the Extension field
     *
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
}
