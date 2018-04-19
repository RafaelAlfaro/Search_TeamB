/*
 * Copyright (c) 2018 Jalasoft.
 * This software is confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.view;

import javax.swing.JFileChooser;
import java.awt.Component;
import java.io.File;

/**
 * This class is used to handle operations related to files
 *
 * @author ronald castellon
 * @version 1.0
 */
public class Folder extends Component {
    public JFileChooser folderSelector;
    public File file;
    public int btnSelected;

    /**
     * Constructor of the class, it displays the JFileChooser component to navigate within the directories
     */
    public Folder() {
        folderSelector = new JFileChooser();
        folderSelector.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        File workingDirectory = new File(System.getProperty("user.dir"));
        folderSelector.setCurrentDirectory(workingDirectory);
        folderSelector.setVisible(true);
        btnSelected = folderSelector.showDialog(this, "Select Folder");
    }

    /**
     * This method retrieves the path selected by the component JFileChooser
     *
     * @return :  the absolute path contained in the object folderSelector
     */
    public String getPath() {
        String absolutePath;
        file = folderSelector.getSelectedFile();
        if (file != null) {
            absolutePath = file.getAbsolutePath();
        } else {
            absolutePath = "";
        }
        return absolutePath;

    }
}
