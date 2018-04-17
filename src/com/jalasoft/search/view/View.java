/**
 * Copyright (c) 2018 Jalasoft.
 * This software is confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.view;

import com.jalasoft.search.commons.LogHandle;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.toedter.calendar.JDateChooser;

/**
 * This class handles all the UI controls that are displayed in the main window
 *
 * @version 1.0
 * @author: ronald castellon
 */
public class View extends JFrame {
    private JButton btSearch;
    private JButton btCancel;
    private JButton btSelect;
    private JLabel lbFileName;
    private JLabel lbFolder;
    private JFormattedTextField tBxSearch;
    private JFormattedTextField tBxSearchPath;
    private JComboBox cBxAdvancedSearch;
    private String[] advancedSearch = {"...", "Regular files", "Multimedia", "Other"};
    private String[] searchCriteria = {"<", ">", "="};
    private String[] measureUnit = {"Bytes", "KB", "MB", "GB"};
    private Folder folder;
    private JCheckBox ckBxAdvancedSearch;
    private JLabel lbContains;
    private JFormattedTextField tBxContains;
    private JLabel lbSize;
    private JComboBox cBxSizeCriteria;
    private JFormattedTextField tBxSize;
    private JComboBox cBxMeasureUnit;
    private JLabel lbOwner;
    private JFormattedTextField tBxOwner;
    private JCheckBox chBxDate;
    private JCheckBox chBxCreated, chBxModified, chBxAccessed;
    private JRadioButton radiobtnCreated, radiobtnModified, radiobtnAccessed;
    private ButtonGroup btnGrpFileOperation;
    private JCheckBox chBxHiddenFiles;
    private JFrame mainFrame;
    private JPanel searchPanel;
    private JPanel basicSearchPanel;
    private JPanel advSearchPanel;
    private JPanel resultPanel;
    private GridBagConstraints gridBagConstraints;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    public String[][] data;
    String[] columns = {"Name", "Ext", "Size", "Path", "Owner", "Is directory", "Files found"};
    private JDialog dialogBox;
    private JDateChooser startDate;
    private JDateChooser endDate;
    private JLabel lblBetween;
    private JLabel lblAnd;
    private SimpleDateFormat simpleDateFormat;
    private JPanel dbPanel;
    private JLabel lblName, storedCriteria;
    private JFormattedTextField tBxSaveCriteria;
    private JButton btnSaveCriteria;
    private JButton btnLoadCriteria;
    private JTable tblSearchCriteria;
    private DefaultTableModel tableModelDB;
    private JScrollPane scrollPaneDB;
    private String[][] criteriaList;
    String[] criteriaColumns = {"Name", "Criteria"};

