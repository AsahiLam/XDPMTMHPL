/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.Connection;
import DAL.entities.StudentGrade;
import DAL.entities.Person;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Tính
 */
public class StudentGradeDAL { 
    
    public StudentGradeDAL(){    
    }
    
    public ArrayList<StudentGrade> getAll(){
        ArrayList<StudentGrade> list = new ArrayList<>();
        try {
            Connection conn = MyDatabaseConnection.connectDB();
            String sql = "SELECT sg.EnrollmentID, sg.CourseID, c.Title, sg.StudentID, p.Firstname, p.Lastname, sg.Grade FROM studentgrade sg\n" +
                            "JOIN person p ON sg.StudentID = p.PersonID\n" +
                            "JOIN course c ON sg.CourseID = c.CourseID\n";
            PreparedStatement pst = conn.prepareCall(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                StudentGrade sg = new StudentGrade();
                sg.setEnrollmentID(rs.getInt("EnrollmentID"));
                sg.setCourseID(rs.getInt("CourseID"));
                sg.setTitle(rs.getString("Title"));
                sg.setStudentID(rs.getInt("StudentID"));
                sg.setFirstName(rs.getString("Firstname"));
                sg.setLastName(rs.getString("Lastname"));
                sg.setGarde(rs.getFloat("Grade"));
                list.add(sg);
            }
            
            conn.close();  
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public void addStudent(int enrollmentID, int studentID, int courseID){
        try {
            Connection conn = MyDatabaseConnection.connectDB();
            String sql = "INSERT INTO studentgrade VALUES (?,?,?,NULL)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, enrollmentID);
            pst.setInt(2, studentID);
            pst.setInt(3, courseID);
            pst.executeUpdate();
            
            conn.close();
        } catch (Exception e) {
        }
    }
    
public void editGrade(int enrollmentID, Float grade) {
    try {
        Connection conn = MyDatabaseConnection.connectDB();
        String sql = "UPDATE studentgrade\n" +
                     "SET Grade = ?\n" +
                     "WHERE EnrollmentID = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setFloat(1, grade);
        pst.setInt(2, enrollmentID);
        pst.executeUpdate();

        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
    
    public void deleteStudent(int enrollmentID){
        try {
            Connection conn = MyDatabaseConnection.connectDB();
            String sql = "DELETE FROM studentgrade WHERE EnrollmentID = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, enrollmentID);
            pst.executeUpdate();
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
