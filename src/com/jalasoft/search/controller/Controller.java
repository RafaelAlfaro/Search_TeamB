/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.controller;

import com.jalasoft.search.commons.LogHandle;
import com.jalasoft.search.commons.PathHandler;
import com.jalasoft.search.model.Asset;
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
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Creating Controller Object");
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
     * Verifies AdvanceSearch option
     */
    private boolean advanceSearchValidator() {
        boolean ckbAdvanceSearch = this.view.getAdvanceSearchStatus();
        if (ckbAdvanceSearch) {
            this.searchCriteria.setAdvanceSearchStatus(this.view.getAdvanceSearchStatus());
            String typeSearch = this.view.getAdvanceSearch();
            if (typeSearch.equals("Regular files")) {
                LogHandle.getInstance().WriteLog(LogHandle.INFO, "Search Type :" + typeSearch);
                this.searchCriteria.setAdvanceSearch(typeSearch);
            }
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Advance Search was not enable");
        }
        return ckbAdvanceSearch;
    }

    /**
     * Fills the criteria in the object
     */
    private void fillCriteria() {
        List<Asset> listFilesFound = new ArrayList<>();
        listFilesFound.clear();
        view.clearJTable();
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Objects found :" + listFilesFound.size());
        String fileToSearch = this.view.getFileName();
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "File To Search :" + fileToSearch);
        String pathToSearch = this.view.getSearchPath();
        pathValidator(fileToSearch, pathToSearch);
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Path to Search :" + pathToSearch);
        if ((fileToSearch != null) && (!fileToSearch.equals(""))) {
            pathValidator(fileToSearch, pathToSearch);
            listFilesFound = search.listFilesByPath(this.searchCriteria);
            fillTable(listFilesFound);
        } else {
            view.showWarningMessage("Warning", "The file Name is empty");
        }
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Objects found :" + listFilesFound.size());

        if(advanceSearchValidator()){
            ownerValidator();
            includeHiddenFilesValidator();
        }

    }

    /**
     * Validates SizeCriteria input
     */
    private void sizeCriteriaValidator() {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting SizeCriteria:" + view.getSizeCriteria());

        // Select between < > or =
        searchCriteria.setSizeCriteria(view.getOwnerName());

//        getTbxSize
    }

    /**
     * Validates owner input
     */
    private void ownerValidator() {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting Owner:" + view.getOwnerName());
        searchCriteria.setOwnerFile(view.getOwnerName());
    }

    /**
     * Validates includeHiddenFiles input
     */
    private void includeHiddenFilesValidator() {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting includeHiddenFiles:" +
                view.includeHiddenFiles());
        searchCriteria.setFileHidden(view.includeHiddenFiles());
    }

    /**
     * Fills the Table with the list of file found with the criteria used
     *
     * @param listFilesFound
     */
    private void fillTable(List<Asset> listFilesFound) {
        for (Asset item : listFilesFound) {
//            String[] values = {item.getFileName(), item.getExtension(), item.getSize(), item.getPath(), item.getOwner()};
            String[] values = {item.getFileName(), "", item.getSize(), item.getPath(), item.getOwner()};
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Field added to column:" + item.getFileName() +
                    "" + item.getSize() + item.getPath() + item.getOwner());
            view.getTable().addRow(values);

        }
    }

//    private String[] getValues(Asset item) {
//        String extention = (item.getExtension() != "") ? item.getExtension() | "";
//        String[] values = {item.getFileName(), "", item.getSize(), item.getPath(), item.getOwner()};

//        return values;
//    }
}

