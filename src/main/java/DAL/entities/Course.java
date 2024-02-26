/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.entities;

/**
 *
 * @author lamquoc
 */
public class Course {

    private int CourseID;
    private String Title;
    private int Credits;
    private Department Department;
    
    public Course() {
    }

    public Course(int CourseID, String Title, int Credits, Department Department) {
        this.CourseID = CourseID;
        this.Title = Title;
        this.Credits = Credits;
        this.Department = Department;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int Credits) {
        this.Credits = Credits;
    }

    public Department getDepartment() {
        return Department;
    }

    public void setDepartment(Department Department) {
        this.Department = Department;
    }

    @Override
    public String toString() {
        return "Course{" + "CourseID=" + CourseID + ", Title=" + Title + ", Credits=" + Credits + ", Department=" + Department + '}';
    }

}
