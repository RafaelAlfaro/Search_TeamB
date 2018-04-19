/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.controller;

import com.jalasoft.search.commons.DigitalUnitConverter;
import com.jalasoft.search.commons.LogHandle;
import com.jalasoft.search.commons.SearchQuery;
import com.jalasoft.search.commons.PathHandler;
import com.jalasoft.search.commons.ToolHandler;
import com.jalasoft.search.model.Asset;
import com.jalasoft.search.model.FolderSearch;
import com.jalasoft.search.model.SearchCriteria;
import com.jalasoft.search.model.Search;
import com.jalasoft.search.view.View;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private int selectRow = -1;

    public Controller(Search search, View view) {
        LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Creating Controller Object");
        this.view = view;
        this.search = search;
        this.searchCriteria = new SearchCriteria();
        this.view.getBtSearch().addActionListener(e -> fillCriteria());
        this.view.getBtnSaveCriterion().addActionListener(e -> saveCriterion());
        this.view.getBtnLoadCriterion().addActionListener(e -> loadCriteria());
        getRowSelected();
        this.view.getBtnApplyCriterion().addActionListener((e -> applySelectedCriterion(selectRow)));
    }

    private void getRowSelected() {
        this.view.getTblSearchCriteria().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String message;
                try {
                    if (view.getTblSearchCriteria().getSelectedRow() > -1) {
                        selectRow = 0;
                        int row = view.getTblSearchCriteria().getSelectedRow() + 1;
                        LogHandle.getInstance().WriteLog(LogHandle.ERROR, "Criterion selected : " + row);
                        selectRow = row;
                    }
                } catch (Exception ex) {
                    message = "Exception : " + ex.toString();
                    LogHandle.getInstance().WriteLog(LogHandle.ERROR, message);
                }
            }
        });
    }

    /**
     * Applies the selected criterion
     */
    private void applySelectedCriterion(int row) {
        String message;
        try {
            SearchQuery searchQuery = new SearchQuery();
            Map<Integer, SearchCriteria> CriteriaMap = searchQuery.getAllData();
            applyCriteria(ToolHandler.getTheSearchCriterion(CriteriaMap, row));
        } catch (Exception ex) {
            message = "Exception : " + ex.toString();
            LogHandle.getInstance().WriteLog(LogHandle.ERROR, message);
        }
    }


    /**
     * Verifies if the path is correct
     *
     * @param searchFileName: The path gets from view
     */
    private void pathValidator(String searchFileName, String searchPath) {
        if (searchPath.isEmpty()) {
            searchPath = System.getProperty("user.dir");
            view.SetTbSearchPath().setText(searchPath);
        }
        PathHandler validator = new PathHandler(searchPath);
        if (!validator.isValidPath()) {
            LogHandle.getInstance().WriteLog(LogHandle.ERROR, "The Path is not correct");
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
            this.searchCriteria.setEnableAdvanceSearch(this.view.getAdvanceSearchStatus());
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
     * Load the criteria list from database
     */
    private void loadCriteria() {
        view.clearJTableDB();
        String message;
        try {
            SearchQuery searchQuery = new SearchQuery();
            Map<Integer, SearchCriteria> CriteriaMap = searchQuery.getAllData();
            fillTableFromDB(searchQuery.getAllData());
        } catch (Exception e) {
            message = "Exception : " + e.toString();
            LogHandle.getInstance().WriteLog(LogHandle.ERROR, message);
        }
    }

    private void fillTableFromDB(Map<Integer, SearchCriteria> CriteriaMap) {
        for (Map.Entry<Integer, SearchCriteria> criteria : CriteriaMap.entrySet()) {
            Integer key = criteria.getKey();
            String criterionName = criteria.getValue().getCriteriaName();

            String[] values = {Integer.toString(key), criterionName};
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Field added to column: Key ->" + key +
                    " Criterion ->" + criterionName);
            view.getTableDB().addRow(values);
        }

    }

    /**
     * Save a criterion with a specific name
     */
    private void saveCriterion() {
        String message;
        try {
            SearchQuery serachQuery = new SearchQuery();
            String criterionName = view.gettBxSaveCriterion().getText();
            if (!criterionName.isEmpty()) {
                Map<Integer, SearchCriteria> CriteriaMap = serachQuery.getAllData();
                if (ToolHandler.existCriteriaName(CriteriaMap, criterionName) == null) {
                    message = "The \"" + criterionName + "\" criterion was saved successfully in the database.";
                    fillCriteria();
                    searchCriteria.setCriteriaName(criterionName);
                    serachQuery.addCriterial(searchCriteria.toString());
                    LogHandle.getInstance().WriteLog(LogHandle.INFO, message);
                    view.showInformationMessage("Saved:", message);
                    loadCriteria();
                } else {
                    message = "Already this criterion exist in the criteria list. Please select another name.";
                    view.showErrorMessage("Error Message", message);
                    LogHandle.getInstance().WriteLog(LogHandle.INFO, message);
                }
            } else {
                message = "The criterion must have a name to be saved";
                view.showErrorMessage("Error Message", message);
                LogHandle.getInstance().WriteLog(LogHandle.INFO, message);
            }
        } catch (Exception e) {
            message = "Exception : " + e.toString();
            LogHandle.getInstance().WriteLog(LogHandle.ERROR, message);
        }
    }

    /**
     * Fills the criteria in the object
     */
    private void fillCriteria() {
        searchCriteria.clearCriteria();
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
            advanceSearchCriteria();
            search.cleanList();
            listFilesFound = search.listFilesByPath(this.searchCriteria);
            fillTable(listFilesFound);
        } else {
            view.showWarningMessage("Warning", "The file Name is empty");
        }
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Objects found :" + listFilesFound.size());
    }

    private void advanceSearchCriteria() {
        if (advanceSearchValidator()) {
            ownerValidator();
            includeHiddenFilesValidator();
            sizeCriteriaValidator();
            stringContainedInSearchCriteria();
            dateSearchValidator();
        }
    }

    /**
     * This method verifies that date search is selected
     */
    private void dateSearchValidator() {
        if (view.getDateChkBxSelected()) {
            searchCriteria.setEnableDateCriterion(view.getDateChkBxSelected());
            fileCreatedValidator();
            fileModifiedValidator();
            fileAccessedValidator();
            startDateValidator();
            endDateValidator();
        } else {
            searchCriteria.setDateCriteria(' ');
        }
    }

    /**
     * Validates FileCreated input
     */
    private void fileCreatedValidator() {
        searchCriteria.setFileCreated(view.searchIfCreated());
        if (searchCriteria.getFileCreated()) {
            searchCriteria.setDateCriteria('c');
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting File Created Criteria:" +
                    searchCriteria.getFileCreated());
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "File Created Criteria not configured");
        }
    }

    /**
     * Validates FileModified input
     */
    private void fileModifiedValidator() {
        searchCriteria.setFileModified(view.searchIfModified());
        if (searchCriteria.getFileModified()) {
            searchCriteria.setDateCriteria('u');
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting File Modified Criteria:" +
                    searchCriteria.getFileModified());
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "File Modified Criteria not configured");
        }
    }

    /**
     * Validates FileAccessed input
     */
    private void fileAccessedValidator() {
        searchCriteria.setFileAccessed(this.view.searchIfAccessed());
        if (searchCriteria.getFileAccessed()) {
            searchCriteria.setDateCriteria('a');
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting File Accessed Criteria:" +
                    searchCriteria.getFileAccessed());
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "File Accessed Criteria not configured");
        }
    }

    /**
     * Validates StartDate validator input
     */
    private void startDateValidator() {
        searchCriteria.setStartDateCriteria(this.view.getStartDate());
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting Start Date Criteria:" +
                searchCriteria.getStartDateCriteria());
    }

    /**
     * Validates EndDate validator input
     */
    private void endDateValidator() {
        searchCriteria.setEndDateCriteria(this.view.getEndDate());
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting End Date Criteria:" +
                searchCriteria.getEndDateCriteria());
    }

    /**
     * Validates Contained input
     */
    private void stringContainedInSearchCriteria() {
        String containsString = view.stringContainedInSearchCriteria();
        if (!containsString.isEmpty()) {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Setting Contains Criteria:" + containsString);
            searchCriteria.setContains(containsString);
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Contains Criteria is empty");
        }
    }

    /**
     * Validates SizeCriteria input
     */
    private void sizeCriteriaValidator() {
        if (!view.getFileSizeCriteria().isEmpty()) {
            LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Setting SizeCriteria:" +
                    view.getSizeCriteria());
            this.searchCriteria.setSizeCriteria(view.getSizeCriteria());
            LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Setting SizeCriteria:" +
                    view.getFileSizeUnitCriteria());
            this.searchCriteria.setMeasureUnit(view.getFileSizeUnitCriteria());
            DigitalUnitConverter converter = new DigitalUnitConverter();
            long sizeFileToSearch = Long.parseLong(view.getFileSizeCriteria());
            this.searchCriteria.setSizeFile(converter.convertTo(sizeFileToSearch, view.getFileSizeUnitCriteria(),
                    "Bytes"));
            LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Size to Search :" + view.getFileSizeCriteria()
                    + " " + view.getFileSizeUnitCriteria());
        } else {
            LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Size is empty");
        }
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
        String isDirectory;
        for (Asset item : listFilesFound) {
            if (item instanceof FolderSearch) {
                isDirectory = "Yes";
            } else {
                isDirectory = "";
            }
            String[] values = {item.getFileName(), "", item.getSize(), item.getPath(), item.getOwner(), isDirectory};
            LogHandle.getInstance().WriteLog(LogHandle.INFO, "Field added to column:" + item.getFileName() +
                    "" + item.getSize() + item.getPath() + item.getOwner());
            view.getTable().addRow(values);

        }
    }

    /**
     * This method applies a criteria with the set of  pre-defined values to all the components of
     * the search. THIS IS A TEMPORAL METHOD, the settings should be applied from the controller
     *
     * @param searchToCriteria Criterion
     */
    public void applyCriteria(SearchCriteria searchToCriteria) {

        DigitalUnitConverter converter = new DigitalUnitConverter();

        view.setSearchPath(searchToCriteria.getSearchPath());
        view.setFileName(searchToCriteria.getFileName());
        view.setAdvSearchChkBx(searchToCriteria.getEnableAdvanceSearch());

        if (searchToCriteria.getEnableAdvanceSearch()) {
            view.setAdvSearchComboBx(searchToCriteria.getAdvanceSearch());
            view.settBxContains(searchToCriteria.getContains());

            view.setCriteriaSizeOperand(searchToCriteria.getSizeCriteria());
            view.setFileSize(Long.toString(converter.convertTo(searchToCriteria.getSizeFile(), "Bytes",
                    searchToCriteria.getMeasureUnit())));

            view.setCriteriaSizeUnit(searchToCriteria.getMeasureUnit());
            view.setOwner(searchToCriteria.getOwnerFile());

            view.setDateChkBx(searchToCriteria.getEnableDateCriterion());
            view.setRadioButton(searchToCriteria.getActiveStaus());
            view.setStartDate(searchToCriteria.getStartDateCriteria());
            view.setEndDate(searchToCriteria.getEndDateCriteria());
            view.setincludeHiddenFiles(searchToCriteria.getFileHidden());
        }
    }

}
