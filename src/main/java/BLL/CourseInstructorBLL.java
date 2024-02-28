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
    CourseInstructorDAL courseInstructorDAL = new CourseInstructorDAL();
    int numofrecords = 1;
    
    public List<CourseInstructor> getListCourseInstructor(int page){
        List<CourseInstructor> course = courseInstructorDAL.getListCourseInstructor();
        int size = course.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return course.subList(from, Math.min(to,size));
    }
    
    public int getListCourseInstructorCount(){
        return (int) Math.ceil((double) courseInstructorDAL.getListCourseInstructorCount() / numofrecords);
    }
    
    public CourseInstructor getCourseInstructorByID(int CourseID){
        return courseInstructorDAL.getCourseInstructorById(CourseID);
    }
    
    public List<CourseInstructor> getCourseInstructorWithInfo(String info, int page){
        List<CourseInstructor> course = courseInstructorDAL.getCourseInstructorWithInfo(info);
        int size = course.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        return course.subList(from, Math.min(to,size));
    }
    
    public int getCourseInstructorWithInfoCount(String info){
        return (int) Math.ceil((double)courseInstructorDAL.getCourseInstructorWithInfoCount(info)/ numofrecords);
    }
}
