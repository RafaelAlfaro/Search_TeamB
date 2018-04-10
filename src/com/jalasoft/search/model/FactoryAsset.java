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

/**
 * Implements the model Factory Asset that allow create new Assets
 *
 * @author sofia cespedes
 * @version 1.0
 */

abstract class FactoryAsset {

    public Asset createAsset(char flagAsset) {
        if (flagAsset == 'f') {
            return new FileSearch();
        } else if (flagAsset == 'm') {
            return new Multimedia();
        } else {
            return new FolderSearch();
        }
    }
}

