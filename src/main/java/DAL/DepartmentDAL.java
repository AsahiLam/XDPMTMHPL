/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import DAL.entities.Department;
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
public class DepartmentDAL extends MyDatabaseConnection {

    Connection connection = null;
    PreparedStatement p = null;
    ResultSet rs = null;

    public DepartmentDAL() {
        
    }

    public Department getDepartMent(int DepartmentID) {
        Department dp = new Department();

        try {
            connection = MyDatabaseConnection.connectDB();

            String query = "SELECT * FROM Department WHERE Department.DepartmentID = ?";

            p = connection.prepareStatement(query);

            p.setInt(1, DepartmentID);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    dp.setDepartmentID(rs.getInt("DepartmentID"));
                    dp.setName(rs.getString("Name"));
                    dp.setBudget(rs.getDouble("Budget"));
                    dp.setStartDate(Date.valueOf(rs.getString("StartDate").substring(0, 10)));
                    dp.setAdmintrator(rs.getInt("Administrator"));
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dp;
    }

    public List<Department> getDepartmentList() {
        List<Department> dpl = new ArrayList<>();

        try {
            connection = MyDatabaseConnection.connectDB();
            
            String query = "SELECT * FROM Department";

            p = connection.prepareStatement(query);

            rs = p.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    Department dp = new Department();
                    dp.setDepartmentID(rs.getInt("DepartmentID"));
                    dp.setName(rs.getString("Name"));
                    dp.setBudget(rs.getDouble("Budget"));
                    dp.setStartDate(Date.valueOf(rs.getString("StartDate").substring(0, 10)));
                    dp.setAdmintrator(rs.getInt("Administrator"));
                    dpl.add(dp);
                }
            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dpl;
    }
}
