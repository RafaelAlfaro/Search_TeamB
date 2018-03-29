/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search.controller;

import com.jalasoft.search.model.Search;
import com.jalasoft.search.view.Window;

/**
 * This class is the main class of controller
 * @author rafael alfaro
 * @version 1.0
 */

public class Controller {
    private Window view;
    private Search search;

    public Controller(Window view, Search search){
        this.view = view;
        this.search = search;
        this.view.getSearchButton().addActionListener(e->fillCriteria());
    }

    private void fillCriteria(){
        String fn;
        fn = this.view.getFileName()
    }
}
