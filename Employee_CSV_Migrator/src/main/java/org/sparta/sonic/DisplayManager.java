package org.sparta.sonic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DisplayManager {

    //Provide a simple user interface to display the results of reading the file –
    // how many unique,
    // clean records there are,
    // how many duplicates,
    // how many records with missing fields,
    // possibly display the questionable records.

    //fields
    ArrayList<Employee> cleanedArrayList;
    ArrayList<Employee> corruptedArrayList;

    //constructor
    public DisplayManager(ArrayList<Employee> cleanedArrayList, ArrayList<Employee> corruptedArrayList) {
        this.cleanedArrayList = cleanedArrayList;
        this.corruptedArrayList = corruptedArrayList;
    }

    public void PrintAll()
    {
        System.out.println("Amount of unique records: "+cleanedArrayList.size());
        System.out.println("Amount of corrupted records: "+corruptedArrayList.size());
        printDuplicates();
    }


    //how many duplicates
    private void printDuplicates()
    {
        //sets have to be unique whereas arraylists dont
        //so just compare size to see how many are uniqeue
        Set<Employee> corruptedSet = new HashSet<>(corruptedArrayList);
        int duplicateCounter = corruptedArrayList.size() - corruptedSet.size();
        System.out.println("Amount of duplicates: "+ duplicateCounter);
    }

    //how many records with missing fields
    //is this necessary? - because .csv doesn't contain missing fields


    //possibly display the questionable records


}
