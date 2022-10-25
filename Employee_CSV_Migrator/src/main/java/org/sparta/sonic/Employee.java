package org.sparta.sonic;

import java.util.Date;

public class Employee {

    // Fields
    private int    id;
    private String namePrefix;
    private String firstName;
    private char   middleInitial;
    private String lastName;
    private char   gender;
    private String email;
    private Date   dateOfBirth;
    private Date   dateOfJoining;
    private int    salary;

    // Constructors
    public Employee() {}

    // Getters
    public int    getId()            {return id;}
    public String getNamePrefix()    {return namePrefix;}
    public String getFirstName()     {return firstName;}
    public char   getMiddleInitial() {return middleInitial;}
    public String getLastName()      {return lastName;}
    public char   getGender()        {return gender;}
    public String getEmail()         {return email;}
    public Date   getDateOfBirth()   {return dateOfBirth;}
    public Date   getDateOfJoining() {return dateOfJoining;}
    public int    getSalary()        {return salary;}

    // Setters
    public void setId            (int id)             {this.id = id;}
    public void setNamePrefix    (String namePrefix)  {this.namePrefix = namePrefix;}
    public void setFirstName     (String firstName)   {this.firstName = firstName;}
    public void setMiddleInitial (char middleInitial) {this.middleInitial = middleInitial;}
    public void setLastName      (String lastName)    {this.lastName = lastName;}
    public void setGender        (char gender)        {this.gender = gender;}
    public void setEmail         (String email)       {this.email = email;}
    public void setDateOfBirth   (Date dateOfBirth)   {this.dateOfBirth = dateOfBirth;}
    public void setDateOfJoining (Date dateOfJoining) {this.dateOfJoining = dateOfJoining;}
    public void setSalary        (int salary)         {this.salary = salary;}
}
