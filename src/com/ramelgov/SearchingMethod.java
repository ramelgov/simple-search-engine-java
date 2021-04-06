package com.ramelgov;

import java.util.List;
import java.util.Map;
import java.util.Set;

interface SearchingMethod {

    Set<Integer> search(Map<String, Set<Integer>> invertedIndex, List<String> fileData, String query);
}
