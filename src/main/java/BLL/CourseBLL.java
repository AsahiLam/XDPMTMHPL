/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CourseDAL;
import DAL.CourseOnlineDAL;
import DAL.CourseOnsiteDAL;
import DAL.entities.Course;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class CourseBLL {

    CourseDAL courseDAL;
    CourseOnlineDAL courseOnlineDAL;
    CourseOnsiteDAL courseOnsiteDAL;

    public CourseBLL() {
        courseDAL = new CourseDAL();
    }

    public List<Course> getAllCourse() {
        courseOnsiteDAL = new CourseOnsiteDAL();
        courseOnlineDAL = new CourseOnlineDAL();
        List<Course> course = new ArrayList<>();
        course.addAll(courseOnsiteDAL.getListOnSite());
        course.addAll(courseOnlineDAL.getListOnline());
        course.sort((c1, c2) -> Integer.compare(c1.getCourseID(), c2.getCourseID()));
        return course;
    }

    public List<Course> getAllCourse2() {
        return courseDAL.getListCourse();
    }

    public Course getCourseWithID(int courseID) {
        courseDAL = new CourseDAL();
        return courseDAL.getCourseByID(courseID);
    }

    public List<Course> getCoursesWithInfo(String info) {
        courseOnlineDAL = new CourseOnlineDAL();
        List<Course> course = new ArrayList<>();
        course.addAll(courseOnlineDAL.getCoursesWithInfo(info));
        course.sort((c1, c2) -> Integer.compare(c1.getCourseID(), c2.getCourseID()));
        return course;
    }

    public int checkCourseOnline(int CourseID) {
        courseOnsiteDAL = new CourseOnsiteDAL();
        courseOnlineDAL = new CourseOnlineDAL();
        if (courseOnsiteDAL.getCourseOnsiteByID(CourseID) != null) {
            return 1;
        } else if (courseOnlineDAL.getCourseOnlineByID(CourseID) != null) {
            return 0;
        } else {
            return -1;
        }
    }
    
    // Bnt-Add
    public boolean addCourse (String Title, int Credits, int DepartmentID, String Url) {
        courseDAL = new CourseDAL();

        int saveID = courseDAL.addCourse(Title, Credits, DepartmentID);
        
        if (saveID > 0) {
            courseOnlineDAL.addCourseOnline(saveID, Url);
            return true;
        } else {
            return false;
        }
    }
    //
    
    
    public boolean updateCourse (String Title, int Credits, int DepartmentID, int id, String Url) {
        courseDAL = new CourseDAL();

        courseDAL.updateCourse(Title, Credits, DepartmentID, id);
        
        courseOnlineDAL.updateCourseOnline(id, Url);
            return true;
    }
    
    public boolean deleteCourse (int id) {
        courseOnlineDAL.deleteCourseOnline(id);
        
        courseDAL = new CourseDAL();
        courseDAL.deleteCourse(id);
            return true;
    }
    
    
    
    public boolean addCourseOnsite (String Title, int Credits, int DepartmentID, String Location, String Days, String time) {
        courseDAL = new CourseDAL();

        int saveID = courseDAL.addCourse(Title, Credits, DepartmentID);
        
        if (saveID > 0) {
            courseOnlineDAL.addCourseOnsite(saveID, Location, Days, time);
            return true;
        } else {
            return false;
        }
    }
    
    
    public boolean updateCourseOnsite (String Title, int Credits, int DepartmentID, int id, String Location, String Days, String Time) {
        courseDAL = new CourseDAL();

        courseDAL.updateCourse(Title, Credits, DepartmentID, id);
        
        courseOnlineDAL.updateCourseOnsite(id, Location, Days, Time);
            return true;
    }
    
    public boolean deleteCourseOnsite (int id) {
        courseOnlineDAL.deleteCourseOnsite(id);
        
        courseDAL = new CourseDAL();
        courseDAL.deleteCourse(id);
            return true;
    }
}
