
/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.model;

import org.testng.annotations.Test;
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
        List<FileSearch> listFilesFound = new ArrayList<>();
        String fileToSearch, pathTosearch;
        fileToSearch = "help.txt";
        pathTosearch = "c:\\DevFundamentals";
        listFilesFound = searchInstance.Search(fileToSearch,pathTosearch);
        listFilesFound.forEach(s-> assertEquals(fileToSearch,s.getFileName()));
    }
}