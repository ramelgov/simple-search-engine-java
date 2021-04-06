package com.ramelgov;

import java.util.*;
import java.util.stream.Collectors;

/**
 * This class implements the "ALL" searching strategy using SearchingMethod interface.
 */
class AllSearchingMethod implements SearchingMethod {
    /**
     * This method prints all the lines that contain all of the words in the query.
     *
     * @param invertedIndex is the hash set of the data from the file, loads every run of the application.
     * @param fileData      is an array list of Strings comprise of the lines from the given file.
     * @param query         is the user's query.
     * @return HashSet of all the result indexes
     */
    @Override
    public Set<Integer> search(Map<String, Set<Integer>> invertedIndex, List<String> fileData, String query) {
        Set<String> queryWordsSet = Arrays.stream(query.trim().split(" ")).collect(Collectors.toSet());
        Set<String> tempLine;
        Set<Integer> searchResult = new HashSet<>();
        for (int i = 0; i < fileData.size(); i++) {
            tempLine = Arrays.stream(fileData.get(i).trim().split(" ")).collect(Collectors.toSet());
            if (tempLine.containsAll(queryWordsSet)) {
                searchResult.add(i);
            }
        }
        return searchResult;
    }
}
