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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Search {
    List<FileSearch> listFilesFound = new ArrayList<>();
    SearchCriteria searchCriteria = new SearchCriteria();
    FileSearch fileSearch = new FileSearch();
    /**
     * Constructor
     */
    public Search() {
    }

    /**
     * Search files by name and directory and store results in a list of FileSearch
     *
     * @param fileName: File name
     * @return listFilesFound - List of FileSearch objects
     */
    public List<FileSearch> listFilesByPath(String fileName) {
        Path path = Paths.get(searchCriteria.getSearchPath());
                try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path filePath : stream) {
                    File file = new File(filePath.toString());
                    if (Files.isDirectory(filePath)) {
                        listFilesByPath(fileName);
                    } else if (fileName.equalsIgnoreCase(filePath.getFileName().toString()) ||
                                    fileName.equalsIgnoreCase("*." + getFileExtension(new File(filePath.toString()))) ||
                                    fileName.contains(filePath.getFileName().toString().replaceAll("[*]", "")) ||
                                    fileName.isEmpty() || fileName.equals("*") || fileName.equals("*.*")) {
                        fileSearch.setPath(filePath.toString());
                        fileSearch.setExtension(getFileExtension(file));
                        fileSearch.setFileName(filePath.getFileName().toString());
                        if (searchCriteria.getAdvanceSearch() != null) {

                            if (searchCriteria.getOwnerFile() != null) {
                                String filePathOwner = Files.getOwner(path).toString();
                                if (filePathOwner.equals(searchCriteria.getOwnerFile())) {
                                    fileSearch.setOwner(Files.getOwner(path).toString());
                                }
                            }
                            if (searchCriteria.getSizeFile() != null) {
                                long sizeFilePath = file.length();
                                switch (searchCriteria.getSizeType()) {
                                    case "Kb":
                                        sizeFilePath = sizeFilePath / 1024;
                                        break;
                                    case "Mb":
                                        sizeFilePath = sizeFilePath / (1024 * 1024);
                                        break;
                                }
                                if (verifySizeCriteria(sizeFilePath, searchCriteria.getSizeCriteria(), searchCriteria.getSizeFile())) {
                                    fileSearch.setSize(Long.toString(sizeFilePath));
                                }
                            }
                            if (searchCriteria.getInsideFile()) {
                                Scanner scanFile = new Scanner(file);
                                while(scanFile.hasNext()){
                                    String line = scanFile.nextLine().toLowerCase().toString();
                                    if(line.contains(searchCriteria.getContains())){
                                        fileSearch.setContent(line);
                                        break;
                                    }
                                }
                            }
                            if (searchCriteria.getInTitle()) {
                                BufferedReader br = new BufferedReader(new FileReader(file));
                                String title = br.readLine();
                                if (title.contains(searchCriteria.getContains())){
                                    fileSearch.setContent(title);
                                }
                            }
                        }
                            listFilesFound.add(fileSearch);
                    }
                    if (searchCriteria.getFileHidden()) {
                        if (!file.isHidden()) {
                            listFilesFound.remove(fileSearch);
                        }
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
     * Verify size criteria
     *
     * @param sizeFilePath: file size
     * @param criteria: criteria for compare
     * @param fileSize: Size to compare
     * @return boolean
     */
    private boolean verifySizeCriteria(long sizeFilePath, char criteria, String fileSize ) {
        switch (criteria) {
            case '=':
                return (Long.parseLong(fileSize) == sizeFilePath);
            case '>':
                return (Long.parseLong(fileSize) > sizeFilePath);
            case '<':
                return (Long.parseLong(fileSize) < sizeFilePath);
            default: return false;
        }
    }

    /**
     * Gets extension from a specific file
     *
     * @param file: file to get it extension
     * @return String
     */
    private static String getFileExtension(File file)  {
        String fileName = file.getName();
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get file name without extension
     *
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
