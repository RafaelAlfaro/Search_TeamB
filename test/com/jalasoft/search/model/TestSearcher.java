
/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.model;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * This Class contains the tests for the methods in search.
 *
 * @author sofia.cespedes
 * @version 1.0
 */
public class TestSearcher {
    @Test
    public void singleSearch() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch;
        String directoryPath = System.getProperty("user.dir");
        File file = new File("help.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(file.getName());
        searchCriteria.setSearchPath(directoryPath);
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }

    @Test
    public void singleSearchWithWildCard() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch;
        String directoryPath = System.getProperty("user.dir");
        File file = new File("helpFileCreatedToTestWildCard.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName("*File*Createdto*");
        searchCriteria.setSearchPath(directoryPath);
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }

    @Test
    public void createFileSearch()throws IOException {
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, directoryPath;
        directoryPath = System.getProperty("user.dir");
        File file = new File("filetofind.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(fileToSearch);
        searchCriteria.setSearchPath(directoryPath);
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> {
            assertEquals(fileToSearch, s.getFileName());
            assertTrue(s instanceof FileSearch);
        });
    }

    @Test
    public void createFolderSearch() {
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, directoryPath;
        fileToSearch = "folderToFind";
        directoryPath = System.getProperty("user.dir");
        new File(directoryPath + "/" + fileToSearch).mkdir();

        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(fileToSearch);
        searchCriteria.setSearchPath(directoryPath);
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> {
            assertEquals(fileToSearch, s.getFileName());
            assertTrue(s instanceof FolderSearch);
        });
    }

    @Test
    public void createMultimedia() throws IOException {
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, directoryPath;

        directoryPath = System.getProperty("user.dir");
        File file = new File("filetofind.avi");
        file.createNewFile();
        fileToSearch = file.getName();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(fileToSearch);
        searchCriteria.setSearchPath(directoryPath);
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> {
            assertEquals(fileToSearch, s.getFileName());
            assertTrue(s instanceof Multimedia);
        });
    }

    @Test
    public void advancedSearchChecked() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch;
        String directoryPath = System.getProperty("user.dir");
        File file = new File("help.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(file.getName());
        searchCriteria.setSearchPath(directoryPath);
        searchCriteria.setEnableAdvanceSearch(true);
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }

    @Test
    public void advancedSearchByContains() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, stringToSearch;
        stringToSearch = "String into a file containing some text that is interesting material";
        String directoryPath = System.getProperty("user.dir");
        File file = new File("helpcontain.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(stringToSearch);
        output.close();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(file.getName());
        searchCriteria.setSearchPath(directoryPath);
        searchCriteria.setEnableAdvanceSearch(true);
        searchCriteria.setContains("some text");

        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }

    @Test
    public void advancedSearchByContainsAndWildCards() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, stringToSearch;
        stringToSearch = "String into a file containing some text that is interesting material";
        String directoryPath = System.getProperty("user.dir");
        File file = new File("helpcontain.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(stringToSearch);
        output.close();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(file.getName());
        searchCriteria.setSearchPath(directoryPath);
        searchCriteria.setEnableAdvanceSearch(true);
        searchCriteria.setContains("file*ome*interes*");

        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }


    @Test
    public void advancedSearchBySize() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, stringToSearch;
        stringToSearch = "String into a file containing some text that is interesting material";
        String directoryPath = System.getProperty("user.dir");
        File file = new File("helpcontain.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(stringToSearch);
        output.close();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(file.getName());
        searchCriteria.setSearchPath(directoryPath);
        searchCriteria.setEnableAdvanceSearch(true);
        searchCriteria.setSizeCriteria(">");
        searchCriteria.setSizeFile(123);

        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }

    @Test
    public void advancedSearchByOwner() throws IOException{
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch, stringToSearch;
        stringToSearch = "String into a file containing some text that is interesting material";
        String directoryPath = System.getProperty("user.dir");
        File file = new File("helpcontain.txt");
        file.createNewFile();
        fileToSearch = file.getName();
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(stringToSearch);
        output.close();
        Path path = Paths.get(directoryPath);
        String filePathOwner = Files.getOwner(path).toString();
        String[] parts = filePathOwner.split("[\\\\(\\)]");
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName(file.getName());
        searchCriteria.setSearchPath(directoryPath);
        searchCriteria.setEnableAdvanceSearch(true);
        searchCriteria.setOwnerFile(parts[1]);

        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }
}
