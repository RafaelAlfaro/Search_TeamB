/**
 * Copyright (c) 2018 Jalasoft.
 * This software is confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.view;
/**
 * This class handles all the UI controls that are displayed in the main window
 *
 * @author: ronald castellon
 * @version 1.0
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private String[] searchCriteria = {"<", ">", "=", "<>"};
    private String[] measureUnit = {"Bytes", "KB", "MB", "GB"};
    private Folder folder;
    private JCheckBox ckBxAdvancedSearch;
    private JLabel lbContains;
    private JFormattedTextField tBxContains;
    private JLabel lbInTitle;
    private JCheckBox chBxInTitle;
    private JLabel lbInsideFile;
    private JCheckBox ckBxInsideFile;
    private JLabel lbSize;
    private JComboBox cBxSizeCriteria;
    private JFormattedTextField tBxSize;
    private JComboBox cBxMeasureUnit;
    private JLabel lbOwner;
    private JFormattedTextField tBxOwner;
    private JLabel lbDate;
    private JCheckBox chBxCreated;
    private JLabel lbCreated;
    private JCheckBox chBxModified;
    private JLabel lbModified;
    private JCheckBox chBxAccessed;
    private JLabel lbAccessed;
    private JCheckBox chBxHiddenFiles;
    private JLabel lbHiddenFiles;
    private JCheckBox ckBxHiddenFilesOnly;
    private JLabel lbHiddenFilesOnly;
    private JFrame mainFrame;
    private JPanel searchPanel;
    private JPanel resultPanel;
    private GridBagConstraints gridBagConstraints;
    private JTable table;
    private DefaultTableModel tableModel;
    private JScrollPane scrollPane;
    public String[][] data;
    private JPanel basicSearchPanel;
    static final String EMPTY = "                                 ";

    /**
     * this method creates all the UI components
     */
    public void createWindowObjects() {
        btSearch = new JButton("Search");
        btCancel = new JButton("Close");
        btSelect = new JButton("Find..");
        cBxAdvancedSearch = new JComboBox(advancedSearch);
        ckBxAdvancedSearch = new JCheckBox("Advanced Search");
        lbFileName = new JLabel("File name:");
        lbFolder = new JLabel("Path: ");
        tBxSearch = new JFormattedTextField(EMPTY);
        tBxSearchPath = new JFormattedTextField(EMPTY);
        lbContains = new JLabel("Contains:");
        lbContains.setLocation(300, 250);
        tBxContains = new JFormattedTextField();
        tBxContains.setPreferredSize(new Dimension(120, 25));
        lbInTitle = new JLabel("In title");
        chBxInTitle = new JCheckBox();
        lbInsideFile = new JLabel("Inside file");
        ckBxInsideFile = new JCheckBox();
        lbSize = new JLabel("File size");
        cBxSizeCriteria = new JComboBox(searchCriteria);
        tBxSize = new JFormattedTextField();
        tBxSize.setPreferredSize(new Dimension(40, 25));
        cBxMeasureUnit = new JComboBox(measureUnit);
        lbOwner = new JLabel("Owner is:");
        tBxOwner = new JFormattedTextField();
        tBxOwner.setPreferredSize(new Dimension(120, 25));
        lbDate = new JLabel("Date");
        chBxCreated = new JCheckBox();
        lbCreated = new JLabel("Created");
        chBxModified = new JCheckBox(" JAJAJA");
        lbModified = new JLabel("Modified");
        chBxAccessed = new JCheckBox();
        lbAccessed = new JLabel("Accessed");
        chBxHiddenFiles = new JCheckBox();
        lbHiddenFiles = new JLabel("Include Hidden files");
        ckBxHiddenFilesOnly = new JCheckBox();
        lbHiddenFilesOnly = new JLabel("Hidden files only");
        mainFrame = new JFrame();
        searchPanel = new JPanel(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        resultPanel = new JPanel();
        basicSearchPanel = new JPanel();
    }

    /**
     * This method sets all objects created to its corresponding panel
     */
    public void setObjectsInWindow() {
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        searchPanel.add(lbFolder, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        searchPanel.add(tBxSearchPath, gridBagConstraints);
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        searchPanel.add(btSelect, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        searchPanel.add(lbFileName, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        searchPanel.add(tBxSearch, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        searchPanel.add(ckBxAdvancedSearch, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        searchPanel.add(cBxAdvancedSearch, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        searchPanel.add(btSearch, gridBagConstraints);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        searchPanel.add(btCancel, gridBagConstraints);
        String[] columns = {"File", "Ext", "Size", "Path", "Owner"};
        data = new String[][]{};
        tableModel = new DefaultTableModel(data, columns);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(855, 650));
        table.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(table);
        resultPanel.add(scrollPane);
        basicSearchPanel.add(searchPanel, BorderLayout.NORTH);
        mainFrame.add(basicSearchPanel, BorderLayout.WEST);
        mainFrame.add(resultPanel, BorderLayout.CENTER);
    }

    /**
     * Getter method to return the table tableModel
     * @return DefaultTableModel
     */
    public DefaultTableModel getTable() {
        return this.tableModel;
    }

    /**
     * This method initializes the UI by creating and setting all components in the main window
     * @param windowName
     */
    public void initUI(String windowName) {
        createWindowObjects();
        setMainWindow(windowName);
        setObjectsInWindow();
        enableEventsListening();
    }

    /**
     * This method configures all setting on the frame that will contain all panels where the UI will display
     * @param windowTitle
     */
    public void setMainWindow(String windowTitle) {
        mainFrame.setVisible(true);
        mainFrame.setSize(1250, 700);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchPanel.setBackground(Color.LIGHT_GRAY);
        resultPanel.setBackground(Color.gray);
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
     * This method keeps track of all events that occur with the objects: btSelect, btSearch,
     * btCancel, ckBxAdvancedSearch, cBxAdvancedSearch
     */
    public void enableEventsListening() {
        btSelect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                folder = new Folder();
                String string = folder.getPath().toString();
                tBxSearchPath.setText(string);
                gridBagConstraints.gridx = 1;
                gridBagConstraints.gridy = 1;
                searchPanel.add(tBxSearchPath, gridBagConstraints);
            }
        });

        btSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        btCancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        ckBxAdvancedSearch.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (ckBxAdvancedSearch.isSelected()) {
                    cBxAdvancedSearch.setEnabled(true);
                    cBxAdvancedSearch.setVisible(true);
                } else {
                    cBxAdvancedSearch.setEnabled(false);
                    cBxAdvancedSearch.setSelectedIndex(0);
                    //hideAdvancedSearch();
                }
            }
        });

        cBxAdvancedSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cBxAdvancedSearch.getSelectedItem().toString() == "Regular files") {
                    //showAdvancedSearch();
                } else {
                    //hideAdvancedSearch();
                }
            }
        });
    }

}
