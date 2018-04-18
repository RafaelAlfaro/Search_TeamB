/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */
package com.jalasoft.search.commons;

import com.google.gson.Gson;
import com.jalasoft.search.model.SearchCriteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * This class handles queries to database
 *
 * @author rafael alfaro
 * @version 1.0
 */
public class SearchQuery {
    private static Connection connection;

    /**
     * Get a new instance to the database
     *
     * @throws SQLException           SQL Exception
     * @throws ClassNotFoundException Class Not Found Exception
     */
    public SearchQuery() throws SQLException, ClassNotFoundException {
        connection = DBConnection.getInstance().getConnection();
    }

    /**
     * Adds a new criteria
     *
     * @param criteriaJSON criteria JSON
     * @throws SQLException
     */
    public void addCriterial(String criteriaJSON) throws SQLException {
        String query = "Insert into criteria values (?,?)";
        PreparedStatement pre = connection.prepareStatement(query);
        pre.setString(2, criteriaJSON);
        pre.execute();
    }

    /**
     * Gets all Criteria
     *
     * @return ResultSet
     * @throws SQLException SQL Exception
     */
    public ResultSet getAllCriteria() throws SQLException {
        Statement state = connection.createStatement();
        ResultSet result = state.executeQuery("SELECT id,criteria FROM criteria");
        return result;
    }

    /**
     * Gets all criteria in a Map
     *
     * @return Map
     * @throws SQLException           SQL Exception
     * @throws ClassNotFoundException Class Not Found Exception
     */
    public Map<Integer, SearchCriteria> getAllData() throws SQLException, ClassNotFoundException {
        Map<Integer, SearchCriteria> scMap = new HashMap<>();
        SearchQuery dbh = new SearchQuery();
        ResultSet set = dbh.getAllCriteria();
        Gson gson = new Gson();
        while (set.next()) {
            SearchCriteria criforjson = gson.fromJson(set.getString("criteria"), SearchCriteria.class);
            int id = set.getInt("id");
            scMap.put(id, criforjson);
        }
        return scMap;
    }
}
