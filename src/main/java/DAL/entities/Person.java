/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.entities;

import java.sql.Date;

/**
 *
 * @author lamquoc
 */
public class Person {
    private int PersonID;
    private String Lastname;
    private String Firstname;
    private Date HireDate;
    private Date EnrollmentDate;

    public Person(int PersonID, String Lastname, String Firstname, Date HireDate, Date EnrollmentDate) {
        this.PersonID = PersonID;
        this.Lastname = Lastname;
        this.Firstname = Firstname;
        this.HireDate = HireDate;
        this.EnrollmentDate = EnrollmentDate;
    }

    public Person() {
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String Lastname) {
        this.Lastname = Lastname;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String Firstname) {
        this.Firstname = Firstname;
    }

    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date HireDate) {
        this.HireDate = HireDate;
    }

    public Date getEnrollmentDate() {
        return EnrollmentDate;
    }

    public void setEnrollmentDate(Date EnrollmentDate) {
        this.EnrollmentDate = EnrollmentDate;
    }
    
    
}