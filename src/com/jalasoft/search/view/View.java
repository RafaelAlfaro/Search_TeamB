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

//import javax.swing.*;
//import javax.swing.event.ChangeEvent;
//import javax.swing.event.ChangeListener;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//
//public class View extends JFrame {
//
//    private JButton btSearch;
//    private JButton btCancel;
//    private JButton btSelect;
//    private JLabel lbFileName;
//    private JLabel lbAdvancedSearch;
//    private JLabel lbFolder;
//    private JFormattedTextField tBxSearch;
//    private JFormattedTextField tBxSearchPath;
//    private JComboBox cBxAdvancedSearch;
//    private String[] advancedSearch = {"...", "Regular files", "Multimedia", "Other"};
//    private String[] searchCriteria = {"<", ">", "=", "<>"};
//    private String[] measureUnit = {"Bytes", "KB", "MB", "GB"};
//    private Folder folder;
//    private JCheckBox ckBxAdvancedSearch;
//    private JLabel lbContains;
//    private JFormattedTextField tBxContains;
//    private JLabel lbInTitle;
//    private JCheckBox chBxInTitle;
//    private JLabel lbInsideFile;
//    private JCheckBox ckBxInsideFile;
//    private JLabel lbSize;
//    private JComboBox cBxSizeCriteria;
//    private JFormattedTextField tBxSize;
//    private JComboBox cBxMeasureUnit;
//    private JLabel lbOwner;
//    private JFormattedTextField tBxOwner;
//    private JLabel lbDate;
//    private JCheckBox chBxCreated;
//    private JLabel lbCreated;
//    private JCheckBox chBxModified;
//    private JLabel lbModified;
//    private JCheckBox chBxAccessed;
//    private JLabel lbAccessed;
//    private JCheckBox chBxHiddenFiles;
//    private JLabel lbHiddenFiles;
//    private JCheckBox ckBxHiddenFilesOnly;
//    private JLabel lbHiddenFilesOnly;
//
//
//    /**
//     * Constructor of the class View, it sets the window size and the layout
//     *
//     * @param title: is the name that will be set on the window's name
//     */
//    public View(String title) {
//        super(title);
//        pack();
//        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setExtendedState(MAXIMIZED_BOTH);
//        setVisible(true);
//    }
//
//    /**
//     * This method hides from the window all the objects that correspond to the advanced search and it adjusts
//     * the search and cancel buttons ("btSearch" and "btCancel")
//     */
//    public void hideAdvancedSearch() {
//        btSearch.setBounds(70, 140, 80, 20);
//        btCancel.setBounds(170, 140, 80, 20);
//        lbContains.setVisible(false);
//        tBxContains.setVisible(false);
//        chBxInTitle.setVisible(false);
//        lbInTitle.setVisible(false);
//        ckBxInsideFile.setVisible(false);
//        lbInsideFile.setVisible(false);
//        lbSize.setVisible(false);
//        cBxSizeCriteria.setVisible(false);
//        tBxSize.setVisible(false);
//        cBxMeasureUnit.setVisible(false);
//        lbOwner.setVisible(false);
//        tBxOwner.setVisible(false);
//        lbDate.setVisible(false);
//        chBxCreated.setVisible(false);
//        lbCreated.setVisible(false);
//        chBxModified.setVisible(false);
//        lbModified.setVisible(false);
//        chBxAccessed.setVisible(false);
//        lbAccessed.setVisible(false);
//        chBxHiddenFiles.setVisible(false);
//        lbHiddenFiles.setVisible(false);
//        ckBxHiddenFilesOnly.setVisible(false);
//        lbHiddenFilesOnly.setVisible(false);
//    }
//
//    /**
//     * This method displays in the window all the objects that correspond to the advanced search
//     */
//    public void showAdvancedSearch() {
//        btSearch.setBounds(70, 140 + 400, 80, 20);
//        btCancel.setBounds(170, 140 + 400, 80, 20);
//        lbContains.setVisible(true);
//        tBxContains.setVisible(true);
//        chBxInTitle.setVisible(true);
//        lbInTitle.setVisible(true);
//        ckBxInsideFile.setVisible(true);
//        lbInsideFile.setVisible(true);
//        lbSize.setVisible(true);
//        cBxSizeCriteria.setVisible(true);
//        tBxSize.setVisible(true);
//        cBxMeasureUnit.setVisible(true);
//        lbOwner.setVisible(true);
//        tBxOwner.setVisible(true);
//        lbDate.setVisible(true);
//        chBxCreated.setVisible(true);
//        lbCreated.setVisible(true);
//        chBxModified.setVisible(true);
//        lbModified.setVisible(true);
//        chBxAccessed.setVisible(true);
//        lbAccessed.setVisible(true);
//        chBxHiddenFiles.setVisible(true);
//        lbHiddenFiles.setVisible(true);
//        ckBxHiddenFilesOnly.setVisible(true);
//        lbHiddenFilesOnly.setVisible(true);
//    }
//
//    /**
//     * This method creates all the window components that are displayed in the Main View
//     */
//    public void createWindowObjects() {
//        btSearch = new JButton("Search");
//        btCancel = new JButton("Cancel");
//        btSelect = new JButton("Find..");
//        cBxAdvancedSearch = new JComboBox(advancedSearch);
//        ckBxAdvancedSearch = new JCheckBox();
//        lbFileName = new JLabel("File name:");
//        lbAdvancedSearch = new JLabel("Advanced search");
//        lbFolder = new JLabel("Path: ");
//        tBxSearch = new JFormattedTextField();
//        tBxSearchPath = new JFormattedTextField();
//        lbContains = new JLabel("Contains:");
//        lbContains.setLocation(300, 250);
//        tBxContains = new JFormattedTextField();
//        tBxContains.setPreferredSize(new Dimension(120, 25));
//        lbInTitle = new JLabel("In title");
//        chBxInTitle = new JCheckBox();
//        lbInsideFile = new JLabel("Inside file");
//        ckBxInsideFile = new JCheckBox();
//        lbSize = new JLabel("File size");
//        cBxSizeCriteria = new JComboBox(searchCriteria);
//        tBxSize = new JFormattedTextField();
//        tBxSize.setPreferredSize(new Dimension(40, 25));
//        cBxMeasureUnit = new JComboBox(measureUnit);
//        lbOwner = new JLabel("Owner is:");
//        tBxOwner = new JFormattedTextField();
//        tBxOwner.setPreferredSize(new Dimension(120, 25));
//        lbDate = new JLabel("Date");
//        chBxCreated = new JCheckBox();
//        lbCreated = new JLabel("Created");
//        chBxModified = new JCheckBox();
//        lbModified = new JLabel("Modified");
//        chBxAccessed = new JCheckBox();
//        lbAccessed = new JLabel("Accessed");
//        chBxHiddenFiles = new JCheckBox();
//        lbHiddenFiles = new JLabel("Include Hidden files");
//        ckBxHiddenFilesOnly = new JCheckBox();
//        lbHiddenFilesOnly = new JLabel("Hidden files only");
//    }
//
//    /**
//     * This method sets the positions and dimensions of all the components in the window
//     */
//    public void configureObjectSettings() {
//
//        cBxAdvancedSearch.setPreferredSize(new Dimension(140, 25));
//        cBxAdvancedSearch.setSelectedIndex(0);
//        cBxAdvancedSearch.setEnabled(false);
//        cBxAdvancedSearch.setVisible(false);
//        tBxSearch.setPreferredSize(new Dimension(150, 25));
//        tBxSearchPath.setPreferredSize(new Dimension(500, 25));
//
//        //This is the definition in windown for the controls in basic search
//        lbFolder.setBounds(30, 15, 100, 20);
//        add(lbFolder);
//        btSelect.setBounds(65, 18, 15, 15);
//        add(btSelect);
//        tBxSearchPath.setBounds(80, 16, 225, 20);
//        add(tBxSearchPath);
//        lbFileName.setBounds(30, 40, 130, 20);
//        add(lbFileName);
//        tBxSearch.setBounds(100, 40, 100, 20);
//        add(tBxSearch);
//        ckBxAdvancedSearch.setBounds(20, 90, 23, 15);
//        add(ckBxAdvancedSearch);
//        lbAdvancedSearch.setBounds(43, 88, 150, 20);
//        add(lbAdvancedSearch);
//        cBxAdvancedSearch.setBounds(180, 88, 130, 20);
//        add(cBxAdvancedSearch);
//        btSearch.setBounds(70, 140, 80, 20);
//        add(btSearch);
//        btCancel.setBounds(170, 140, 80, 20);
//        add(btCancel);
//
//        //This is the definition in window for the controls in Advanced search (regular files)
//        lbContains.setBounds(30, 170, 100, 20);
//        add(lbContains);
//        tBxContains.setBounds(90, 170, 100, 20);
//        add(tBxContains);
//        chBxInTitle.setBounds(195, 160, 30, 30);
//        add(chBxInTitle);
//        lbInTitle.setBounds(220, 165, 100, 20);
//        add(lbInTitle);
//        ckBxInsideFile.setBounds(195, 175, 30, 30);
//        add(ckBxInsideFile);
//        lbInsideFile.setBounds(220, 180, 100, 20);
//        add(lbInsideFile);
//        lbSize.setBounds(30, 210, 100, 20);
//        add(lbSize);
//        cBxSizeCriteria.setBounds(80, 205, 60, 30);
//        add(cBxSizeCriteria);
//        tBxSize.setBounds(135, 205, 70, 30);
//        add(tBxSize);
//        cBxMeasureUnit.setBounds(200, 205, 90, 30);
//        add(cBxMeasureUnit);
//        lbOwner.setBounds(30, 255, 100, 20);
//        add(lbOwner);
//        tBxOwner.setBounds(90, 255, 100, 20);
//        add(tBxOwner);
//        lbDate.setBounds(30, 290, 100, 20);
//        add(lbDate);
//        chBxCreated.setBounds(30, 320, 30, 30);
//        add(chBxCreated);
//        lbCreated.setBounds(55, 325, 70, 20);
//        add(lbCreated);
//        chBxModified.setBounds(107, 320, 30, 30);
//        add(chBxModified);
//        lbModified.setBounds(132, 325, 70, 20);
//        add(lbModified);
//        chBxAccessed.setBounds(190, 320, 30, 30);
//        add(chBxAccessed);
//        lbAccessed.setBounds(215, 325, 70, 20);
//        add(lbAccessed);
//        chBxHiddenFiles.setBounds(30, 400, 30, 30);
//        add(chBxHiddenFiles);
//        lbHiddenFiles.setBounds(55, 405, 150, 20);
//        add(lbHiddenFiles);
//        ckBxHiddenFilesOnly.setBounds(30, 420, 30, 30);
//        add(ckBxHiddenFilesOnly);
//        lbHiddenFilesOnly.setBounds(55, 425, 150, 20);
//        add(lbHiddenFilesOnly);
//    }
//
//    /**
//     * Returns the btSearch button
//     *
//     * @return JButton
//     */
//    public JButton getBtSearch() {
//        return btSearch;
//    }
//
//    /**
//     * Gets the File Name
//     *
//     * @return String
//     */
//    public String getFileName() {
//        return this.tBxSearch.getText();
//    }
//
//    /**
//     * Gets the searchWithCriteria path
//     *
//     * @return String
//     */
//    public String getSearchPath() {
//        return this.tBxSearchPath.getText();
//    }
//
//
//    /**
//     * This method keeps track of all events that occur with the objects: btSelect, btSearch,
//     * btCancel, ckBxAdvancedSearch, cBxAdvancedSearch
//     */
//    public void enableEventsListening() {
//        btSelect.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                folder = new Folder();
//                String string = folder.getPath().toString();
//                tBxSearchPath.setText(string);
//            }
//        });
//
//        btSearch.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//            }
//        });
//
//        btCancel.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                System.exit(0);
//            }
//        });
//
//        ckBxAdvancedSearch.addChangeListener(new ChangeListener() {
//            @Override
//            public void stateChanged(ChangeEvent e) {
//                if (ckBxAdvancedSearch.isSelected()) {
//                    cBxAdvancedSearch.setEnabled(true);
//                    cBxAdvancedSearch.setVisible(true);
//                } else {
//                    cBxAdvancedSearch.setEnabled(false);
//                    cBxAdvancedSearch.setSelectedIndex(0);
//                    hideAdvancedSearch();
//                }
//            }
//        });
//
//        cBxAdvancedSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (cBxAdvancedSearch.getSelectedItem().toString() == "Regular files") {
//                    showAdvancedSearch();
//                } else {
//                    hideAdvancedSearch();
//                }
//            }
//        });
//    }
//
//
//    /**
//     * This method Initializes the objects in the view
//     */
//    public void initView() {
//        createWindowObjects();
//        configureObjectSettings();
//        hideAdvancedSearch();
//        enableEventsListening();
//    }
//}

