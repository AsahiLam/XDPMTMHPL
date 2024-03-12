
package DAL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

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
public class CourseInstructorDAL extends MyDatabaseConnection{

    Connection connection = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    public CourseInstructorDAL() {
        
    }

    public List<CourseInstructor> getListCourseInstructor() {
        List<CourseInstructor> list = new ArrayList<>();

        try {
            
            connection = MyDatabaseConnection.connectDB();
            
            String query = "SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID";

            p = connection.prepareStatement(query);

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
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//
    public int getListCourseInstructorCount() {
        int count = 0;
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "SELECT COUNT(*) as count FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID";

            p = connection.prepareStatement(query);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {

                    count = rs.getInt("count");
                }
            } else {
                return 0;
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
//
    public CourseInstructor getCourseInstructorById(int CourseID) {
        CourseInstructor ci = new CourseInstructor();
        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID WHERE c.CourseID = ?";

            p = connection.prepareStatement(query);

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
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ci;
    }
//    
    public CourseInstructor getCourseInstructorByIds(int CourseID, int PersonID){
        CourseInstructor ci = new CourseInstructor();
        
         try {
             connection = MyDatabaseConnection.connectDB();
             
            String query = "SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID WHERE c.CourseID = ? AND p.PersonID = ?";

            p = connection.prepareStatement(query);

            p.setInt(1, CourseID);
            p.setInt(2, PersonID);
            
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
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ci;
    }
//    
//   
    private int getCourseIDFromDatabase(String courseTitle) throws SQLException {
    int courseID = -1; // Giá trị mặc định nếu không tìm thấy

    try (Connection connection = MyDatabaseConnection.connectDB();
         PreparedStatement preparedStatement = connection.prepareStatement("SELECT CourseID FROM course WHERE Title = ?")) {

        preparedStatement.setString(1, courseTitle);
        try (ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                courseID = resultSet.getInt("CourseID");
            }
        }
        connection.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return courseID;
}

   public List<CourseInstructor> getListCourseInstructors() {
    List<CourseInstructor> courseInstructors = new ArrayList<>();

    try {
        connection = MyDatabaseConnection.connectDB();
        
        String query = "SELECT c.CourseID, c.Title, i.PersonID, p.Lastname, p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID";

        p = connection.prepareStatement(query);

        rs = p.executeQuery();

        while (rs.next()) {
            CourseInstructor ci = new CourseInstructor();
            ci.setCourseID(rs.getInt("CourseID"));
            ci.setTitle(rs.getString("Title"));
            ci.setInstructor(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Firstname"), Date.valueOf(rs.getString("HireDate").substring(0, 10)), null));
            courseInstructors.add(ci);
        }
        connection.close();
    } catch (SQLException ex) {
        Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
    }

    return courseInstructors;
}
//
    public boolean addCourseInstructor(CourseInstructor ct){
    Connection connection = null;
    PreparedStatement p = null;

    try {
        // Lấy giá trị personID từ đối tượng CourseInstructor
        int personID = ct.getInstructor().getPersonID();

        // Lấy giá trị courseID từ cơ sở dữ liệu (đã có sẵn)
        int courseID = getCourseIDFromDatabase(ct.getTitle());

        String sql = "INSERT INTO courseinstructor (CourseID, PersonID) VALUES (?, ?)";
        connection = MyDatabaseConnection.connectDB();
        p = connection.prepareStatement(sql);

        p.setInt(1, courseID);
        p.setInt(2, personID);

        int rowsAffected = p.executeUpdate();

        return rowsAffected > 0;
    } catch (SQLException ex) {
        // Xử lý lỗi nếu cần thiết
        ex.printStackTrace();
        return false;
    } finally {
        
        try {
            if (p != null) {
                p.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public int findCourseID(String title)  {
    String sql = "SELECT courseinstructor.CourseID,PersonID FROM courseinstructor, course "
            + "WHERE course.Title = ? AND courseinstructor.CourseID = course.CourseID";

    try (Connection connection = MyDatabaseConnection.connectDB();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setString(1, title);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            connection.close();
            return resultSet.getInt("CourseID");
            
        } else {
            connection.close();
            System.out.println("Can't find the course");
            return 0;
        }
    }catch (SQLException ex) {
        // Xử lý lỗi nếu cần thiết
        ex.printStackTrace();
        return -1;
    }
}
    public boolean updateCourseInstructor(CourseInstructor ci, int oldPersonID, String oldCourseTitle,int courseID, int oldCourseID) {
    String sql = "UPDATE courseinstructor SET PersonID = ?, CourseID = ? WHERE PersonID = ? AND CourseID = ?";

    try (Connection connection = MyDatabaseConnection.connectDB();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        int personID = ci.getInstructor().getPersonID();
         courseID = getCourseIDFromDatabase(ci.getTitle());

        preparedStatement.setInt(1, personID);
        preparedStatement.setInt(2, courseID);
        preparedStatement.setInt(3, oldPersonID);
        preparedStatement.setInt(4, oldCourseID);

        int rowsAffected = preparedStatement.executeUpdate();

        connection.close();
        
        return rowsAffected > 0;
    }catch (SQLException ex) {
        // Xử lý lỗi nếu cần thiết
        ex.printStackTrace();
        return false;
    }
}
   public boolean delete(int idPerson, String Title) {
    int courseID = findCourseID(Title);
    String sql = "DELETE FROM courseinstructor WHERE CourseID = ? AND PersonID = ?";

    try (Connection connection = MyDatabaseConnection.connectDB();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

        preparedStatement.setInt(1, courseID);
        preparedStatement.setInt(2, idPerson);

        int rowsAffected = preparedStatement.executeUpdate();

        connection.close();
        return rowsAffected > 0;
    }catch (SQLException ex) {
        // Xử lý lỗi nếu cần thiết
        ex.printStackTrace();
        return false;
    }
}
    public List<CourseInstructor> getCourseInstructorWithInfo(String info) {
        List<CourseInstructor> list = new ArrayList<>();
        try {
            String query = """
                               SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c 
                                JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID 
                                WHERE CAST(c.CourseID AS VARCHAR(10)) LIKE ? 
                                        OR c.Title LIKE ? 
                                        OR CAST(p.PersonID AS VARCHAR(10)) LIKE ? 
                                        or p.Firstname LIKE ? 
                                        OR P.Lastname LIKE ?
                               """;

            p = connection.prepareCall(query);

            p.setString(1, "%" + info + "%");
            p.setString(2, "%" + info + "%");
            p.setString(3, "%" + info + "%");
            p.setString(4, "%" + info + "%");
            p.setString(5, "%" + info + "%");

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CourseInstructor ci = new CourseInstructor();
                    ci.setCourseID(rs.getInt("CourseID"));
                    ci.setTitle(rs.getString("Title"));
                    ci.setInstructor(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Lastname"), Date.valueOf(rs.getString("HireDate").substring(0, 10)), null));
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
    
    public int getCourseInstructorWithInfoCount(String info){
        int count = 0;
        try{
            String query ="""
                               SELECT COUNT(*) AS count FROM `Course` c JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID WHERE CAST(c.CourseID AS VARCHAR(10)) LIKE ? OR c.Title LIKE ? OR CAST(p.PersonID AS VARCHAR(10)) LIKE ? or p.Firstname LIKE ? OR P.Lastname LIKE ?
                               """;
            
            p = connection.prepareCall(query);
            
            p.setString(1, "%" + info + "%");
            p.setString(2, "%" + info + "%");
            p.setString(3, "%" + info + "%");
            p.setString(4, "%" + info + "%");
            p.setString(5, "%" + info + "%");
            
            rs = p.executeQuery();
            
            if(rs != null){
                while(rs.next()){
                    count = rs.getInt("count");
                }
            }else {
                
                return 0;
            }
            
            
        }catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
    
    public List<CourseInstructor> getCoursesByPersonID (int PersonID){
        List<CourseInstructor> list = new ArrayList<>();
        try {
            connection = MyDatabaseConnection.connectDB();
            String query = """
                               SELECT c.CourseID,c.Title,i.PersonID,p.Lastname,p.Firstname, p.HireDate, p.EnrollmentDate FROM `Course` c 
                                JOIN `Department` d ON c.DepartmentID = d.DepartmentID JOIN `CourseInstructor` i ON c.CourseID = i.CourseID JOIN `Person` p ON i.PersonID = p.PersonID 
                                WHERE p.PersonID = ?                          
                               """;

            p = connection.prepareCall(query);

            p.setInt(1, PersonID );
          
            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    CourseInstructor ci = new CourseInstructor();
                    ci.setCourseID(rs.getInt("CourseID"));
                    ci.setTitle(rs.getString("Title"));
                    ci.setInstructor(new Person(rs.getInt("PersonID"), rs.getString("Lastname"), rs.getString("Lastname"), Date.valueOf(rs.getString("HireDate").substring(0, 10)), null));
                    list.add(ci);
                }
            }        
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CourseInstructorDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

