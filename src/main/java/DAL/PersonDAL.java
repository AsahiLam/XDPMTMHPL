package DAL;


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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author lamquoc
 */
public class PersonDAL extends MyDatabaseConnection {

    Connection connection = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    public PersonDAL() {
       
    }
    
    
    public Person getPersonByID(int PersonID) {
        Person person = new Person();

        try {
            
            connection = MyDatabaseConnection.connectDB();
            
            String query = "SELECT * FROM Person Where PersonID = ?";
            
            p = connection.prepareStatement(query);
            
            p.setInt(1, PersonID);
            
            rs = p.executeQuery();
            
                while(rs.next()){
                    person.setPersonID(PersonID);
                    person.setFirstname(rs.getString("Firstname"));
                    person.setLastname(rs.getString("Lastname"));
                    person.setEnrollmentDate(rs.getDate("EnrollmentDate"));
                    person.setHireDate(rs.getDate("HireDate"));
                }
            
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PersonDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return person;
    }
    
    public List<Person> getPersonNotInstructorOfCourse(int CourseID){
        List<Person> list = new ArrayList<>();
        
        try{
            connection = MyDatabaseConnection.connectDB();
            
            String query = """
                           SELECT *
                           FROM Person a
                           WHERE a.PersonID NOT IN (
                               SELECT p.PersonID
                               FROM Person p
                               JOIN courseInstructor ci ON p.PersonID = ci.PersonID
                               WHERE ci.CourseID = ?
                           ) AND a.HireDate IS NOT NULL;""";
            
            p = connection.prepareStatement(query);
            
            p.setInt(1, CourseID);
            
            rs = p.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    Person person = new Person();
                    person.setPersonID(rs.getInt("PersonID"));
                    person.setLastname(rs.getString("Lastname"));
                    person.setFirstname(rs.getString("Firstname"));
                    person.setEnrollmentDate(null);
                    person.setHireDate(Date.valueOf(rs.getString("HireDate").substring(0,10)));
                    list.add(person);
                }
            }else{
                return null;
            }
            connection.close();
        }catch (SQLException ex) {
                Logger.getLogger(PersonDAL.class.getName()).log(Level.SEVERE, null, ex);
            }
            return list;
    }
}
