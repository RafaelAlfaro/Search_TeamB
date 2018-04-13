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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

import com.jalasoft.search.commons.LogHandle;
import com.yevdo.jwildcard.JWildcard;

/**
 * Implements the model class and the methods to search
 *
 * @author sofia cespedes
 * @version 1.0
 */

public class Search {
    List<Asset> listFilesFound;
    Asset fileCompare = new Asset();
    boolean isAdvancedFile, isSingleSearch = false;

    /**
     * Constructor
     */
    public Search() {
        listFilesFound = new ArrayList<>();
    }

    /**
     * Search files by name and directory and store results in a list of FileSearch
     *
     * @param searchCriteria: File name
     * @return listFilesFound - List of FileSearch objects
     */

    public List<Asset> listFilesByPath(SearchCriteria searchCriteria) {
        //listFilesFound.clear();
        String fileName = searchCriteria.getFileName();
        Path path = Paths.get(searchCriteria.getSearchPath());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(searchCriteria.getSearchPath()))) {
            for (Path filePath : stream) {
                isAdvancedFile = false;
                isSingleSearch = false;
                File file = new File(filePath.toString());
                if (Files.isDirectory(filePath)) {
                    searchCriteria.setSearchPath(filePath.toString());
                    if (filePath.getFileName().toString().toLowerCase().equals(fileName.toLowerCase()) ||JWildcard.matches(fileName.toLowerCase(), filePath.getFileName().toString().toLowerCase())) {
                        fileCompare = fileCompare.createAsset('d');
                        fileCompare.setSize(Long.toString(file.length()));
                        fileCompare.setPath(filePath.toString());
                        ((FolderSearch) fileCompare).setNumberOfFiles(file.list().length);
                        if (!searchCriteria.getAdvanceSearch().isEmpty()) {
                            isAdvancedFile = true;
                            advancedSearchExecution(searchCriteria, path, filePath, file);
                        }
                        else
                        {
                            isSingleSearch = true;
                        }


                        listFilesFound.add(fileCompare);
                    }
                    listFilesByPath(searchCriteria);
                }
                else if (filePath.getFileName().toString().toLowerCase().contains(fileName.toLowerCase()) ||
                        JWildcard.matches(fileName.toLowerCase(), filePath.getFileName().toString().toLowerCase()) ||
                        fileName.isEmpty() || fileName.equals("*") || fileName.equals("*.*") || fileName.equals(".*")
                        || fileName.equals("*.")) {
                    isSingleSearch = true;
                    if (file.canRead()) {
                        fileCompare = fileCompare.createAsset('f');
                        ((FileSearch) fileCompare).setExtension(getFileExtension(path.getFileName().toString()));

                    } else {
                        fileCompare = fileCompare.createAsset('m');
                    }

                    fileCompare.setPath(filePath.toString());
                    fileCompare.setFileName(filePath.getFileName().toString());

                    if (!searchCriteria.getAdvanceSearch().isEmpty()) {
                        isSingleSearch = false;
                        advancedSearchExecution(searchCriteria, path, filePath,file);
                    }
                    if(isAdvancedFile || isSingleSearch){
                        listFilesFound.add(fileCompare);}
                }
                if (searchCriteria.getFileHidden()) {
                    if (!file.isHidden() && listFilesFound.size() > 0) {
                        listFilesFound.remove(fileCompare);
                    }
                }
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listFilesFound;
    }

    /**
     * Verify file meets with advanced search criteria and inscribe attributes to object
     *
     * @param searchCriteria: Search criteria instance
     * @param path:     Path of the file to search
     * @param filePath: Path of the current file
     * @param file:     File instance
     *
     */
    private boolean advancedSearchExecution(SearchCriteria searchCriteria, Path path, Path filePath, File file) throws IOException
    {
        if (searchCriteria.getOwnerFile() != null) {
            String filePathOwner = Files.getOwner(path).toString();
            if (filePathOwner.equals(searchCriteria.getOwnerFile())) {
                fileCompare.setOwner(Files.getOwner(path).toString());
                isAdvancedFile = true;
            }
            else { isAdvancedFile = false;}
        }
        if (searchCriteria.getSizeFile() > 0) {
            long sizeFilePath = file.length();
            if (verifySizeCriteria(sizeFilePath, searchCriteria.getSizeCriteria(),
                    searchCriteria.getSizeFile())) {
                fileCompare.setSize(Long.toString(sizeFilePath));
                isAdvancedFile = true;
            }
            else { isAdvancedFile = false;}
        }
        if (!searchCriteria.getContains().isEmpty()) {
            if (file.canRead()) {
                Scanner scanFile = new Scanner(file);
                while (scanFile.hasNext()) {
                    String line = scanFile.nextLine().toLowerCase().toString();
                    if (line.contains(searchCriteria.getContains()) || JWildcard.matches(searchCriteria.getContains(), line)) {
                        ((FileSearch) fileCompare).setContent(line);
                        isAdvancedFile = true;
                        break;
                    }
                    isAdvancedFile = false;
                }
            }
            else {
                isAdvancedFile = false;
                LogHandle.getInstance().WriteLog(LogHandle.ERROR, "File can't be read .....");
            }
        }

        if (searchCriteria.getDateCriteria() != ' ') {
            BasicFileAttributes view
                    = Files.getFileAttributeView(filePath, BasicFileAttributeView.class)
                    .readAttributes();
            FileTime fileTime = view.creationTime();
            FileTime dateTimeFrom = view.creationTime();
            FileTime dateTimeTo = view.creationTime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            long parserDateFrom, parserDateTo;
            try {
                parserDateFrom = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").parse(searchCriteria.getFileDateFrom())
                        .getTime();
                parserDateFrom = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss").parse(searchCriteria.getFileDateTo())
                        .getTime();
                dateTimeFrom = FileTime.fromMillis(parserDateFrom);
                dateTimeTo = FileTime.fromMillis(parserDateFrom);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            switch (searchCriteria.getDateCriteria()) {
                case 'c':
                    fileTime = view.creationTime();
                case 'u':
                    fileTime = view.lastModifiedTime();
                case 'a':
                    fileTime = view.lastAccessTime();
            }
            if (fileTime.compareTo(dateTimeFrom) > 0 && fileTime.compareTo(dateTimeTo) < 0) {
                isAdvancedFile = true;
                fileCompare.setFileDate(fileTime.toString(), searchCriteria.getDateCriteria());
            }
        }
        return isAdvancedFile;
    }

    /**
     * Verify size criteria
     *
     * @param sizeFilePath: file size
     * @param criteria:     criteria for compare
     * @param fileSize:     Size to compare
     * @return boolean
     */
    private boolean verifySizeCriteria(long sizeFilePath, String criteria, Long fileSize) {
        switch (criteria) {
            case "=":
                return (sizeFilePath == fileSize );
            case ">":
                return (sizeFilePath > fileSize);
            case "<":
                return (sizeFilePath < fileSize);
            default:
                return false;
        }
    }

    /**
     * Gets extension from a specific file
     *
     * @param fileName: file to get it extension
     * @return String
     */
    private static String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get file name without extension
     *
     * @param fileName: file to get it name without extension
     * @return String
     */
    private static String getFileNameWithoutExtension(String fileName) {
        try {
            return fileName = fileName.replaceFirst("[.][^.]+$", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
