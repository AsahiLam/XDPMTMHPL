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
import java.util.AbstractMap.SimpleEntry;
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
    }

    public List<Course> getListOnline() {
        List<Course> course = new ArrayList<>();

        try {
            connection = MyDatabaseConnection.connectDB();
            String query = "SELECT c.CourseID, c.Title, c.Credits, c.DepartmentID,'Online' AS CourseType, s.url\n"
                    + "FROM Course c\n"
                    + "JOIN OnlineCourse s ON c.CourseID = s.CourseID;";

            p = connection.prepareStatement(query);

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
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public int getListOnlineCount() {
        int count = 0;

        try {
            connection = MyDatabaseConnection.connectDB();

            String query = "SELECT COUNT(*) AS count\n"
                    + "FROM Course c\n"
                    + "JOIN OnlineCourse s ON c.CourseID = s.CourseID;";

            p = connection.prepareStatement(query);

            rs = p.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public List<Course> getCoursesWithInfo(String info) {
        List<Course> course = new ArrayList<>();

        try {
            connection = MyDatabaseConnection.connectDB();

            String query = "SELECT 'Online' AS CourseType, c.*, d.*, s.* FROM Course c JOIN OnlineCourse s ON c.CourseID = s.CourseID JOIN Department d ON d.DepartmentID = c.DepartmentID WHERE CAST(c.CourseID AS VARCHAR(10)) LIKE ? or c.Title LIKE ? or d.Name LIKE ?";
            p.clearParameters();
            p = connection.prepareStatement(query);

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

            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public int getCoursesWithInfoCount(String info) {
        int count = 0;

        try {
            connection = MyDatabaseConnection.connectDB();
            String query = "SELECT COUNT(*) AS count FROM Course c JOIN OnlineCourse s ON c.CourseID = s.CourseID JOIN Department d ON d.DepartmentID = c.DepartmentID WHERE CAST(c.CourseID AS VARCHAR(10)) LIKE ? or c.Title LIKE ? or d.Name LIKE ?";
            p.clearParameters();
            p = connection.prepareStatement(query);

            p.setString(1, "%" + info + "%");
            p.setString(2, "%" + info + "%");
            p.setString(3, "%" + info + "%");

            rs = p.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public CourseOnline getCourseOnlineByID(int CourseID) {
        CourseOnline course = new CourseOnline();

        try {
            connection = MyDatabaseConnection.connectDB();
            String query = "SELECT c.CourseID, c.Title, c.Credits, c.DepartmentID,'Online' AS CourseType, s.url\n"
                    + "FROM Course c\n"
                    + "JOIN OnlineCourse s ON c.CourseID = s.CourseID Where c.CourseID = ?";

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
                    course.setURL(rs.getString("url"));
                }
            } else {
                return null;
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return course;
    }

    public List<SimpleEntry<String, Integer>> getStatistic_Amount_Student() {
        List list = new ArrayList();
        try {
            connection = MyDatabaseConnection.connectDB();

            String query = """
                           SELECT 
                               C.Title,
                               COUNT(g.StudentID) AS StudentCount
                           FROM 
                               OnlineCourse CO
                           JOIN 
                               Course C ON CO.CourseID = C.CourseID
                           LEFT JOIN 
                               StudentGrade g ON g.CourseID = C.CourseID
                           GROUP BY 
                               CO.CourseID, C.Title;""";

            p = connection.prepareStatement(query);

            rs = p.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new SimpleEntry(rs.getString("Title"), rs.getInt("StudentCount")));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<SimpleEntry<String, Integer>> getStatistic_Amount_Instructor() {
        List list = new ArrayList();
        try {
            connection = MyDatabaseConnection.connectDB();

            String query = """
                           SELECT 
                               C.Title,
                               COUNT(CI.PersonID) AS InstructorCount
                           FROM 
                               OnlineCourse CO
                           JOIN 
                               Course C ON CO.CourseID = C.CourseID
                           LEFT JOIN 
                               CourseInstructor CI ON CO.CourseID = CI.CourseID
                           GROUP BY 
                               CO.CourseID, C.Title;""";

            p = connection.prepareStatement(query);

            rs = p.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    list.add(new SimpleEntry(rs.getString("Title"), rs.getInt("InstructorCount")));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }
    
    // Bnt-Add
    public int addCourseOnline (int ID, String url) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            String query = "INSERT INTO onlinecourse (CourseID, url) VALUES (?, ?);";
            p = connection.prepareStatement(query);
        
            p.setInt(1, ID);
            p.setString(2, url);
            
            ans = p.executeUpdate();
            
            connection.close();
            
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }
    //
    
    // Update
    public int updateCourseOnline (int ID, String url) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "UPDATE onlinecourse SET url = ? WHERE CourseID = ?;";
            p = connection.prepareStatement(query);
        
            p.setString(1, url);
            p.setInt(2, ID);
            
            ans = p.executeUpdate();
            
            connection.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }
    
    
    public int updateCourseOnsite (int ID, String Location, String Days, String Time) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "UPDATE onsitecourse SET Location = ?, Days = ?, Time = ? WHERE CourseID = ?;";
            p = connection.prepareStatement(query);
        
            p.setString(1, Location);
            p.setString(2, Days);
            p.setString(3, Time);
            p.setInt(4, ID);
            
            ans = p.executeUpdate();
            
            connection.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }
    
    // delete
        public int deleteCourseOnline (int ID) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "DELETE FROM onlinecourse " +
                    "WHERE CourseID = ?;";
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
        
        public int deleteCourseOnsite (int ID) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "DELETE FROM onsitecourse " +
                    "WHERE CourseID = ?;";
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
        
    public int addCourseOnsite (int ID, String Location, String Days, String Time) {
        int ans = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            String query = "INSERT INTO onsitecourse (CourseID, Location, Days, Time) VALUES (?, ?, ?, ?);";
            p = connection.prepareStatement(query);
        
            p.setInt(1, ID);
            p.setString(2, Location);
            p.setString(3, Days);
            p.setString(4, Time);
            ans = p.executeUpdate();
            
            connection.close();
            return ans;
        } catch (Exception e) {
            e.printStackTrace();
            return ans;
        }
    }
}
