/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.entities;

import DAL.entities.Course;

/**
 *
 * @author lamquoc
 */
public class CourseOnline extends Course {

    private String URL;

    public CourseOnline(String URL, int CourseID, String Title, int Credits, DAL.entities.Department Department) {
        super(CourseID, Title, Credits, Department);
        this.URL = URL;
    }

    public CourseOnline() {
    }

    public CourseOnline(String URL) {
        this.URL = URL;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    @Override
    public String toString() {
        return "CourseOnline{" + "URL=" + URL + '}';
    }

}
