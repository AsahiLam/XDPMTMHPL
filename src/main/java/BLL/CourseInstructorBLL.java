/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.CourseInstructorDAL;
import DAL.entities.CourseInstructor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class CourseInstructorBLL {
    CourseInstructorDAL courseInstructorDAL = new CourseInstructorDAL();
    int numofrecords = 30;
    
    public List<CourseInstructor> getAllCourseInstructor(){
        return courseInstructorDAL.getListCourseInstructor();
    }
    
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
    
    public CourseInstructor getCourseInstructorByIDs(int CourseID, int PersonID){
        return courseInstructorDAL.getCourseInstructorByIds(CourseID,PersonID);
    }
    
    public boolean addCourseInstructor(CourseInstructor ci) throws SQLException{
        
        return courseInstructorDAL.addCourseInstructor(ci);
    }
   public boolean updateCourseInstructor(CourseInstructor ci, int oldPersonID, String oldCourseTitle) throws SQLException {
    CourseInstructorDAL courseInstructorDAL = new CourseInstructorDAL(); // Đảm bảo bạn đã khởi tạo đối tượng courseInstructorDAL

    int courseID = courseInstructorDAL.findCourseID(ci.getTitle());
    int oldCourseID = courseInstructorDAL.findCourseID(oldCourseTitle);

    List<CourseInstructor> courseInstructors = courseInstructorDAL.getListCourseInstructors();
 

    for (int i = 0; i < courseInstructors.size(); i++) {
        if (oldPersonID == courseInstructors.get(i).getInstructor().getPersonID() && oldCourseID == courseInstructors.get(i).getCourseID()) {
            courseInstructors.set(i, ci);
            break;
        }
    }

    return courseInstructorDAL.updateCourseInstructor(ci, oldPersonID, oldCourseTitle, courseID, oldCourseID);
}
   public boolean deleteCourseInstructor(int idPerson, String courseTitle) throws SQLException {
    CourseInstructorDAL courseInstructorDAL = new CourseInstructorDAL();
    int courseID = courseInstructorDAL.findCourseID(courseTitle);
    List<CourseInstructor> courseInstructors = courseInstructorDAL.getListCourseInstructors();

    // Tìm và xóa đối tượng trong danh sách
    CourseInstructor foundCourseInstructor = null;
    for (CourseInstructor ci : courseInstructors) {
        if (ci.getInstructor().getPersonID() == idPerson && ci.getCourseID() == courseID) {
            foundCourseInstructor = ci;
            break;
        }
    }

    if (foundCourseInstructor != null) {
        courseInstructors.remove(foundCourseInstructor);
    }

    // Gọi hàm xóa từ DAL
    return courseInstructorDAL.delete(idPerson, courseTitle);
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
