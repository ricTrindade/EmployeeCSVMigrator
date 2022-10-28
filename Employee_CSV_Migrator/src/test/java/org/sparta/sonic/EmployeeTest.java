package org.sparta.sonic;

import org.junit.jupiter.api.*;
import org.sparta.sonic.Model.Employee;

import java.sql.Date;

public class EmployeeTest {
    Employee testEmployee = new Employee();
    //These also make use of .getX so two birds with one stone?

    @Test
    @DisplayName("Check that setId sets the ID")
    void checkSetIdSets1(){
        testEmployee.setId(1);
        Assertions.assertEquals(1, testEmployee.getId());
    }
    @Test
    @DisplayName("Check that setNamePrefix sets the namePrefix")
    void checkSetNamePrefixSetsMr(){
        testEmployee.setNamePrefix("Mr");
        Assertions.assertEquals("Mr", testEmployee.getNamePrefix());
    }

    @Test
    @DisplayName("Check that setFirstName sets the firstName")
    void checkSetFirstNameSetsTest(){
        testEmployee.setFirstName("Test");
        Assertions.assertEquals("Test", testEmployee.getFirstName());
    }

    @Test
    @DisplayName("Check that setMiddleInitial sets the middleInitial")
    void checkSetMiddleInitialSetsFALSE(){
        testEmployee.setMiddleInitial("FALSE");
        Assertions.assertEquals("FALSE", testEmployee.getMiddleInitial());
    }

    @Test
    @DisplayName("Check that setLastName sets the lastName")
    void checkSetLastNameSetsPerson(){
        testEmployee.setLastName("Person");
        Assertions.assertEquals("Person", testEmployee.getLastName());
    }

    @Test
    @DisplayName("Check that setGender sets the gender")
    void checkSetGenderSetsM(){
        testEmployee.setGender('M');
        Assertions.assertEquals('M', testEmployee.getGender());
    }

    @Test
    @DisplayName("Check that setEmail sets the email")
    void checkSetEmailSetsEmail(){
        testEmployee.setEmail("test@gmail.com");
        Assertions.assertEquals("test@gmail.com", testEmployee.getEmail());
    }

    @Test
    @DisplayName("Check that setDateOfBirth sets the dateOfBirth")
    void checkSetDateOfBirthSets2000Aug13(){
        testEmployee.setDateOfBirth(Date.valueOf("2000-08-13"));
        Assertions.assertEquals(Date.valueOf("2000-08-13"), testEmployee.getDateOfBirth());
    }

    @Test
    @DisplayName("Check that setDateOfJoining sets the dateOfJoining")
    void checkSetDateOfJoiningSets2022Nov23(){
        testEmployee.setDateOfJoining(Date.valueOf("2022-02-23"));
        Assertions.assertEquals(Date.valueOf("2022-02-23"), testEmployee.getDateOfJoining());
    }
    @Test
    @DisplayName("Check that setSalary sets the salary")
    void checkSetSalarySets19000(){
        testEmployee.setSalary(19000);
        Assertions.assertEquals(19000, testEmployee.getSalary());
    }
    @Test
    @DisplayName("Check that setIsValid sets the isValid boolean to true")
    void checkSetIsValidSetsTrue(){
        testEmployee.setIsValid(true);
        Assertions.assertTrue(testEmployee.getIsValid());
    }

}
