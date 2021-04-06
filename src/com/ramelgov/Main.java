package com.ramelgov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;


public class Main {
    /**
     * @author ram_elgov
     * @param args
     * <p>A mini search engine program. The user inputs a list of  people with their
     * full names and Emails, and than a query.
     * The application returns all matched people based on the query.</p>
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Print the user menu
        File file = new File(args[1]);
        Scanner scanner1 = new Scanner(file);
        List<String> fileData = new ArrayList<>();
        while (scanner1.hasNextLine()) {
            fileData.add(scanner1.nextLine());
        }
        Map<String, Set<Integer>> invertedIndex = loadInvertedIndex(fileData);
        System.out.println(invertedIndex);
        printUserMenu(file, invertedIndex, fileData);
    }

    /**
     *
     * @return the inverted index.
     */
    public static Map<String, Set<Integer>> loadInvertedIndex (List<String> fileData) {
        Map<String, Set<Integer>> invertedIndex = new HashMap<>();
        List<String> currentLine;

        for (int index = 0; index < fileData.size(); index++) {
            currentLine = Arrays.stream(fileData.get(index).trim().split(" ")).collect(Collectors.toCollection(ArrayList :: new));
            for (String s : currentLine) {
                // initialize the key-value pair if needed
                invertedIndex.computeIfAbsent(s.toLowerCase(Locale.ROOT), k -> new HashSet<>(Set.of()));
                // add the current line index for the current word in the inverted index
                invertedIndex.get(s.toLowerCase(Locale.ROOT)).add(index);


            }
        }
        return invertedIndex;
    }

    /**
     * Function to print the list of the people.
     * @param file is the array of user input.
     */
    public static void printFile(File file) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        while (input.hasNextLine()) {
            System.out.println(input.nextLine());
        }

    }

    /**
     * This is the user menu. It contains three options:
     * 1. Preform a search on the dataset.
     * 2. Print the dataset to the console.
     * 3. Exit the application.
     * @param file is the dataset file.
     */
    public static void printUserMenu(File file, Map<String, Set<Integer>> invertedIndex, List<String> fileData) {
        boolean exit = false;
        while (!exit) {
            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");
            try (Scanner scanner = new Scanner(System.in)) {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("Select a matching strategy: ALL, ANY, NONE");
                        String searchingMethod = scanner.nextLine();
                        System.out.println("Enter a name or email to search all suitable people.");
                        String query = scanner.nextLine();
                        SearchEngine searchEngine = new SearchEngine();
                        switch (searchingMethod) {
                            case "ALL":
                                searchEngine.setMethod(new AllSearchingMethod());
                                break;
                            case "ANY":
                                searchEngine.setMethod(new AnySearchingMethod());
                                break;
                            case "NONE":
                                searchEngine.setMethod(new NoneSearchingMethod());
                                break;
                            default:
                                return;
                        }
                        Set<Integer> searchResult = searchEngine.search(invertedIndex, fileData, query);
                        printSearchResult(searchResult, fileData);

                        break;
                    case 2:
                        printFile(file);
                        break;
                    case 0:
                        System.out.println("\nBye!");
                        exit = true;
                        break;
                }

            } catch (Exception e) {
                System.out.println("Incorrect option! Try again.\n");
            }
        }
    }
    private static void printSearchResult(Set<Integer> searchResult, List<String> fileData) {
        if (searchResult.size() == 0) {
            System.out.println("No matching people found.");
        } else {
            System.out.println(searchResult.size() + " persons found:");
            for (Integer lineIndex : searchResult) {
                System.out.println(fileData.get(lineIndex));
            }
        }
    }
}