import com.jalasoft.search.model.FileSearch;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


import java.io.File;
import java.util.Locale;

public class View extends Application {

    private Stage stage;
    private DatePicker checkInDatePicker;
    private Label lblPath;
    private TextField txtPath;
    private Button btnBrowseButton;
    private Label lblFileName;
    private TextField txtFileName;
    private CheckBox chkAdvancedSearch;
    private Button btnSearch;
    private Button btnCancel;

    private TableView tblResult;
    private ObservableList<FileSearch> filesList;


    public View() {
        super();
        Locale.setDefault(Locale.US);
        filesList = FXCollections.observableArrayList();
    }

    /**
     * Getter method
     *
     * @return: ObservableList\<AssetFile\>
     */
    public ObservableList<FileSearch> getFilesList() {
        return filesList;
    }

    public void mainUI(String[] args) {
        Locale.setDefault(Locale.US);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        stage.setTitle("View");
        //initUI();
        stage.show();
    }

    public void initUI() {
        VBox vbox = new VBox(20);
        vbox.setStyle("-fx-padding: 10;");
        Scene scene = new Scene(vbox, 600, 400);
        this.stage.setScene(scene);

        checkInDatePicker = new DatePicker();

        GridPane gridBasicSearch = new GridPane();
        gridBasicSearch.setHgap(10);
        gridBasicSearch.setVgap(10);
        gridBasicSearch.setAlignment(Pos.TOP_LEFT);
        lblPath = new Label("Path: ");
        gridBasicSearch.add(lblPath, 0, 0);
        GridPane.setHalignment(lblPath, HPos.LEFT);
        txtPath = new TextField();
        gridBasicSearch.add(txtPath, 1, 0);
        btnBrowseButton = new Button("...");
        gridBasicSearch.add(btnBrowseButton, 2, 0);
        lblFileName = new Label("AssetFile name: ");
        gridBasicSearch.add(lblFileName, 0, 1);
        GridPane.setHalignment(lblFileName, HPos.LEFT);
        txtFileName = new TextField();
        gridBasicSearch.add(txtFileName, 1, 1);
        chkAdvancedSearch = new CheckBox("Advanced search");
        gridBasicSearch.add(chkAdvancedSearch, 0, 2);
        btnSearch = new Button("Search");
        gridBasicSearch.add(btnSearch, 0, 3);
        btnCancel = new Button("Cancel");
        gridBasicSearch.add(btnCancel, 1, 3);

        tblResult = new TableView();
        // setup the file column
        TableColumn<FileSearch, String> colFileName = new TableColumn<>("AssetFile name");
        colFileName.setMinWidth(120);
        colFileName.setCellValueFactory(new PropertyValueFactory<>("fileName"));

        // setup the Extension column
        TableColumn<FileSearch, String> colExt = new TableColumn<>("Ext");
        colExt.setMaxWidth(45);
        colExt.setPrefWidth(40);
        colExt.setCellValueFactory(new PropertyValueFactory<>("extension"));

        // setup the Size column
        TableColumn<FileSearch, String> colSize = new TableColumn<>("Size (Kb)");
        colSize.setMinWidth(100);
        colSize.setCellValueFactory(new PropertyValueFactory<>("size"));

        // setup the path column
        TableColumn<FileSearch, String> colPath = new TableColumn<>("Path");
        colPath.setMinWidth(200);
        colPath.setCellValueFactory(new PropertyValueFactory<>("path"));

        // setup the Owner column
        TableColumn<FileSearch, String> colOwner = new TableColumn<>("Owner");
        colOwner.setMinWidth(150);
        colOwner.setCellValueFactory(new PropertyValueFactory<>("owner"));

        //setup the table with all the columns
//        tblResult.setItems(getFiles());
        tblResult.getColumns().addAll(colFileName, colExt, colSize, colPath, colOwner);


        GridPane gridResult = new GridPane();
        GridPane gridAdvancedSearch = new GridPane();
        gridResult.add(tblResult, 0, 0);
        vbox.getChildren().add(gridBasicSearch);
        vbox.getChildren().add(gridResult);
        enableEventsListening();
        //this.btnSearch.setOnAction(e -> this.fillCriteria());
    }

