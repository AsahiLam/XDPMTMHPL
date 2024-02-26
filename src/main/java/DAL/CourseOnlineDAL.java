/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import BLL.DepartmentBLL;
import DAL.entities.Course;
import DAL.entities.CourseOnline;
import DAL.entities.Department;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamquoc
 */
public class CourseOnlineDAL extends MyDatabaseConnection {

    Connection connection = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    public CourseOnlineDAL() {
        connection = MyDatabaseConnection.connectDB();
    }

    public List<Course> getListOnline() {
        List<Course> course = new ArrayList<>();

        try {
            String query = "SELECT c.CourseID, c.Title, c.Credits, c.DepartmentID,'Online' AS CourseType, s.url\n"
                    + "FROM Course c\n"
                    + "JOIN OnlineCourse s ON c.CourseID = s.CourseID;";

            p = CourseOnlineDAL.connectDB().prepareStatement(query);

            rs = p.executeQuery();

            while (rs.next()) {
                DepartmentDAL departmentDAL = new DepartmentDAL();
                int CourseID = rs.getInt("CourseID");
                String Title = rs.getString("Title");
                int Credits = rs.getInt("Credits");
                Department Department = departmentDAL.getDepartMent(rs.getInt("DepartmentID"));
                String url = rs.getString("url");

                CourseOnline courseOnline = new CourseOnline(url, CourseID, Title, Credits, Department);

                course.add(courseOnline);
            }
            CourseOnlineDAL.connectDB().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public int getListOnlineCount() {
        int count = 0;

        try {
            String query = "SELECT COUNT(*) AS count\n"
                    + "FROM Course c\n"
                    + "JOIN OnlineCourse s ON c.CourseID = s.CourseID;";

            p = CourseOnlineDAL.connectDB().prepareStatement(query);

            rs = p.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
            CourseOnlineDAL.connectDB().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public List<Course> getCoursesWithInfo(String info) {
        List<Course> course = new ArrayList<>();

        try {

            String query = "SELECT 'Online' AS CourseType, c.*, d.*, s.* FROM Course c JOIN OnlineCourse s ON c.CourseID = s.CourseID JOIN Department d ON d.DepartmentID = c.DepartmentID WHERE CAST(c.CourseID AS VARCHAR(10)) LIKE ? or c.Title LIKE ? or d.Name LIKE ?";
            p.clearParameters();
            p = CourseOnlineDAL.connectDB().prepareStatement(query);

            p.setString(1, "%" + info + "%");
            p.setString(2, "%" + info + "%");
            p.setString(3, "%" + info + "%");

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    DepartmentBLL departmentBLL = new DepartmentBLL();
                    int CourseID = rs.getInt("CourseID");
                    String Title = rs.getString("Title");
                    int Credits = rs.getInt("Credits");
                    Department Department = departmentBLL.getDepartment(rs.getInt("DepartmentID"));
                    String url = rs.getString("url");

                    CourseOnline courseOnline = new CourseOnline(url, CourseID, Title, Credits, Department);

                    course.add(courseOnline);
                }
            } else {
                return null;
            }
            CourseOnlineDAL.connectDB().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public int getCoursesWithInfoCount(String info) {
        int count = 0;

        try {

            String query = "SELECT COUNT(*) AS count FROM Course c JOIN OnlineCourse s ON c.CourseID = s.CourseID JOIN Department d ON d.DepartmentID = c.DepartmentID WHERE CAST(c.CourseID AS VARCHAR(10)) LIKE ? or c.Title LIKE ? or d.Name LIKE ?";
            p.clearParameters();
            p = CourseOnlineDAL.connectDB().prepareStatement(query);

            p.setString(1, "%" + info + "%");
            p.setString(2, "%" + info + "%");
            p.setString(3, "%" + info + "%");

            rs = p.executeQuery();

            while(rs.next()){
                count = rs.getInt("count");
            } 
            CourseOnlineDAL.connectDB().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public CourseOnline getCourseOnlineByID(int CourseID) {
        CourseOnline course = new CourseOnline();

        try {
            String query = "SELECT c.CourseID, c.Title, c.Credits, c.DepartmentID,'Online' AS CourseType, s.url\n"
                    + "FROM Course c\n"
                    + "JOIN OnlineCourse s ON c.CourseID = s.CourseID Where c.CourseID = ?";

            p = CourseOnlineDAL.connectDB().prepareStatement(query);

            p.setInt(1, CourseID);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    course.setCourseID(CourseID);
                    course.setTitle(rs.getString("Title"));
                    course.setCredits(rs.getInt("Credits"));
                    DepartmentBLL departmentBLL = new DepartmentBLL();
                    course.setDepartment(departmentBLL.getDepartment(rs.getInt("DepartmentID")));
                    course.setURL(rs.getString("url"));
                }
            } else {
                return null;
            }
            CourseOnlineDAL.connectDB().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

}
