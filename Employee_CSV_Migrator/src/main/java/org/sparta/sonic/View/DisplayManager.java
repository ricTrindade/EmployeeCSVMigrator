package org.sparta.sonic.View;

import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.utility.logging.LoggerSingleton;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

//Provide a simple user interface to display the results of reading the file –
// how many unique,
// clean records there are,
// how many duplicates,
// how many records with missing fields - is this necessary because csv doesn't contain missing fields
// possibly display the questionable records - unsure yet

public class DisplayManager {
    private static final Logger logger = LoggerSingleton.getSingleton().getLogger();
    //fields
    ArrayList<Employee> cleanedArrayList;
    ArrayList<Employee> corruptedArrayList;

    //constructor
    public DisplayManager(ArrayList<Employee> cleanedArrayList, ArrayList<Employee> corruptedArrayList) {
        this.cleanedArrayList = cleanedArrayList;
        this.corruptedArrayList = corruptedArrayList;
    }

    public void PrintAll() {
        System.out.println("Amount of unique records: "+getCleanedArraySize());
        System.out.println("Amount of corrupted records: "+getCorruptedArraySize());
        System.out.println("Amount of duplicates: "+ getDuplicateCount());
    }

    public static void printEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            printEmployee(employee);
            System.out.print("\n");
        }
    }

    public static void printEmployee(Employee employee) {
        Field[] fields = employee.getClass().getDeclaredFields();
        for (Field field : fields)
        {
            try {
                field.setAccessible(true);
                Object value = field.get(employee);
                if (!field.getName().equals("isValid")) {
                    System.out.println(field.getName()+": "+value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }


    public int getCleanedArraySize() {
        return cleanedArrayList.size();
    }

    public int getCorruptedArraySize() {
        return corruptedArrayList.size();
    }


    //how many duplicates
    //want to change to private but public for testing?
    public int getDuplicateCount() {
        //sets have to be unique whereas arraylists dont
        //so just compare size to see how many are unique
        Set<Employee> corruptedSet = new HashSet<>(corruptedArrayList);
        return corruptedArrayList.size() - corruptedSet.size();
    }
}
