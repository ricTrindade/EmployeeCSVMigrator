package org.sparta.sonic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

//Provide a simple user interface to display the results of reading the file –
// how many unique,
// clean records there are,
// how many duplicates,
// how many records with missing fields - is this necessary because csv doesn't contain missing fields
// possibly display the questionable records - unsure yet

public class DisplayManager {
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
        System.out.println("Amount of unique records: "+getCleanedArraySize());
        System.out.println("Amount of corrupted records: "+getCorruptedArraySize());
        System.out.println("Amount of duplicates: "+ getDuplicateCount());
    }

    public int getCleanedArraySize()
    {
        return cleanedArrayList.size();
    }

    public int getCorruptedArraySize()
    {
        return corruptedArrayList.size();
    }


    //how many duplicates
    //want to change to private but public for testing?
    public int getDuplicateCount()
    {
        //sets have to be unique whereas arraylists dont
        //so just compare size to see how many are unique
        Set<Employee> corruptedSet = new HashSet<>(corruptedArrayList);
        return corruptedArrayList.size() - corruptedSet.size();
    }
}
