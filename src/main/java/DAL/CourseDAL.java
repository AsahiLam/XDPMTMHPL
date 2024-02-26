/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import BLL.CourseOnSiteBLL;
import BLL.CourseOnlineBLL;
import BLL.DepartmentBLL;
import DAL.entities.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        connection = MyDatabaseConnection.connectDB();
    }

    public Course getCourseByID(int CourseID) {
        Course course = new Course();

        String query = "SELECT * FROM Course c JOIN Department d ON c.DepartmentID and d.DepartmentID WHERE c.CourseID = ?";

        try {
            p = CourseDAL.connectDB().prepareStatement(query);

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
            CourseDAL.connectDB().close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return course;
    }

}
