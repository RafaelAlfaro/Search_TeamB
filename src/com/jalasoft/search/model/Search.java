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
 * Implements the model class and the methods to search
 *
 * @author sofia cespedes
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Search {
    List <FileSearch> listFilesFound = new ArrayList<>();
    /**
     * Searchs a file by name in specific directory
     * Initialize an instance of File
     */
    public void Search(String fileName, String directory) {
        FileSearch fileSearch = new FileSearch();
        // if no directory is specified it should set in the workdir directory
        if (directory.isEmpty()){
            directory = System.getProperty("user.dir");
        }
        Path path = Paths.get(directory);
        ListFilesByPath(path, fileName, fileSearch);
    }

    /**
     * Search files by name and directory and store results in a list of FileSearch
     * @param path: path to search
     * @param fileName: name of the file
     * @param fileSearch: instance of FileSearch class
     * @return listFilesFound - List of FileSearch objects
     */
    public List<FileSearch> ListFilesByPath(Path path, String fileName, FileSearch fileSearch) {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path filePath : stream) {
                if (Files.isDirectory(filePath)) {
                    ListFilesByPath(filePath, fileName, fileSearch);
                }
                else if (fileName.equalsIgnoreCase(filePath.getFileName().toString()) ||
                        fileName.equalsIgnoreCase(getFileNameWithoutExtension(new File(filePath.toString()))) ||
                        fileName.equalsIgnoreCase("*." + getFileExtension(new File(filePath.toString()))) ||
                        fileName.isEmpty() || fileName.equals("*")) {
                    fileSearch.setPath(filePath.toString());
                    fileSearch.setOwner(Files.getOwner(path).toString());
                    fileSearch.setExtension(getFileExtension(new File(filePath.toString())));
                    fileSearch.setFileName(filePath.getFileName().toString());
                    listFilesFound.add(fileSearch);
                }
            }
            stream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return listFilesFound;
    }

    /**
     * Gets extension from a specific file
     * @param file: file to get it extension
     * @return String
     */
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }

    /**
     * Get file name without extension
     * @param file: file to get it name without extension
     * @return String
     */
    private static String getFileNameWithoutExtension(File file) {
        String fileName = "";

        try {
            if (file != null && file.exists()) {
                String name = file.getName();
                fileName = name.replaceFirst("[.][^.]+$", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileName = "";
        }

        return fileName;
    }
}