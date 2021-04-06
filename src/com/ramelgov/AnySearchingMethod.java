package com.ramelgov;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements the "ANY" searching strategy using SearchingMethod interface.
 */
class AnySearchingMethod implements SearchingMethod {
    /**
     * This method prints all the lines that contain at list one of the words in the query.
     *
     * @param invertedIndex is the hash set of the data from the file, loads every run of the application.
     * @param fileData      is an array list of Strings comprise of the lines from the given file.
     * @param query         is the user's query.
     * @return HashSet of all the result indexes
     */

    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, List<String> fileData, String query) {
        List<String> queryWords = Arrays.stream(query.trim().split(" ")).collect(Collectors.toCollection(ArrayList::new));
        Set<Integer> searchResult = new HashSet<>();
        for (String queryWord : queryWords) {
            Set<Integer> currentQueryValue = invertedIndex.getOrDefault(queryWord.toLowerCase(Locale.ROOT), new HashSet<>(Set.of()));
            searchResult.addAll(new HashSet<>(currentQueryValue));
        }
        return searchResult;
    }
}
