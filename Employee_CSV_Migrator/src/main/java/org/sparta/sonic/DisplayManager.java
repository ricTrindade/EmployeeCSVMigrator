package org.sparta.sonic;

import java.lang.reflect.Field;
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

    public static void printEmployee(Employee employee)
    {
        Field[] fields = employee.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            try {
                field.setAccessible(true);
                Object value = field.get(employee);
                System.out.println(field.getName()+": "+value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
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
