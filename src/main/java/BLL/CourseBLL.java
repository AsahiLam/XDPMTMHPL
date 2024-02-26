/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CourseDAL;
import DAL.CourseOnlineDAL;
import DAL.CourseOnsiteDAL;
import DAL.entities.Course;
import java.sql.SQLException;
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

    public List<Course> getCoursesWithInfo(String info){
        courseOnlineDAL = new CourseOnlineDAL();
        List<Course> course = new ArrayList<>();
        course.addAll(courseOnlineDAL.getCoursesWithInfo(info));
        course.sort((c1, c2) -> Integer.compare(c1.getCourseID(), c2.getCourseID()));

        return course;
    }

    public int checkCourseOnline(int CourseID){
        courseOnsiteDAL = new CourseOnsiteDAL();
        courseOnlineDAL = new CourseOnlineDAL();
        if(courseOnsiteDAL.getCourseOnsiteByID(CourseID) != null){
            return 1;
        }
        else if(courseOnlineDAL.getCourseOnlineByID(CourseID) != null){
            return 0;
        }
        else{
            return -1;
        }
    }
}
