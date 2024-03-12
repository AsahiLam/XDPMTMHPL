/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CourseOnlineDAL;
import DAL.entities.Course;
import DAL.entities.CourseOnline;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class CourseOnlineBLL {

    int numofrecords = 30;

    CourseOnlineDAL courseOnlineDAL;

    public CourseOnlineBLL() {
        courseOnlineDAL = new CourseOnlineDAL();
    }

    public List<Course> getAllOnlineCourse(int page) {
        List<Course> course = courseOnlineDAL.getListOnline();
        int size = course.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return course.subList(from, Math.min(to, size));
    }

    public int getAllOnlineCourseTotalPage() {

        return (int) Math.ceil((double) courseOnlineDAL.getListOnlineCount() / numofrecords);
    }

    public List<Course> getCourseWithInfo(String info, int page) {
        List<Course> course = courseOnlineDAL.getCoursesWithInfo(info);
        int size = course.size();
        int from, to;
        from = (page - 1) * numofrecords;
        to = page * numofrecords;

        return course.subList(from, Math.min(to, size));
    }

    public int getCourseWithInfoTotalPage(String info) {
        return (int) Math.ceil((double) courseOnlineDAL.getCoursesWithInfoCount(info) / numofrecords);
    }

    public CourseOnline getCourseOnlineByID(int CourseID) {
        CourseOnline course = courseOnlineDAL.getCourseOnlineByID(CourseID);
        return course;
    }

    public List<SimpleEntry<String, Integer>> statistic_OnlineCourse_StudentCount() {
        return courseOnlineDAL.getStatistic_Amount_Student();
    }

    public List<SimpleEntry<String, Integer>> statistic_OnlineCourse_InstructorCount() {
        return courseOnlineDAL.getStatistic_Amount_Instructor();
    }

}
