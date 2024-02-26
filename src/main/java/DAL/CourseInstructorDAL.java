/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import BLL.PersonBLL;
import DAL.entities.CourseInstructor;
import DAL.entities.Person;
import java.sql.Connection;
import java.sql.Date;
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
public class CourseInstructorDAL extends MyDatabaseConnection {

    Connection connection = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    public CourseInstructorDAL() {
        connection = CourseInstructorDAL.connectDB();
    }

    public List<CourseInstructor> getListCourseInstructor() {
        List<CourseInstructor> list = new ArrayList<>();

        try {
            String query = "SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID";

            p = CourseInstructorDAL.connectDB().prepareStatement(query);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CourseInstructor ci = new CourseInstructor();
                    ci.setCourseID(rs.getInt("CourseID"));
                    ci.setTitle(rs.getString("Title"));
                    ci.setInstructor(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"), Date.valueOf(rs.getString("HireDate").substring(0, 10)), null));
                    list.add(ci);
                }
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getListCourseInstructorCount() {
        int count = 0;
        try {
            String query = "SELECT COUNT(*) as count FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID";

            p = CourseInstructorDAL.connectDB().prepareStatement(query);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    count = rs.getInt("count");
                }
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public CourseInstructor getCourseInstructorById(int CourseID) {
        CourseInstructor ci = new CourseInstructor();
        try {
            String query = "SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID WHERE c.CourseID = ?";

            p = CourseInstructorDAL.connectDB().prepareStatement(query);

            p.setInt(1, CourseID);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    ci.setCourseID(CourseID);
                    ci.setTitle("Title");
                    ci.setInstructor(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"), Date.valueOf(rs.getString("HireDate").substring(0, 10)), null));
                }
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ci;
    }

}
