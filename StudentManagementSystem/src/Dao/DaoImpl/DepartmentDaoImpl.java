package Dao.DaoImpl;

import Dao.DepartmentDao;
import DbConnection.DbConnection;
import Model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    Connection connection = DbConnection.getConnection();
    private static final String Insert_Dept = "insert into departments(dpt_name,dpt_code) values(?,?)";
    private static final String  Get_All_Departments_Query="select * from departments";
    private static final String  Get_Department_Name_Query="select * from departments where dpt_name=?";
    private static final String  Get_Department_Id_Query="select * from departments where dpt_id=?";
    private static final String Update_Dept = "update departments set dpt_name=?,dpt_code=? where dpt_id=?";
    private static final String Delete_Dept = "delete from departments where dpt_id=?";
    @Override
    public boolean addDepartment(Department department) {
        boolean success=true;
        try {
            PreparedStatement ps = connection.prepareStatement(Insert_Dept);
            ps.setString(1, department.getName());
            ps.setString(2, department.getDptCode());
            ps.execute();
        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> getAllDepartments=new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(Get_All_Departments_Query);
            ResultSet rst = ps.executeQuery();
            while(rst.next()){
                Department department=new Department();
                department.setDptId(rst.getInt("dpt_id"));
                department.setName(rst.getString("dpt_name"));
                department.setDptCode(rst.getString("dpt_code"));
                getAllDepartments.add(department);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return getAllDepartments;
    }

    @Override
    public Department getDepartmentName(String name) {
        try{
            PreparedStatement ps=connection.prepareStatement(Get_Department_Name_Query);
            ps.setString(1,name);
            ResultSet rst=ps.executeQuery();
            while(rst.next()){
                Department department=new Department();
                department.setDptId(rst.getInt("dpt_id"));
                department.setName(rst.getString("dpt_name"));
                department.setDptCode((rst.getString("dpt_code")));
                return  department;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }


    @Override
    public Department getDepartmentById(Integer id) {
        try{
            PreparedStatement ps=connection.prepareStatement(Get_Department_Id_Query);
            ps.setInt(1,id);
            ResultSet rst=ps.executeQuery();
            while(rst.next()){
                Department department=new Department();
                department.setDptId(rst.getInt("dpt_id"));
                department.setName(rst.getString("dpt_name"));
                department.setDptCode((rst.getString("dpt_code")));
                return  department;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateDepartment(Department department) {
        Boolean success=true;
        try {
            PreparedStatement ps1 = connection.prepareStatement(Update_Dept);
            ps1.setString(1, department.getName());
            ps1.setString(2, department.getDptCode());
            ps1.setInt(3, department.getDptId());
            ps1.execute();

        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Boolean deleteDepartment(Integer id) {
        Boolean success=true;
        try {
            PreparedStatement ps = connection.prepareStatement(Delete_Dept);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }
}
