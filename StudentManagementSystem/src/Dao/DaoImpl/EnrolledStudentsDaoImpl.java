package Dao.DaoImpl;

import Dao.EnrolledStudentsDao;
import DbConnection.DbConnection;
import Model.Courses;
import Model.Department;
import Model.EnrolledStudents;
import Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EnrolledStudentsDaoImpl implements EnrolledStudentsDao {
    Connection connection=DbConnection.getConnection();
    private static final String Assign_Course = "insert into enrolled_students(student_id,course_id,obt_marks) values (?,?,?)";
    private static final String Get_All_Students_Courses = "SELECT e.enrolled_id,s.student_name,c.course_name FROM enrolled_students e \n" +
            "INNER JOIN students s ON s.`student_id`=e.`student_id`\n" +
            "INNER JOIN courses c ON c.`course_id`=e.`course_id` ;";

    private static final String Get_Enrolled_Students_BY_ID_Query ="select * from enrolled_students where enrolled_id=?";
    private static final String Update_Course = "update enrolled_students set student_id=?, course_id=? where enrolled_id=?";
    private static final String Delete_Enrolled = "delete from enrolled_students where enrolled_id=?;";
    @Override
    public Boolean assignCourse(EnrolledStudents enrolledStudents) {
        Boolean success=true;
        try{
            PreparedStatement ps=connection.prepareStatement(Assign_Course);
            ps.setInt(1,enrolledStudents.getStudent().getStudentId());
            ps.setInt(2,enrolledStudents.getCourses().getCourseId());
            ps.setInt(3,0);
            ps.execute();
        }catch (Exception e){
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<EnrolledStudents> getAllEnrolledStudents() {
        List< EnrolledStudents > getAllEnrolledStudentsList = new ArrayList < > ();
        try {
            PreparedStatement ps = connection.prepareStatement(Get_All_Students_Courses);
            ResultSet rst = ps.executeQuery();
            while (rst.next()) {
                EnrolledStudents enrolledStudents=new EnrolledStudents();
                enrolledStudents.setStudent(new Student());
                enrolledStudents.setCourses(new Courses());
                enrolledStudents.setEnrolledId(rst.getInt("enrolled_id"));
                enrolledStudents.getStudent().setStudentName(rst.getString("student_name"));
                enrolledStudents.getCourses().setName(rst.getString("course_name"));
                //enrolledStudents.setObtMarks(rst.getInt("obt_marks"));
                getAllEnrolledStudentsList.add(enrolledStudents);
            }
            return getAllEnrolledStudentsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EnrolledStudents getEnrolledStudentById(Integer id) {
        try {
            PreparedStatement ps=connection.prepareStatement(Get_Enrolled_Students_BY_ID_Query);
            ps.setInt(1,id);
            ResultSet rst=ps.executeQuery();
            while(rst.next()){
                EnrolledStudents enrolledStudents=new EnrolledStudents();
                enrolledStudents.setEnrolledId(rst.getInt("enrolled_id"));
                Student student=new Student();
                student.setStudentId(rst.getInt("student_id"));
                enrolledStudents.setStudent(student);
                Courses courses=new Courses();
                courses.setCourseId(rst.getInt("course_id"));
                enrolledStudents.setCourses(courses);
                return enrolledStudents;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean updateEnrolledStudents(EnrolledStudents enrolledStudents) {
        Boolean success=true;
        try {
            PreparedStatement ps1 = connection.prepareStatement(Update_Course);
            ps1.setInt(1, enrolledStudents.getStudent().getStudentId());
            ps1.setInt(2, enrolledStudents.getCourses().getCourseId());
           
            ps1.setInt(3, enrolledStudents.getEnrolledId());
            ps1.execute();

        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Boolean deleteEnrolledStudents(Integer id) {
        Boolean success=true;
        try {
            PreparedStatement ps = connection.prepareStatement(Delete_Enrolled);
            ps.setInt(1, id);
            ps.execute();

        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }
}
