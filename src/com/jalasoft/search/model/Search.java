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

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
import com.yevdo.jwildcard.JWildcard;
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
import java.util.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Implements the model class and the methods to search
 *
 * @author sofia cespedes
 * @version 1.0
 */

public class Search {
    List<Asset> listFilesFound = new ArrayList<>();
    SearchCriteria searchCriteria = new SearchCriteria();

    /**
     * Constructor
     */
    public Search() {
    }

    /**
     * Search files by name and directory and store results in a list of FileSearch
     *
     * @param searchCriteria: File name
     * @return listFilesFound - List of FileSearch objects
     */

    public List<Asset> listFilesByPath(SearchCriteria searchCriteria) {
        String fileName = searchCriteria.getFileName();
        Path path = Paths.get(searchCriteria.getSearchPath());
        Asset fileCompare = new Asset();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path filePath : stream) {
                File file = new File(filePath.toString());
                if (Files.isDirectory(filePath)) {
                    if ( JWildcard.matches(fileName, filePath.getFileName().toString()))
                    {
                        fileCompare.createAsset('d');
                        listFilesFound.add(fileCompare);
                    }
                    listFilesByPath(searchCriteria);
                } else if ( JWildcard.matches(fileName, filePath.getFileName().toString()) ||
                        fileName.isEmpty() || fileName.equals("*") || fileName.equals("*.*") || fileName.equals(".*")|| fileName.equals("*.")) {
                    if (filePath instanceof FileSearch)
                    {
                         fileCompare.createAsset('f');
                        ((FileSearch)fileCompare).setExtension(getFileExtension(path.getFileName().toString()));
                    }
                    else
                    {
                        fileCompare.createAsset('m');
                    }

                    fileCompare.setPath(filePath.toString());
                    fileCompare.setFileName(filePath.getFileName().toString());
                    if (searchCriteria.getAdvanceSearch() != null) {

                        if (searchCriteria.getOwnerFile() != null) {
                            String filePathOwner = Files.getOwner(path).toString();
                            if (filePathOwner.equals(searchCriteria.getOwnerFile())) {
                                fileCompare.setOwner(Files.getOwner(path).toString());
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
                                fileCompare.setSize(Long.toString(sizeFilePath));
                            }
                        }
                        if (searchCriteria.getInsideFile()) {
                            Scanner scanFile = new Scanner(file);
                            while(scanFile.hasNext()){
                                String line = scanFile.nextLine().toLowerCase().toString();
                                if(JWildcard.matches(searchCriteria.getContains(), line)){
                                    ((FileSearch)fileCompare).setContent(line);
                                    break;
                                }
                            }
                        }
                        if (searchCriteria.getInTitle()) {
                            BufferedReader br = new BufferedReader(new FileReader(file));
                            String title = br.readLine();
                            if (JWildcard.matches(searchCriteria.getContains(), title)){
                                ((FileSearch)fileCompare).setContent(title);
                            }
                        }

                        if (searchCriteria.getDateCriteria()!= ' ')
                        {
                            BasicFileAttributes view
                                    = Files.getFileAttributeView(filePath, BasicFileAttributeView.class)
                                    .readAttributes();
                            FileTime fileTime = view.creationTime();
                            FileTime dateTimeFrom = view.creationTime();
                            FileTime dateTimeTo = view.creationTime();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            String date = "01.01.2013 10:00:10";
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
                                    fileTime=view.creationTime();
                                case 'u':
                                    fileTime=view.lastModifiedTime();
                                case 'a':
                                    fileTime=view.lastAccessTime();
                            }
                            if(fileTime.compareTo(dateTimeFrom)> 0 && fileTime.compareTo(dateTimeTo)<0)
                            {
                                fileCompare.setFileDate(fileTime.toString(),searchCriteria.getDateCriteria());
                            }
                        }
                    }
                    listFilesFound.add(fileCompare);
                }
                if (searchCriteria.getFileHidden()) {
                    if (!file.isHidden()) {
                        listFilesFound.remove(fileCompare);
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
     * @param fileName: file to get it extension
     * @return String
     */
    private static String getFileExtension(String fileName)  {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
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
