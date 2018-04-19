/*
 *  Copyright (c) 2018 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information").  You shall not disclose such Confidential Information and shall use
 *   it only in accordance with the terms of the license agreement you entered into with Jalasoft.
 */
package com.jalasoft.search.commons;

import com.jalasoft.search.model.SearchCriteria;

import java.util.Map;

/**
 * This class contains some tools
 *
 * @author rafael alfaro
 * @version 1.0
 */
public class ToolHandler {
    /**
     * Gets a SearchCriteria if the criteria with the name is found in other case return null
     *
     * @param mapSearchCriteria The map with all criteria
     * @param criterionName     Criteria name to search in the Map
     * @return SearchCriteria
     */
    public static SearchCriteria existCriteriaName(Map<Integer, SearchCriteria> mapSearchCriteria, String criterionName) {
        for (Map.Entry<Integer, SearchCriteria> criteria : mapSearchCriteria.entrySet()) {
            if (criteria.getValue().getCriteriaName().equals(criterionName)) {
                return criteria.getValue();
            }
        }
        return null;
    }

    /**
     * Gets a specific criterion from criteria list
     *
     * @param mapSearchCriteria map with the criteria
     * @param key               Position of criterion
     * @return SearchCriteria
     */
    public static SearchCriteria getTheSearchCriterion(Map<Integer, SearchCriteria> mapSearchCriteria, int key) {
        return mapSearchCriteria.get(key);
    }

    /**
     * Gets the index of item in the array
     *
     * @param values
     * @param stString
     * @return
     */
    public static int getArrayIndex(String[] values, String stString) {
        int index = -1;
        for (int i = 0; i < values.length; i++) {
            if (values[i] == null ? stString == null : values[i].equals(stString)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
