package Dao.DaoImpl;

import Dao.CoursesDao;
import DbConnection.DbConnection;
import Model.Courses;
import Model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CoursesDaoImpl implements CoursesDao {
    Connection connection=DbConnection.getConnection();
    private static final String Insert_Course_Query = "insert into courses(course_name,course_code) values(?,?)";
    private static final String Get_All_Courses_Query ="select * from courses";
    private static final String Update_Course = "update courses set course_name=?,course_code=? where course_id=?";
    private static final String Delete_Course = "delete from courses where course_id=?;";
    private static final String  Get_Courses_Id_Query="select * from courses where course_id=?";
    private static final String  Get_Course_By_Name_Query="select * from courses where course_name=?";
    @Override
    public Boolean addCourses(Courses courses) {
        Boolean success=true;
        try{

            PreparedStatement ps=connection.prepareStatement(Insert_Course_Query);
            ps.setString(1,courses.getName());
            ps.setString(2,courses.getCourseCode());
            ps.execute();
        }catch (Exception e){
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public List<Courses> getAllCourses() {
        List<Courses> getAllCoursesList=new ArrayList<>();
        try{
            PreparedStatement ps = connection.prepareStatement(Get_All_Courses_Query);
            ResultSet rst = ps.executeQuery();
            while(rst.next()){
                Courses courses=new Courses();
                courses.setCourseId(rst.getInt("course_id"));
                courses.setName(rst.getString("course_name"));
                courses.setCourseCode(rst.getString("course_code"));
                getAllCoursesList.add(courses);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return getAllCoursesList;
    }

    @Override
    public Boolean updateCourses(Courses courses) {
        Boolean success=true;
        try {

            PreparedStatement ps1 = connection.prepareStatement(Update_Course);
            ps1.setString(1,courses.getName());
            ps1.setString(2,courses.getCourseCode());
            ps1.setInt(3,courses.getCourseId());
            ps1.execute();


        } catch (Exception e) {
            success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Boolean deleteCourses(Integer id) {
        Boolean success=true;
        try {
            PreparedStatement ps = connection.prepareStatement(Delete_Course);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
             success=false;
            e.printStackTrace();
        }
        return success;
    }

    @Override
    public Courses getCoursesById(Integer id) {
        try{
            PreparedStatement ps=connection.prepareStatement(Get_Courses_Id_Query);
            ps.setInt(1,id);
            ResultSet rst=ps.executeQuery();
            while(rst.next()){
                Courses courses=new Courses();
                courses.setCourseId(rst.getInt("course_id"));
                courses.setName(rst.getString("course_name"));
                courses.setCourseCode(rst.getString("course_code"));
                return  courses;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Courses getCourseByName(String name) {
        try {
            PreparedStatement ps=connection.prepareStatement(Get_Course_By_Name_Query);
            ps.setString(1,name);
            ResultSet rst=ps.executeQuery();
            while (rst.next()){
                Courses courses=new Courses();
                courses.setCourseId(rst.getInt("course_id"));
                courses.setName(rst.getString("course_name"));
                courses.setCourseCode(rst.getString("course_code"));
                return courses;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
