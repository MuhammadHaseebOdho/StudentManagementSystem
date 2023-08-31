package Dao.DaoImpl;

import Dao.StudentDao;
import DbConnection.DbConnection;
import Model.Department;
import Model.Student;
//import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {

   

    Connection connection=DbConnection.getConnection();
    private static final String Insert_Student_Query = "insert into students(student_name,student_rollnum,student_age,dept_id) values(?,?,?,?)";
    private static final String Get_All_Student_Query ="\n" +
            "SELECT s.student_id,s.student_name,s.student_rollnum,s.student_age,d.dpt_name\n" +
            "FROM students s\n" +
            "INNER JOIN departments d ON s.`dept_id`=d.`dpt_id`;";

    private static final String Get_Student_BY_ID_Query ="select * from students where student_id=?";
    private static final String Update_Student_Query = "update students set student_name=?,student_rollnum=?,student_age=?,dept_id=? where student_id=?";
    private static final String Delete_Student_Query = "delete from students where student_id=?";
    private static final String  Get_Student_Name_Query="select * from students where student_name=?";


    public  Boolean updateStudent(Student student) {
        Boolean success=true;
        try {

            PreparedStatement ps1 = connection.prepareStatement(Update_Student_Query);
            ps1.setString(1, student.getStudentName());
            ps1.setString(2, student.getRollNumber());
            ps1.setInt(3, student.getAge());
            ps1.setInt(4, student.getDepartment().getDptId());
            ps1.setInt(5, student.getStudentId());
            ps1.execute();
        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
       return success;
    }
    @Override
    public boolean addStudent(Student student) {
        boolean success=true;
        try{
            PreparedStatement ps=connection.prepareStatement(Insert_Student_Query);
            ps.setString(1,student.getStudentName());
            ps.setString(2,student.getRollNumber());
            ps.setInt(3,student.getAge());
            ps.setInt(4,student.getDepartment().getDptId());
            ps.execute();

        }catch (Exception e){
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<Student> getAllStudents(){
        List<Student> getallstudentsList=new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(Get_All_Student_Query);
            ResultSet rst = ps.executeQuery();
            while(rst.next()){
                Student student=new Student();
                student.setStudentId(rst.getInt("student_id"));
                student.setStudentName(rst.getString("student_name"));
           
                student.setAge(rst.getInt("student_age"));
                student.setRollNumber(rst.getString("student_rollnum"));
                Department department=new Department();
                department.setName(rst.getString("dpt_name"));
                student.setDepartment(department);
                getallstudentsList.add(student);
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
       return  getallstudentsList;
    }

    @Override
    public Student getStudentById(Integer id) {
        try {
            PreparedStatement ps=connection.prepareStatement(Get_Student_BY_ID_Query);
            ps.setInt(1,id);
            ResultSet rst=ps.executeQuery();
            while(rst.next()){
                Student student=new Student();
                student.setStudentId(rst.getInt("student_id"));
                student.setStudentName(rst.getString("student_name"));
                student.setRollNumber(rst.getString("student_rollnum"));
                student.setAge(rst.getInt("student_age"));
                Department department=new Department();
                department.setDptId(rst.getInt("dept_id"));
                student.setDepartment(department);
                return student;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean deleteStudent(Integer id) {
        Boolean succes=true;
        try {
            PreparedStatement ps = connection.prepareStatement(Delete_Student_Query);
            ps.setInt(1, id);
            ps.execute();
        }catch (Exception e){
            succes=false;
            e.printStackTrace();
        }
        return succes;
    }

    @Override
    public Student getStudentByName(String name) {
        try{
            PreparedStatement ps=connection.prepareStatement(Get_Student_Name_Query);
            ps.setString(1,name);
            ResultSet rst=ps.executeQuery();
            while(rst.next()){
                Student student=new Student();
                student.setStudentId((rst.getInt("student_id")));
                student.setStudentName(rst.getString("student_name"));
                Department department=new Department();
                department.setDptId(rst.getInt("dept_id"));
                student.setDepartment(department);
                return  student;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
