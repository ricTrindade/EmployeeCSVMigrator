package org.sparta.sonic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sparta.sonic.Model.Employee;
import org.sparta.sonic.View.DisplayManager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class DisplayManagerTester {


    @Test
    @DisplayName("Check that duplicateArray returns 2")
    void checkThatDuplicateArrayCountReturns0()
    {
        Employee testEmployee1 = new Employee();
        testEmployee1.setId(0);
        testEmployee1.setNamePrefix("Mr");
        testEmployee1.setFirstName("Test");
        testEmployee1.setMiddleInitial("FALSE");
        testEmployee1.setLastName("Person");
        testEmployee1.setGender('M');
        testEmployee1.setEmail("test@gmail.com");
        testEmployee1.setDateOfBirth(new Date(2000, Calendar.AUGUST, 13));
        testEmployee1.setDateOfJoining(new Date(2022, Calendar.NOVEMBER, 23));
        testEmployee1.setSalary(100);

        Employee testEmployee2 = new Employee();
        testEmployee1.setId(1);
        testEmployee1.setNamePrefix("Mr");
        testEmployee1.setFirstName("Tests");
        testEmployee1.setMiddleInitial("G");
        testEmployee1.setLastName("Man");
        testEmployee1.setGender('M');
        testEmployee1.setEmail("tests@gmail.com");
        testEmployee1.setDateOfBirth(new Date(1999, Calendar.JUNE, 20));
        testEmployee1.setDateOfJoining(new Date(2022, Calendar.JANUARY, 4));
        testEmployee1.setSalary(21312);

        ArrayList<Employee> duplicateArray = new ArrayList<>();

        duplicateArray.add(testEmployee1);
        duplicateArray.add(testEmployee2);
        duplicateArray.add(testEmployee2);
        duplicateArray.add(testEmployee2);

        System.out.println("Size of ArrayList: "+duplicateArray.size());
        Set<Employee> removedDuplicateSet = new HashSet<>(duplicateArray);
        System.out.println("Size of "+removedDuplicateSet.size());


        DisplayManager displayManager = new DisplayManager(duplicateArray, duplicateArray);
        Assertions.assertEquals(2, displayManager.getDuplicateCount());
    }

    //need the filtering to be done for this ideally
    @Test
    @DisplayName("Check that cleanedArraySize returns 5")
    void checkThatCleanedArraySizeReturns5()
    {
        Employee testEmployee1 = new Employee();
        testEmployee1.setId(0);
        testEmployee1.setNamePrefix("Mr");
        testEmployee1.setFirstName("Test");
        testEmployee1.setMiddleInitial("FALSE");
        testEmployee1.setLastName("Person");
        testEmployee1.setGender('M');
        testEmployee1.setEmail("test@gmail.com");
        testEmployee1.setDateOfBirth(new Date(2000, Calendar.AUGUST, 13));
        testEmployee1.setDateOfJoining(new Date(2022, Calendar.NOVEMBER, 23));
        testEmployee1.setSalary(100);

        Employee testEmployee2 = new Employee();
        testEmployee1.setId(1);
        testEmployee1.setNamePrefix("Mr");
        testEmployee1.setFirstName("Tests");
        testEmployee1.setMiddleInitial("G");
        testEmployee1.setLastName("Man");
        testEmployee1.setGender('M');
        testEmployee1.setEmail("tests@gmail.com");
        testEmployee1.setDateOfBirth(new Date(1999, Calendar.JUNE, 20));
        testEmployee1.setDateOfJoining(new Date(2022, Calendar.JANUARY, 4));
        testEmployee1.setSalary(21312);

        ArrayList<Employee> testArray = new ArrayList<>();

        testArray.add(testEmployee1);
        testArray.add(testEmployee2);
        testArray.add(testEmployee2);
        testArray.add(testEmployee2);
        testArray.add(testEmployee2);

        DisplayManager displayManager = new DisplayManager(testArray,testArray);

        Assertions.assertEquals(5, displayManager.getCleanedArraySize());
    }

    //need the filtering to be done for this ideally
    @Test
    @DisplayName("Check that corruptedArraySize returns 2")
    void checkThatCleanedArraySizeReturns2()
    {
        Employee testEmployee1 = new Employee();
        testEmployee1.setId(0);
        testEmployee1.setNamePrefix("Mr");
        testEmployee1.setFirstName("Test");
        testEmployee1.setMiddleInitial("FALSE");
        testEmployee1.setLastName("Person");
        testEmployee1.setGender('M');
        testEmployee1.setEmail("test@gmail.com");
        testEmployee1.setDateOfBirth(new Date(2000, Calendar.AUGUST, 13));
        testEmployee1.setDateOfJoining(new Date(2022, Calendar.NOVEMBER, 23));
        testEmployee1.setSalary(100);

        Employee testEmployee2 = new Employee();
        testEmployee1.setId(1);
        testEmployee1.setNamePrefix("Mr");
        testEmployee1.setFirstName("Tests");
        testEmployee1.setMiddleInitial("G");
        testEmployee1.setLastName("Man");
        testEmployee1.setGender('M');
        testEmployee1.setEmail("tests@gmail.com");
        testEmployee1.setDateOfBirth(new Date(1999, Calendar.JUNE, 20));
        testEmployee1.setDateOfJoining(new Date(2022, Calendar.JANUARY, 4));
        testEmployee1.setSalary(21312);

        ArrayList<Employee> testArray = new ArrayList<>();

        testArray.add(testEmployee1);
        testArray.add(testEmployee2);

        DisplayManager displayManager = new DisplayManager(testArray,testArray);

        Assertions.assertEquals(2, displayManager.getCorruptedArraySize());
    }

    @Test
    @DisplayName("Check that empty array returns 0 - Duplicate")
    void checkThatEmptyArrayReturns0Duplicate()
    {
        ArrayList<Employee> emptyArray = new ArrayList<>();
        DisplayManager displayManager = new DisplayManager(emptyArray,emptyArray);
        Assertions.assertEquals(0, displayManager.getDuplicateCount());
    }

    @Test
    @DisplayName("Check that empty array returns 0 - Cleaned")
    void checkThatEmptyArrayReturns0Cleaned()
    {
        ArrayList<Employee> emptyArray = new ArrayList<>();
        DisplayManager displayManager = new DisplayManager(emptyArray,emptyArray);
        Assertions.assertEquals(0, displayManager.getCleanedArraySize());
    }

    @Test
    @DisplayName("Check that empty array returns 0 - Corrupted")
    void checkThatEmptyArrayReturns0Corrupted()
    {
        ArrayList<Employee> emptyArray = new ArrayList<>();
        DisplayManager displayManager = new DisplayManager(emptyArray,emptyArray);
        Assertions.assertEquals(0, displayManager.getCorruptedArraySize());
    }

}