    /**
     * This method creates all the UI components
     */
    public void createWindowObjects() {
        btSearch = new JButton("Search");
        btCancel = new JButton("Close");
        btSelect = new JButton("Find..");
        cBxAdvancedSearch = new JComboBox(advancedSearch);
        cBxAdvancedSearch.setEnabled(false);
        ckBxAdvancedSearch = new JCheckBox("Advanced Search");
        lbFileName = new JLabel("File name:");
        lbFolder = new JLabel("Path: ");
        tBxSearch = new JFormattedTextField();
        tBxSearch.setPreferredSize(new Dimension(140, 25));
        tBxSearchPath = new JFormattedTextField();
        Font font = new Font("TimesRoman", Font.ITALIC, 16);
        Dimension largeDimension = new Dimension(120, 25);
        Dimension smallDimension = new Dimension(70, 25);
        tBxSearchPath.setMinimumSize(largeDimension);
        tBxSearchPath.setFont(font);
        tBxSearchPath.setPreferredSize(new Dimension(140, 25));
        lbContains = new JLabel("Contains:");
        lbContains.setLocation(300, 250);
        tBxContains = new JFormattedTextField();
        tBxContains.setMinimumSize(largeDimension);
        tBxContains.setPreferredSize(new Dimension(250, 25));
        lbSize = new JLabel("File size is");
        cBxSizeCriteria = new JComboBox(searchCriteria);
        tBxSize = new JFormattedTextField();
        tBxSize.setMinimumSize(smallDimension);
        tBxSize.setPreferredSize(new Dimension(70, 25));
        cBxMeasureUnit = new JComboBox(measureUnit);
        lbOwner = new JLabel("Owner is:");
        tBxOwner = new JFormattedTextField();
        tBxOwner.setMinimumSize(largeDimension);
        tBxOwner.setPreferredSize(new Dimension(120, 25));
        chBxDate = new JCheckBox("Include if file was:  ");
        chBxCreated = new JCheckBox("Created");
        chBxModified = new JCheckBox("Modified");
        chBxAccessed = new JCheckBox("Accessed");
        chBxHiddenFiles = new JCheckBox("Only hidden files  ");
        mainFrame = new JFrame();
        searchPanel = new JPanel(new GridBagLayout());
        basicSearchPanel = new JPanel(new GridBagLayout());
        advSearchPanel = new JPanel(new GridBagLayout());
        dbPanel = new JPanel(new GridBagLayout());
        dbPanel.setVisible(true);
        gridBagConstraints = new GridBagConstraints();
        resultPanel = new JPanel();
        data = new String[][]{};
        tableModel = new DefaultTableModel(data, columns);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        criteriaList = new String[][]{};
        tableModelDB = new DefaultTableModel(criteriaList, criteriaColumns);
        tblSearchCriteria = new JTable(tableModelDB);
        scrollPaneDB = new JScrollPane(tblSearchCriteria);
        dialogBox = new JDialog();
        startDate = new JDateChooser();
        startDate.setMinimumSize(largeDimension);
        startDate.setPreferredSize(new Dimension(120, 25));
        startDate.setDate(new Date());
        startDate.setEnabled(false);
        endDate = new JDateChooser();
        endDate.setMinimumSize(largeDimension);
        endDate.setPreferredSize(new Dimension(120, 25));
        endDate.setDate(new Date());
        endDate.setEnabled(false);
        lblBetween = new JLabel("Between");
        lblBetween.setEnabled(false);
        lblAnd = new JLabel("And");
        lblAnd.setEnabled(false);
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        btnGrpFileOperation = new ButtonGroup();
        radiobtnCreated = new JRadioButton("Created");
        radiobtnCreated.setEnabled(false);
        radiobtnModified = new JRadioButton("Modified");
        radiobtnModified.setEnabled(false);
        radiobtnAccessed = new JRadioButton("Accessed");
        radiobtnAccessed.setEnabled(false);
        btnGrpFileOperation.add(radiobtnCreated);
        btnGrpFileOperation.add(radiobtnModified);
        btnGrpFileOperation.add(radiobtnAccessed);
        enableAdvancedSearch(false);
        lblName = new JLabel("Name:");
        storedCriteria = new JLabel("Stored Criterias");
        tBxSaveCriteria = new JFormattedTextField();
        tBxSaveCriteria.setPreferredSize(new Dimension(120, 25));
        btnSaveCriteria = new JButton("Save");
        btnLoadCriteria = new JButton("Load");
    }

