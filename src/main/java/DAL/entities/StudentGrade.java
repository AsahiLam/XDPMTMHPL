/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.entities;

/**
 *
 * @author Tính
 */
public class StudentGrade {
    private int enrollmentID;
    private int CourseID;
    private String title;
    private int StudentID;
    private String firstName;
    private String lastName;
    private float garde;

    public StudentGrade() {
    }

    public StudentGrade(int enrollmentID, int CourseID, String title, int StudentID, String firstName, String lastName, float garde) {
        this.enrollmentID = enrollmentID;
        this.CourseID = CourseID;
        this.title = title;
        this.StudentID = StudentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.garde = garde;
    }

    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public float getGarde() {
        return garde;
    }

    public void setGarde(float garde) {
        this.garde = garde;
    }

    
}
