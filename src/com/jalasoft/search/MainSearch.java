/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */

package com.jalasoft.search;

import com.jalasoft.search.commons.LogHandle;
import com.jalasoft.search.controller.Controller;
import com.jalasoft.search.model.Search;
import com.jalasoft.search.view.View;

import javax.swing.*;

/**
 * This Class is the main program
 *
 * @author rafael alfaro
 * @version 1.0
 */

public class MainSearch {

    public static void main(String[] args) {
        LogHandle.getInstance().WriteLog(LogHandle.INFO, "Starting application .....");
        Search search = new Search();
        View view = new View("Searcher");
        view.initView();
        view.setLocationRelativeTo(null);
        view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        view.setSize(1100, 700);
        view.setVisible(true);
        Controller controller = new Controller(search, view);
    }
}
