/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CourseInstructorDAL;
import DAL.entities.CourseInstructor;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class CourseInstructorBLL {
    CourseInstructorDAL courseInstructorDAL;
    int numofrecords = 1;
    
    public List<CourseInstructor> getListCourseInstructor(int page){
        courseInstructorDAL = new CourseInstructorDAL();
        return courseInstructorDAL.getListCourseInstructor();
    }
    
    public int getListCourseInstructorCount(){
        return courseInstructorDAL.getListCourseInstructorCount();
    }
    
    public CourseInstructor getCourseInstructorByID(int CourseID){
        return courseInstructorDAL.getCourseInstructorById(CourseID);
    }
        
}
