/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.controller;

import com.jalasoft.search.commons.PathHandler;
import com.jalasoft.search.model.FileSearch;
import com.jalasoft.search.model.SearchCriteria;
import com.jalasoft.search.model.Search;
import com.jalasoft.search.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the main class of controller
 *
 * @author rafael alfaro
 * @version 1.0
 */

public class Controller {
    private View view;
    private Search search;
    private SearchCriteria searchCriteria;

    public Controller(Search search, View view) {
        this.view = view;
        this.search = search;
        this.searchCriteria = new SearchCriteria();
        this.view.getBtSearch().addActionListener(e -> fillCriteria());
    }

    /**
     * Verifies if the path is correct
     *
     * @param searchFileName: The path gets from view
     */
    private void pathValidator(String searchFileName, String searchPath) {
        PathHandler validator = new PathHandler(searchPath);
        if (!validator.isValidPath()) {
            System.out.println("The Path is not correct");
        } else {
            this.searchCriteria.setFileName(searchFileName);
            this.searchCriteria.setSearchPath(searchPath);
        }
    }

    /**
     * Fills the criteria in the object
     */
    private void fillCriteria() {
        List<FileSearch> listFilesFound = new ArrayList<>();
        String fileToSearch = this.view.getFileName();
        System.out.println("fileToSearch :" + fileToSearch);
        String pathToSearch = this.view.getSearchPath();
        pathValidator(fileToSearch, pathToSearch);
        System.out.println("pathToSearch :" + pathToSearch);
        listFilesFound = search.searchWithCriteria(this.searchCriteria);
        System.out.println(listFilesFound.size());
    }
}

