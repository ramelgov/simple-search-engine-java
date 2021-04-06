package com.ramelgov;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class implements the "NONE" searching strategy using SearchingMethod interface.
 */
class NoneSearchingMethod implements SearchingMethod {
    /**
     * This method prints all the lines that does not contain <b>any</b> of the words in the query.
     *
     * @param invertedIndex is the hash set of the data from the file, loads every run of the application.
     * @param fileData      is an array list of Strings comprise of the lines from the given file.
     * @param query         is the user's query.
     * @return HashSet of all the result indexes
     */
    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, List<String> fileData, String query) {
        Set<Integer> searchResult = new HashSet<>();
        SearchEngine anySearchEngine = new SearchEngine();
        anySearchEngine.setMethod(new AnySearchingMethod());
        Set<Integer> anyResult = anySearchEngine.search(invertedIndex, fileData, query);
        for (int i = 0; i < fileData.size(); i++) {
            if (!anyResult.contains(i)) {
                searchResult.add(i);
            }
        }
        return searchResult;
    }
}
