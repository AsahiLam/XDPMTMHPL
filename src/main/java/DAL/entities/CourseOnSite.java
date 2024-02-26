/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL.entities;

import DAL.entities.Course;
import java.sql.Time;

/**
 *
 * @author lamquoc
 */
public class CourseOnSite extends Course {

    private String Location;
    private String Days;
    private Time Time;

    public CourseOnSite() {
    }

    public CourseOnSite(String Location, String Days, Time Time, int CourseID, String Title, int Credits, Department Department) {
        super(CourseID, Title, Credits, Department);
        this.Location = Location;
        this.Days = Days;
        this.Time = Time;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getDays() {
        return Days;
    }

    public void setDays(String Days) {
        this.Days = Days;
    }

    public Time getTime() {
        return Time;
    }

    public void setTime(Time Time) {
        this.Time = Time;
    }

    @Override
    public String toString() {
        return "CourseOnSite{" + "Location=" + Location + ", Days=" + Days + ", Time=" + Time + '}';
    }

}
