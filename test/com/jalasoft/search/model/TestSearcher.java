
/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.model;

import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * This Class contains the tests for the methods in search.
 *
 * @author sofia.cespedes
 * @version 1.0
 */
public class TestSearcher {
    @Test
    public void searchFile() {
        Search searchInstance = new Search();
        List<Asset> listFilesFound = new ArrayList<>();
        String fileToSearch;
        File file = new File("help.txt");
        fileToSearch = file.getName();
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setFileName("help");
        searchCriteria.setSearchPath("D:/DevFundamentals");
        Path rootDirPath1= Paths.get("D:/DevFundamentals");
        listFilesFound = searchInstance.listFilesByPath(searchCriteria);
        listFilesFound.forEach(s -> assertEquals(fileToSearch, s.getFileName()));
    }
}
