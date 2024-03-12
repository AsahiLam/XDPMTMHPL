/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CourseOnsiteDAL;
import DAL.entities.Course;
import DAL.entities.CourseOnSite;
import java.util.AbstractMap;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class CourseOnSiteBLL {

    int numofrecords = 30;

    CourseOnsiteDAL courseOnsiteDAL;

    public CourseOnSiteBLL() {
        courseOnsiteDAL = new CourseOnsiteDAL();
    }

    public List<Course> getAllOnsiteCourse(int page) {

        List<Course> course = courseOnsiteDAL.getListOnSite();
        int size = course.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return course.subList(from, Math.min(to, size));
    }

    public int getAllOnsiteCourseTotalPage() {
        
        return (int) Math.ceil((double) courseOnsiteDAL.getListOnSiteCount() / numofrecords);
    }
    
    public List<Course> getCourseWithInfo(String info, int page) {
        List<Course> course = courseOnsiteDAL.getCoursesWithInfo(info);
        int size = course.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;
        
        return course.subList(from, Math.min(to,size));
    }

    public int getCourseWithInfoTotalPage(String info) {
        
        return (int) Math.ceil((double) courseOnsiteDAL.getCoursesWithInfoCount(info) / numofrecords);
    }

    public CourseOnSite getCourseOnsiteByID(int courseID) {
        CourseOnSite course = courseOnsiteDAL.getCourseOnsiteByID(courseID);
        return course;
    }
    
    public List<AbstractMap.SimpleEntry<String,Integer>> statistic_OnlineCourse_StudentCount(){
        return courseOnsiteDAL.getStatistic_Amount_Student();             
    }
    
    public List<AbstractMap.SimpleEntry<String,Integer>> statistic_OnlineCourse_InstructorCount(){
        return courseOnsiteDAL.getStatistic_Amount_Instructor();             
    }
}
