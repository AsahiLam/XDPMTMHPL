/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.StudentGradeDAL;
import DAL.entities.StudentGrade;
import java.util.ArrayList;

/**
 *
 * @author Tính
 */
public class StudentGradeBLL {
    private StudentGradeDAL sgDAL;
    
    public StudentGradeBLL(){
        sgDAL = new StudentGradeDAL();
    }
    
    public ArrayList<StudentGrade> getSGs(){
        ArrayList<StudentGrade> list = new ArrayList<>();
        list = sgDAL.getAll();
        
        return list;
    }
    
    public void addStudentGrade(int enrollmentID, int studentID, int courseID){
        sgDAL.addStudent(enrollmentID, studentID, courseID);
    }
    
    public void editStudenGrade(int enrollmentID, float grade){
        sgDAL.editGrade(enrollmentID, grade);
    }
    
    public void deleteStudentGrade(int enrollmentID){
        sgDAL.deleteStudent(enrollmentID);
    }
    
    public ArrayList<StudentGrade> selectedCourse(String title){
        return sgDAL.selectCourse(title);
    }
}
