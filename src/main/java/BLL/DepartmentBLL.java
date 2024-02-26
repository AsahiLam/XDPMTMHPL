/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BLL;

import DAL.DepartmentDAL;
import DAL.entities.Department;
import java.util.List;

/**
 *
 * @author lamquoc
 */
public class DepartmentBLL {

    DepartmentDAL departmentDAL;

    public DepartmentBLL() {
        departmentDAL = new DepartmentDAL();
    }

    public Department getDepartment(int DepartmentID) {
        Department d = departmentDAL.getDepartMent(DepartmentID);
        return d;
    }
    
    public List<Department> getDepartmentList(){
        List<Department> dpl = departmentDAL.getDepartmentList();
        return dpl;
    }
}
