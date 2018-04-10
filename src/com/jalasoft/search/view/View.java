/**
 * Copyright (c) 2018 Jalasoft.
 * This software is confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.view;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

/**
 * This class handles all the UI controls that are displayed in the main window
 *
 * @author: ronald castellon
 * @version 1.0
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
    private String[] searchCriteria = {"<", ">", "=", "<>"};
    private String[] measureUnit = {"Bytes", "KB", "MB", "GB"};
    private Folder folder;
    private JCheckBox ckBxAdvancedSearch;
    private JLabel lbContains;
    private JFormattedTextField tBxContains;
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
    private JCheckBox ckBxHiddenFilesOnly;
    private JLabel lbHiddenFilesOnly;
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
    String[] columns = {"File", "Ext", "Size", "Path", "Owner"};
    private JDialog dialogBox;

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
        Dimension dimension = new Dimension(400, 30);
        tBxSearchPath.setMinimumSize(dimension);
        tBxSearchPath.setFont(font);
        tBxSearchPath.setPreferredSize(new Dimension(140, 25));
        lbContains = new JLabel("Contains:");
        lbContains.setLocation(300, 250);
        tBxContains = new JFormattedTextField();
        tBxContains.setPreferredSize(new Dimension(120, 25));
        chBxInTitle = new JCheckBox("In title");
        lbInsideFile = new JLabel("Inside file");
        ckBxInsideFile = new JCheckBox("Inside file");
        lbSize = new JLabel("File size is");
        cBxSizeCriteria = new JComboBox(searchCriteria);
        tBxSize = new JFormattedTextField();
        tBxSize.setPreferredSize(new Dimension(70, 25));
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
        chBxHiddenFiles = new JCheckBox("Include hidden files");
        ckBxHiddenFilesOnly = new JCheckBox();
        lbHiddenFilesOnly = new JLabel("Hidden files only");
        mainFrame = new JFrame();
        searchPanel = new JPanel(new GridBagLayout());
        basicSearchPanel = new JPanel(new GridBagLayout());
        advSearchPanel = new JPanel(new GridBagLayout());
        advSearchPanel.setVisible(false);
        gridBagConstraints = new GridBagConstraints();
        resultPanel = new JPanel();
        data = new String[][]{};
        tableModel = new DefaultTableModel(data, columns);
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        dialogBox = new JDialog();
    }

    /**
     * This method sets all objects created to its corresponding panel
     */
    public void setObjectsInWindow() {
        //Setting components in Basic search Panel
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
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
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        advSearchPanel.add(chBxInTitle, gridBagConstraints);
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        advSearchPanel.add(ckBxInsideFile, gridBagConstraints);
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
        //Setting the table in the Result pane
        table.setPreferredScrollableViewportSize(new Dimension(855, 650));
        table.setFillsViewportHeight(true);
        resultPanel.add(scrollPane);
        //Adding basic search and advanced search panels into Search panel
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        basicSearchPanel.setPreferredSize(new Dimension(460, 160));
        searchPanel.add(basicSearchPanel, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        advSearchPanel.setPreferredSize(new Dimension(460, 160));
        searchPanel.add(advSearchPanel, gridBagConstraints);
        searchPanel.setBackground(Color.GRAY);
        basicSearchPanel.setBackground(Color.lightGray);
        advSearchPanel.setBackground(Color.lightGray);
        resultPanel.setBackground(Color.gray);
        //Adding the two main panels in the frame
        mainFrame.add(searchPanel, BorderLayout.WEST);
        mainFrame.add(resultPanel, BorderLayout.CENTER);
    }

    /**
     * This method displays a popup window with a title and a message received as parameters
     * @param title
     * @param message
     */
    public void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(mainFrame, message, title, JOptionPane.WARNING_MESSAGE);

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
     * This method returns a value to indicate whether the string contained in the object tBxContains
     * should be searched in the file titles or not.
     *
     * @return Boolean
     */
    public boolean searchInTitle() {
        return this.chBxInTitle.isSelected();
    }

    /**
     * This method returns a value to indicate whether the string contained in the object tBxContains
     * should be searched in the content of the files  or not.
     *
     * @return Boolean
     */
    public boolean searchInsideFile() {
        return this.ckBxInsideFile.isSelected();
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
     * This method returns the btSearch button
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
                    advSearchPanel.setVisible(true);
                } else {
                    advSearchPanel.setVisible(false);
                    //hideAdvancedSearch();
                }
            }
        });
    }

}



