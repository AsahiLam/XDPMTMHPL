/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.PersonDAL;
import DAL.entities.Person;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class PersonBLL {
    PersonDAL personDAL = new PersonDAL();
    
    public Person getPersonById(int PersonID){
        return personDAL.getPersonByID(PersonID);
    }
    
    public List<Person> getPersonNotInstructorOfCourse(int CourseID){
        return personDAL.getPersonNotInstructorOfCourse(CourseID);
    }
    
}