    /**
     * This method sets all objects created to its corresponding panel
     */
    public void setObjectsInWindow() {
        //Setting components in Basic search Panel
        gridBagConstraints.insets = new Insets(0, 0, 1, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        basicSearchPanel.add(lbFolder, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        basicSearchPanel.add(tBxSearchPath, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        basicSearchPanel.add(btSelect, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        basicSearchPanel.add(lbFileName, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        basicSearchPanel.add(tBxSearch, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        basicSearchPanel.add(btSearch, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        basicSearchPanel.add(btCancel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        basicSearchPanel.add(ckBxAdvancedSearch, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        basicSearchPanel.add(cBxAdvancedSearch, gridBagConstraints);
        //Setting components in the advanced Search Panel
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        advSearchPanel.add(lbContains, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        advSearchPanel.add(tBxContains, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        advSearchPanel.add(lbSize, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        advSearchPanel.add(cBxSizeCriteria, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        advSearchPanel.add(tBxSize, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        advSearchPanel.add(cBxMeasureUnit, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        advSearchPanel.add(lbOwner, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        advSearchPanel.add(tBxOwner, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        advSearchPanel.add(chBxHiddenFiles, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        advSearchPanel.add(chBxDate, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        advSearchPanel.add(radiobtnCreated, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        advSearchPanel.add(radiobtnModified, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 6;
        advSearchPanel.add(radiobtnAccessed, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        advSearchPanel.add(lblBetween, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        advSearchPanel.add(startDate, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        advSearchPanel.add(lblAnd, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 7;
        advSearchPanel.add(endDate, gridBagConstraints);
        //Setting the controls for the DB panel
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        dbPanel.add(storedCriteria, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        dbPanel.add(lblName, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        dbPanel.add(tBxSaveCriteria, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        dbPanel.add(btnSaveCriteria, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        dbPanel.add(btnLoadCriteria, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        tblSearchCriteria.setPreferredScrollableViewportSize(new Dimension(240, 50));
        dbPanel.add(scrollPaneDB, gridBagConstraints);
        //Setting the table in the Result pane
        table.setPreferredScrollableViewportSize(new Dimension(740, 635));
        table.setFillsViewportHeight(true);
        resultPanel.add(scrollPane);
        //Adding basic search and advanced search panels into Search panel
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        basicSearchPanel.setPreferredSize(new Dimension(500, 160));
        searchPanel.add(basicSearchPanel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        advSearchPanel.setPreferredSize(new Dimension(500, 250));
        searchPanel.add(advSearchPanel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        dbPanel.setPreferredSize(new Dimension(500, 235));
        searchPanel.add(dbPanel, gridBagConstraints);
        searchPanel.setBackground(Color.LIGHT_GRAY);
        resultPanel.setBackground(Color.lightGray);
        //Adding the two main panels in the frame
        mainFrame.add(searchPanel, BorderLayout.WEST);
        mainFrame.add(resultPanel, BorderLayout.CENTER);
    }

    /**
     *
     */
    public JFormattedTextField SettbSearchPath() {
        return tBxSearchPath;
    }

    /**
     * This method returns the criteria with which to compare the file size in the advanced
     * search option, the value returned is one of the following (>, =, <)
     *
     * @return String
     */
    public String getComparisonCriteria() {
        return cBxSizeCriteria.getSelectedItem().toString();
    }

    /**
     * This method returns a string that contains the size of the file
     * that is part of the criteria in the advanced
     * search option, the value returned is a string representing a number
     *
     * @return String
     */
    public String getFileSizeCriteria() {
        return tBxSize.getText();
    }

    /**
     * This method returns a string that contains the file size unit that
     * will be used as part of the criteria in the advanced
     * search option, the value returned is a string representing a measure unit (Bytes, KB, MB, GB)
     *
     * @return String
     */
    public String getFileSizeUnitCriteria() {
        return cBxMeasureUnit.getSelectedItem().toString();
    }

    /**
     * This method returns the content of the object tBxOwner, which is the advanced criteria for
     * searching files with an specific owner
     *
     * @return String
     */
    public String getOwnerName() {
        return this.tBxOwner.getText();
    }

    /**
     * This method sets a default path the is specified in the param "path'
     *
     * @param path
     */
    public void setDefaultPath(String path) {
        tBxSearchPath.setText(path);
    }

    /**
     * This method returns a string with the Start date selected from the Calendar component within
     * the advanced search option
     */
    public String getStartDate() {
        String date = simpleDateFormat.format(startDate.getDate());
        return date;
    }

    /**
     * This method returns a string with the End date selected from the Calendar component within
     * the advanced search option
     */
    public String getEndDate() {
        String date = simpleDateFormat.format(endDate.getDate());
        return date;
    }

    /**
     * This method displays a warning popup window with a title and a message received as parameters
     *
     * @param title
     * @param message
     */
    public void showWarningMessage(String title, String message) {
        JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method displays a error popup window with a title and a message received as parameters
     *
     * @param title
     * @param message
     */
    public void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.ERROR_MESSAGE);

    }

    /**
     * Getter method to return the table tableModel
     *
     * @return DefaultTableModel
     */
    public DefaultTableModel getTable() {
        return this.tableModel;
    }

    /**
     * Clears the JTable component
     */
    public void clearJTable() {
        tableModel.setRowCount(0);
    }

    /**
     * This method returns a value to indicate whether hidden files are included in the result or not
     *
     * @return Boolean
     */
    public Boolean includeHiddenFiles() {
        return this.chBxHiddenFiles.isSelected();
    }

    /**
     * This method returns the content of the object tBxContains, which is the advanced criteria for
     * searching files with specific content
     *
     * @return String
     */
    public String stringContainedInSearchCriteria() {
        return this.tBxContains.getText();
    }

    /**
     * This method returns a boolean value to indicate whether the advanced search will contain files
     * created withing a given time range or not.
     *
     * @return boolean
     */
    public boolean searchIfCreated() {
        return radiobtnCreated.isSelected();
    }

    /**
     * This method returns a boolean value to indicate whether the advanced search is enabled or not
     *
     * @return boolean
     */
    public boolean isAdvancedSearchEnabled() {
        return ckBxAdvancedSearch.isSelected();
    }

    /**
     * This method returns a boolean value to indicate whether the advanced search will contain files
     * that were modified withing a given time range or not.
     *
     * @return boolean
     */
    public boolean searchIfModified() {
        return radiobtnModified.isSelected();
    }

    /**
     * This method returns a boolean value to indicate whether the advanced search will contain files
     * that were modified withing a given time range or not.
     *
     * @return boolean
     */
    public boolean searchIfAccessed() {
        return radiobtnAccessed.isSelected();
    }

    /**
     * This method returns from among the RadioButtons "Created", "Modified" and "Accessed", the selected
     * option to use in the search with a given time range, if no option was selected it returns an empty
     * string ("")
     *
     * @return String
     */
    public String getCriteria4TimeRangeSearch() {
        if (radiobtnCreated.isSelected()) {
            return "Created";
        } else if (radiobtnModified.isSelected()) {
            return "Modified";
        } else if (radiobtnAccessed.isSelected()) {
            return "Accessed";
        } else return "";
    }

    /**
     * This method returns a value to indicate whether the string contained in the object tBxContains
     * should be searched in the content of the files  or not.
     *
     * @return Boolean
     */
    public boolean searchInsideFile() {
        return true;
    }

    /**
     * Gets AdvancedSearch selected
     *
     * @return String
     */
    public String getAdvanceSearch() {
        return cBxAdvancedSearch.getSelectedItem().toString();
    }

    /**
     * Gets SizeCriteria selected
     *
     * @return
     */
    public String getSizeCriteria() {
        return cBxSizeCriteria.getSelectedItem().toString();
    }

    /**
     * Gets Size from text box
     *
     * @return
     */
    public String getTbxSize() {
        return tBxSize.getValue().toString();
    }

    /**
     * This method initializes the UI by creating and setting all components in the main window
     *
     * @param windowName
     */
    public void initUI(String windowName) {
        createWindowObjects();
        setObjectsInWindow();
        setMainWindow(windowName);
        enableEventsListening();
    }

    /**
     * This method configures all setting on the frame that will contain all panels where the UI will display
     *
     * @param windowTitle
     */
    public void setMainWindow(String windowTitle) {
        mainFrame.setVisible(true);
        mainFrame.setSize(1250, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setTitle(windowTitle);
    }

    /**
     * Returns the btSearch button
     *
     * @return JButton
     */
    public JButton getBtSearch() {
        return btSearch;
    }

    /**
     * Gets the File Name
     *
     * @return String
     */
    public String getFileName() {
        return this.tBxSearch.getText();
    }

    /**
     * Gets the searchWithCriteria path
     *
     * @return String
     */
    public String getSearchPath() {
        return this.tBxSearchPath.getText();
    }

    /**
     * Gets AdvanceSearch status
     * true  : if checkbox is selected
     * false : if checkbox is not selected
     *
     * @return boolean
     */
    public boolean getAdvanceSearchStatus() {
        return ckBxAdvancedSearch.isSelected();
    }

    /**
     * This method returns the option from the radio button selected to include in the criteria
     * if the file was "Created", "Modified" or "Accessed", otherwise it returns the string "Null"
     *
     * @return String
     */
    public String getRadioButtonSelected() {
        if (radiobtnCreated.isSelected()) return "Created";
        else if (radiobtnModified.isSelected()) return "Modified";
        else if (radiobtnAccessed.isSelected()) return "Accessed";
        else return "Null";
    }

    /**
     * This method sets the default path where the search will perform
     *
     * @param path
     */
    public void setSearchPath(String path) {
        tBxSearchPath.setText(path);
    }

    /**
     * This method sets the name of the file that will be used to perform the search
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        tBxSearch.setText(fileName);
    }

    /**
     * This method sets the content that will be included for the search in the advanced configuration,
     * the input is a string that contains the criteria search
     *
     * @param criteriaContains
     */
    public void settBxContains(String criteriaContains) {
        tBxContains.setText(criteriaContains);
    }

    /**
     * This method sets the operand criteria that will be displayed in the cBxSizeCriteria combo box, the
     * input is an integer that contains the index of the component that will be set as selected
     * 0 is >
     * 1 is <
     * 2 is =
     *
     * @param index
     */
    public void setCriteriaSizeOperand(int index) {
        cBxSizeCriteria.setSelectedIndex(index);
    }

    /**
     * This method sets the size of the file that will be used for the advances search criteria, the
     * input is a string that contains a number that represents the size.
     *
     * @param size
     */
    public void setFileSize(String size) {
        tBxSize.setText(size);
    }

    /**
     * This method sets the file size unit that will be displayed in the cBxMeasureUnit combo box, the
     * input is an integer that contains the index of the component that will be set as selected
     * 0 is for Bytes
     * 1 is for Kb
     * 2 is for Mb
     * 3 is for Gb
     *
     * @param index
     */
    public void setCriteriaSizeUnit(int index) {
        cBxMeasureUnit.setSelectedIndex(index);
    }

    /**
     * This method sets the name of the owner in the tBxOwner text box, the input is a string with
     * the name to be set
     *
     * @param name
     */
    public void setOwner(String name) {
        tBxOwner.setText(name);
    }

    /**
     * This method sets the option to be marked selected from the radio buttons group, the input to
     * receive is a string with one of the options "Created", "Modified" or "Accessed"
     *
     * @param option
     */
    public void setRadioButton(String option) {
        switch (option) {
            case "Creaed":
                radiobtnCreated.setSelected(true);
                break;
            case "Modified":
                radiobtnModified.setSelected(true);
                break;
            case "Accessed":
                radiobtnAccessed.setSelected(true);
                break;
            default:
                break;
        }
    }

    public void setDateChkBx(Boolean isChecked) {
        chBxDate.setSelected(isChecked);
    }

    public Boolean getDateChkBxSelected() {
        return chBxDate.isSelected();
    }

    /**
     * This method sets the starting date in the search with time range criteria, the imput is a
     * string containing the date in the format "dd/MM/yyyy"
     *
     * @param stringDate
     */
    public void setStartDate(String stringDate) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
            startDate.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the starting date in the search with time range criteria, the imput is a
     * string containing the date in the format "dd/MM/yyyy"
     *
     * @param stringDate
     */
    public void setEndDate(String stringDate) {
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stringDate);
            endDate.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets the state of the checkbox ckBxAdvancedSearch to the state passed to the
     * parameter "checked"
     *
     * @param checked
     */
    public void setAdvSearchChkBx(Boolean checked) {
        ckBxAdvancedSearch.setSelected(checked);
    }

    /**
     * *This method sets the type of search that will be displayed in the cBxAdvancedSearch combo box, the
     * input is an integer that contains the index of the element that will be set as selected
     * 0 is for ...
     * 1 is for Regular files
     * 2 is for Multimedia
     * 3 is for Other
     *
     * @param index
     */
    public void setAdvSearchComboBx(int index) {
        cBxAdvancedSearch.setSelectedIndex(index);
    }

    /**
     * This method enables or disables all componets related to the Date search criteria, the input is
     * a Boolean to enable the controls = true, or to disable the controls = false.
     *
     * @param isEnabled
     */
    public void enableDateCriteria(Boolean isEnabled) {
        radiobtnCreated.setEnabled(isEnabled);
        radiobtnModified.setEnabled(isEnabled);
        radiobtnAccessed.setEnabled(isEnabled);
        lblBetween.setEnabled(isEnabled);
        lblAnd.setEnabled(isEnabled);
        startDate.setEnabled(isEnabled);
        endDate.setEnabled(isEnabled);
    }

    /**
     * This method enables or disables the advanced search controls from the UI, the parameter "visible"
     * if true, will show it, otherwise these components will be disabled.
     *
     * @param visible
     */
    public void enableAdvancedSearch(Boolean visible) {
        lbContains.setVisible(visible);
        tBxContains.setVisible(visible);
        lbSize.setVisible(visible);
        cBxSizeCriteria.setVisible(visible);
        tBxSize.setVisible(visible);
        cBxMeasureUnit.setVisible(visible);
        lbOwner.setVisible(visible);
        tBxOwner.setVisible(visible);
        chBxDate.setVisible(visible);
        radiobtnCreated.setVisible(visible);
        radiobtnModified.setVisible(visible);
        radiobtnAccessed.setVisible(visible);
        lblBetween.setVisible(visible);
        startDate.setVisible(visible);
        lblAnd.setVisible(visible);
        endDate.setVisible(visible);
        chBxHiddenFiles.setVisible(visible);
    }


    /**
     * This method keeps track of all events that occur with the objects: btSelect, btSearch,
     * btCancel, ckBxAdvancedSearch, cBxAdvancedSearch
     */
    public void enableEventsListening() {
        btSelect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                folder = new Folder();
                String stPath = folder.getPath();
                LogHandle.getInstance().WriteLog(LogHandle.INFO, "Configuring path : " + stPath);
                tBxSearchPath.setText(stPath);
            }
        });

        btSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });
        // Close Application
        btCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogHandle.getInstance().WriteLog(LogHandle.INFO, "Exit application");
                super.mouseClicked(e);
                System.exit(0);
            }
        });


        btCancel.addMouseListener(new MouseAdapter() {
        });
        btnLoadCriteria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LogHandle.getInstance().WriteLog(LogHandle.INFO, "Set search path");
                super.mouseClicked(e);
            }
        });
        chBxDate.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (chBxDate.isSelected()) {
                    LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Enabling date controls from Advanced Search Panel");
                    enableDateCriteria(true);
                } else {
                    enableDateCriteria(false);
                }
            }
        });
        // Advance Search
        ckBxAdvancedSearch.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (ckBxAdvancedSearch.isSelected()) {
                    LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Displaying Advance Search Panel");
                    cBxAdvancedSearch.setEnabled(true);
                    cBxAdvancedSearch.setVisible(true);

                } else {
                    LogHandle.getInstance().WriteLog(LogHandle.DEBUG, "Hiding Advance Search Panel");
                    enableAdvancedSearch(false);
                    cBxAdvancedSearch.setSelectedIndex(0);
                }
            }
        });
        tBxSize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char character = e.getKeyChar();
                //Verificamos si la tecla pulsada no es un digito
                if (((character < '0') || (character > '9')) && (character != '\b')) {
                    e.consume();
                }
            }
        });
        cBxAdvancedSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cBxAdvancedSearch.getSelectedItem().toString().equals("Regular files")) {
                    LogHandle.getInstance().WriteLog(LogHandle.INFO, "Enable advance search panel");
                    enableAdvancedSearch(true);
                } else {
                    LogHandle.getInstance().WriteLog(LogHandle.INFO, "Disable advance search panel");
                    enableAdvancedSearch(false);
                }
            }
        });
    }
}

