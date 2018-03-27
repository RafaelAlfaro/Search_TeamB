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
 *   @author: ronald castellon
 *   @version 1.0
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Window extends JFrame {

    private JButton btn_Search;
    private JButton btn_Cancel;
    private JButton btn_Select;
    private JLabel lbl_FileName;
    private JLabel lbl_AdvancedSearch;
    private JLabel lbl_Folder;
    private JFormattedTextField txtBx_Search;
    private JFormattedTextField txtBx_SearchPath;
    private JComboBox cmbBx_AdvancedSearch;
    private String[] advancedSearch = {"...", "Regular files", "Multimedia", "Other"};
    private String[] searchCriteria = {"<", ">", "=", "<>"};
    private String[] measureUnit = {"Bytes", "KB", "MB", "GB"};
    private Folder folder;
    private JCheckBox chkBx_AdvancedSearch;
    private JLabel lbl_Contains;
    private JFormattedTextField txtBx_Contains;
    private JLabel lbl_InTitle;
    private JCheckBox chkBx_InTitle;
    private JLabel lbl_InsideFile;
    private JCheckBox chkBx_InsideFile;
    private JLabel lbl_Size;
    private JComboBox cmbBx_SizeCriteria;
    private JFormattedTextField txtBx_Size;
    private JComboBox cmbBx_MeasureUnit;
    private JLabel lbl_Owner;
    private JFormattedTextField txtBx_Owner;
    private JLabel lbl_Date;
    private JCheckBox chkBx_Created;
    private JLabel lbl_Created;
    private JCheckBox chkBx_Modified;
    private JLabel lbl_Modified;
    private JCheckBox chkBx_Accessed;
    private JLabel lbl_Accessed;
    private JCheckBox chkBx_HiddenFiles;
    private JLabel lbl_HiddenFiles;
    private JCheckBox chkBx_HiddenFilesOnly;
    private JLabel lbl_HiddenFilesOnly;


    /**
     * Constructor of the class Window, it sets the window size and the layout
     * 
     * @param title: is the name that will be set on the window's name
     */
    public Window(String title) {
        super(title);
        setResizable(false);
        setSize(390, 180);
        setLayout(null);
    }

    /**
     *  This method hides from the window all the objects that correspond to the advanced search and it adjusts
     *  the search and cancel buttons ("btn_Search" and "btn_Cancel")
     */
    public void hideAdvancedSearch() {
        btn_Search.setBounds(70, 140, 80, 20);
        btn_Cancel.setBounds(170, 140, 80, 20);
        lbl_Contains.setVisible(false);
        txtBx_Contains.setVisible(false);
        chkBx_InTitle.setVisible(false);
        lbl_InTitle.setVisible(false);
        chkBx_InsideFile.setVisible(false);
        lbl_InsideFile.setVisible(false);
        lbl_Size.setVisible(false);
        cmbBx_SizeCriteria.setVisible(false);
        txtBx_Size.setVisible(false);
        cmbBx_MeasureUnit.setVisible(false);
        lbl_Owner.setVisible(false);
        txtBx_Owner.setVisible(false);
        lbl_Date.setVisible(false);
        chkBx_Created.setVisible(false);
        lbl_Created.setVisible(false);
        chkBx_Modified.setVisible(false);
        lbl_Modified.setVisible(false);
        chkBx_Accessed.setVisible(false);
        lbl_Accessed.setVisible(false);
        chkBx_HiddenFiles.setVisible(false);
        lbl_HiddenFiles.setVisible(false);
        chkBx_HiddenFilesOnly.setVisible(false);
        lbl_HiddenFilesOnly.setVisible(false);
    }

    /**
     *  This method displays in the window all the objects that correspond to the advanced search
     */
    public void showAdvancedSearch() {
        btn_Search.setBounds(70, 140 + 400, 80, 20);
        btn_Cancel.setBounds(170, 140 + 400, 80, 20);
        lbl_Contains.setVisible(true);
        txtBx_Contains.setVisible(true);
        chkBx_InTitle.setVisible(true);
        lbl_InTitle.setVisible(true);
        chkBx_InsideFile.setVisible(true);
        lbl_InsideFile.setVisible(true);
        lbl_Size.setVisible(true);
        cmbBx_SizeCriteria.setVisible(true);
        txtBx_Size.setVisible(true);
        cmbBx_MeasureUnit.setVisible(true);
        lbl_Owner.setVisible(true);
        txtBx_Owner.setVisible(true);
        lbl_Date.setVisible(true);
        chkBx_Created.setVisible(true);
        lbl_Created.setVisible(true);
        chkBx_Modified.setVisible(true);
        lbl_Modified.setVisible(true);
        chkBx_Accessed.setVisible(true);
        lbl_Accessed.setVisible(true);
        chkBx_HiddenFiles.setVisible(true);
        lbl_HiddenFiles.setVisible(true);
        chkBx_HiddenFilesOnly.setVisible(true);
        lbl_HiddenFilesOnly.setVisible(true);
    }

    /**
     * This method creates all the window components that are displayed in the Main Window
     */
    public void createWindowObjects() {
        btn_Search = new JButton("Search");
        btn_Cancel = new JButton("Cancel");
        btn_Select = new JButton("Find..");
        cmbBx_AdvancedSearch = new JComboBox(advancedSearch);
        chkBx_AdvancedSearch = new JCheckBox();
        lbl_FileName = new JLabel("File name:");
        lbl_AdvancedSearch = new JLabel("Advanced search");
        lbl_Folder = new JLabel("Path: ");
        txtBx_Search = new JFormattedTextField();
        txtBx_SearchPath = new JFormattedTextField();
        lbl_Contains = new JLabel("Contains:");
        lbl_Contains.setLocation(300, 250);
        txtBx_Contains = new JFormattedTextField();
        txtBx_Contains.setPreferredSize(new Dimension(120, 25));
        lbl_InTitle = new JLabel("In title");
        chkBx_InTitle = new JCheckBox();
        lbl_InsideFile = new JLabel("Inside file");
        chkBx_InsideFile = new JCheckBox();
        lbl_Size = new JLabel("File size");
        cmbBx_SizeCriteria = new JComboBox(searchCriteria);
        txtBx_Size = new JFormattedTextField();
        txtBx_Size.setPreferredSize(new Dimension(40, 25));
        cmbBx_MeasureUnit = new JComboBox(measureUnit);
        lbl_Owner = new JLabel("Owner is:");
        txtBx_Owner = new JFormattedTextField();
        txtBx_Owner.setPreferredSize(new Dimension(120, 25));
        lbl_Date = new JLabel("Date");
        chkBx_Created = new JCheckBox();
        lbl_Created = new JLabel("Created");
        chkBx_Modified = new JCheckBox();
        lbl_Modified = new JLabel("Modified");
        chkBx_Accessed = new JCheckBox();
        lbl_Accessed = new JLabel("Accessed");
        chkBx_HiddenFiles = new JCheckBox();
        lbl_HiddenFiles = new JLabel("Include Hidden files");
        chkBx_HiddenFilesOnly = new JCheckBox();
        lbl_HiddenFilesOnly = new JLabel("Hidden files only");
    }

    /**
     * This method sets the positions and dimensions of all the components in the window
     */
    public void configureObjectSettings() {

        cmbBx_AdvancedSearch.setPreferredSize(new Dimension(140, 25));
        cmbBx_AdvancedSearch.setSelectedIndex(0);
        cmbBx_AdvancedSearch.setEnabled(false);
        cmbBx_AdvancedSearch.setVisible(false);
        txtBx_Search.setPreferredSize(new Dimension(150, 25));
        txtBx_SearchPath.setPreferredSize(new Dimension(500, 25));

        //This is the definition in windown for the controls in basic search
        lbl_Folder.setBounds(30, 15, 100, 20);
        add(lbl_Folder);
        btn_Select.setBounds(65, 18, 15, 15);
        add(btn_Select);
        txtBx_SearchPath.setBounds(80, 16, 225, 20);
        add(txtBx_SearchPath);
        lbl_FileName.setBounds(30, 40, 130, 20);
        add(lbl_FileName);
        txtBx_Search.setBounds(100, 40, 100, 20);
        add(txtBx_Search);
        chkBx_AdvancedSearch.setBounds(20, 90, 23, 15);
        add(chkBx_AdvancedSearch);
        lbl_AdvancedSearch.setBounds(43, 88, 150, 20);
        add(lbl_AdvancedSearch);
        cmbBx_AdvancedSearch.setBounds(180, 88, 130, 20);
        add(cmbBx_AdvancedSearch);
        btn_Search.setBounds(70, 140, 80, 20);
        add(btn_Search);
        btn_Cancel.setBounds(170, 140, 80, 20);
        add(btn_Cancel);

        //This is the definition in window for the controls in Advanced search (regular files)
        lbl_Contains.setBounds(30, 170, 100, 20);
        add(lbl_Contains);
        txtBx_Contains.setBounds(90, 170, 100, 20);
        add(txtBx_Contains);
        chkBx_InTitle.setBounds(195, 160, 30, 30);
        add(chkBx_InTitle);
        lbl_InTitle.setBounds(220, 165, 100, 20);
        add(lbl_InTitle);
        chkBx_InsideFile.setBounds(195, 175, 30, 30);
        add(chkBx_InsideFile);
        lbl_InsideFile.setBounds(220, 180, 100, 20);
        add(lbl_InsideFile);
        lbl_Size.setBounds(30, 210, 100, 20);
        add(lbl_Size);
        cmbBx_SizeCriteria.setBounds(80, 205, 60, 30);
        add(cmbBx_SizeCriteria);
        txtBx_Size.setBounds(135, 205, 70, 30);
        add(txtBx_Size);
        cmbBx_MeasureUnit.setBounds(200, 205, 90, 30);
        add(cmbBx_MeasureUnit);
        lbl_Owner.setBounds(30, 255, 100, 20);
        add(lbl_Owner);
        txtBx_Owner.setBounds(90, 255, 100, 20);
        add(txtBx_Owner);
        lbl_Date.setBounds(30, 290, 100, 20);
        add(lbl_Date);
        chkBx_Created.setBounds(30, 320, 30, 30);
        add(chkBx_Created);
        lbl_Created.setBounds(55, 325, 70, 20);
        add(lbl_Created);
        chkBx_Modified.setBounds(107, 320, 30, 30);
        add(chkBx_Modified);
        lbl_Modified.setBounds(132, 325, 70, 20);
        add(lbl_Modified);
        chkBx_Accessed.setBounds(190, 320, 30, 30);
        add(chkBx_Accessed);
        lbl_Accessed.setBounds(215, 325, 70, 20);
        add(lbl_Accessed);
        chkBx_HiddenFiles.setBounds(30, 400, 30, 30);
        add(chkBx_HiddenFiles);
        lbl_HiddenFiles.setBounds(55, 405, 150, 20);
        add(lbl_HiddenFiles);
        chkBx_HiddenFilesOnly.setBounds(30, 420, 30, 30);
        add(chkBx_HiddenFilesOnly);
        lbl_HiddenFilesOnly.setBounds(55, 425, 150, 20);
        add(lbl_HiddenFilesOnly);
    }

    /**
     *  This method keeps track of all events that occur with the objects: btn_Select, btn_Search,
     *  btn_Cancel, chkBx_AdvancedSearch, cmbBx_AdvancedSearch
     */
    public void enableEventsListening() {
        btn_Select.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                folder = new Folder();
                String string = folder.getPath().toString();
                txtBx_SearchPath.setText(string);
            }
        });

        btn_Search.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }
        });

        btn_Cancel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });

        chkBx_AdvancedSearch.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (chkBx_AdvancedSearch.isSelected()) {
                    cmbBx_AdvancedSearch.setEnabled(true);
                    cmbBx_AdvancedSearch.setVisible(true);
                } else {
                    cmbBx_AdvancedSearch.setEnabled(false);
                    cmbBx_AdvancedSearch.setSelectedIndex(0);
                    hideAdvancedSearch();
                }
            }
        });

        cmbBx_AdvancedSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbBx_AdvancedSearch.getSelectedItem().toString() == "Regular files") {
                    showAdvancedSearch();
                } else {
                    hideAdvancedSearch();
                }
            }
        });
    }

    /**
     *  This method Initializes the objects in the view
     */
    public void initView() {
        createWindowObjects();
        configureObjectSettings();
        hideAdvancedSearch();
        enableEventsListening();
    }
}
