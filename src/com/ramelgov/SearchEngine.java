package com.ramelgov;

import java.util.List;
import java.util.Map;
import java.util.Set;

class SearchEngine {

    private SearchingMethod searchingMethod;

    // it may contain additional fields as well

    public void setMethod(SearchingMethod searchingMethod) {
        this.searchingMethod = searchingMethod;
    }

    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, List<String> fileData, String query) {
        return this.searchingMethod.search(invertedIndex, fileData, query);

    }
}
