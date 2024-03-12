/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import BLL.DepartmentBLL;
import DAL.entities.Course;
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
public class CourseDAL extends MyDatabaseConnection {

    Connection connection = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    public CourseDAL() {

    }

    public Course getCourseByID(int CourseID) {
        Course course = new Course();

        String query = "SELECT * FROM Course c JOIN Department d ON c.DepartmentID and d.DepartmentID WHERE c.CourseID = ?";

        try {
            connection = MyDatabaseConnection.connectDB();

            p = connection.prepareStatement(query);

            p.setInt(1, CourseID);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    course.setCourseID(CourseID);
                    course.setTitle(rs.getString("Title"));
                    course.setCredits(rs.getInt("Credits"));
                    DepartmentBLL departmentBLL = new DepartmentBLL();
                    course.setDepartment(departmentBLL.getDepartment(rs.getInt("DepartmentID")));
                }
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return course;
    }

    public List<Course> getListCourse() {
        List<Course> list = new ArrayList<>();
        try {
            connection = MyDatabaseConnection.connectDB();

            String query = "SELECT * FROM Course";

            p = connection.prepareStatement(query);

            rs = p.executeQuery();

            while (rs.next()) {
                DepartmentBLL departmentBLL = new DepartmentBLL();
                list.add(new Course(rs.getInt("CourseID"), rs.getString("Title"), rs.getInt("Credits"), departmentBLL.getDepartment(rs.getInt("DepartmentID"))));
            }

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    // Btn-Add 
    public int addCourse (String Title, int Credits, int DepartmentID) {
        int ans = 0;
        String query = "INSERT INTO Course (Title, Credits, DepartmentID) VALUES (?, ?, ?);";
        
        try {
            connection = MyDatabaseConnection.connectDB();
            
            p = connection.prepareStatement(query);

            p.setString(1, Title);
            p.setInt(2, Credits);
            p.setInt(3, DepartmentID);
            
            ans = p.executeUpdate();
            
            connection.close();
            return selectMaxCourseID();
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }
    
    public int selectMaxCourseID () {
        int maxID = 0;
        
        String query = "SELECT MAX(CourseID) FROM Course;";
        
        try {
            connection = MyDatabaseConnection.connectDB();
            
            p = connection.prepareStatement(query);
            
            rs = p.executeQuery();
            
            if (rs.next()) {
                maxID = rs.getInt("MAX(CourseID)");
            }
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return maxID;
    }
    //
    
    
    public int updateCourse (String Title, int Credits, int DepartmentID, int CourseID) {
        int ans = 0;
        String query = "UPDATE course SET Title = ?, Credits = ?, DepartmentID = ? WHERE CourseID = ?";
        
        try {
            connection = MyDatabaseConnection.connectDB();
            
            p = connection.prepareStatement(query);

            p.setString(1, Title);
            p.setInt(2, Credits);
            p.setInt(3, DepartmentID);
            p.setInt(4, CourseID);
            ans = p.executeUpdate();
            
            connection.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }
    
    
        public int deleteCourse (int ID) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "DELETE FROM course WHERE CourseID = ?;";
            p = connection.prepareStatement(query);

            p.setInt(1, ID);
            ans = p.executeUpdate();
            
            connection.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }

}