    private void fillCriteria(){
        System.out.printf("xxxxxxxx");
    }
    /**
     * This method keeps track of all events that occur with the objects: btn_Select, btn_Search,
     * btn_Cancel, chkBx_AdvancedSearch, cmbBx_AdvancedSearch
     */
    public void enableEventsListening() {
        btnBrowseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Search path");
                File file = directoryChooser.showDialog(stage);
                if (file != null) {
                    txtPath.setText(file.getAbsolutePath());
                }
            }
        });

    }

    /**
     * Returns the Path
     *
     * @return String
     */
    public String getTxtPath() {
        return txtPath.toString();
    }

    /**
     * Returns the btSearch button
     *
     * @return JButton
     */
    public Button getBtSearch() {
        return btnSearch;
    }

//    /**
//     * @return: returns an observable list of files
//     */
//    public ObservableList<AssetFile> getFiles() {
//        //ObservableList<AssetFile> files = FXCollections.observableArrayList();
//        filesList.add(new AssetFile("code.txt", "c:\\Share", "txt", "250", "Pedro"));
//        filesList.add(new AssetFile("image.gif", "c:\\Share", "gif", "1020", "Pedro"));
//        filesList.add(new AssetFile("comp.zip", "c:\\Program Files\\winzip", "zip", "50", "Raul"));
//        filesList.add(new AssetFile("my doc.csv", "c:\\My Documents", "csv", "740", "Marco Galvez"));
//        filesList.add(new AssetFile("Track003.mp3", "c:\\Multimedia\\myMusic", "mp3", "250", "Sony music"));
//        return filesList;
//    }
}