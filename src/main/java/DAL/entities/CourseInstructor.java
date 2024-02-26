/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.entities;

/**
 *
 * @author lamquoc
 */
public class CourseInstructor{
    private int CourseID;
    private String Title;
    private Person Instructor;

    public CourseInstructor() {
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public Person getInstructor() {
        return Instructor;
    }

    public void setInstructor(Person Instructor) {
        this.Instructor = Instructor;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public CourseInstructor(int CourseID, String Title, Person Instructor) {
        this.CourseID = CourseID;
        this.Title = Title;
        this.Instructor = Instructor;
    }
    
    

    
   
}
