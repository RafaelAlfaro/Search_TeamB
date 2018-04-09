/*
 * Copyright (c) 2018 Jalasoft.
 *
 * This software is the confidential and proprietary information of Jalasoft.
 * ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.jalasoft.search.model;

import com.jalasoft.search.view.Folder;

/**
 * Implements the model class File and the getter and setter´s methods
 *
 * @author sofia cespedes
 * @version 1.0
 */
public class FolderSearch extends Asset {
    private int cantFiles;
    public FolderSearch()
    {
        cantFiles = 0;
    }
}
