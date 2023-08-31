package Dao;

import Model.EnrolledStudents;

import java.util.List;

public interface EnrolledStudentsDao {
    public Boolean assignCourse(EnrolledStudents enrolledStudents);
    List<EnrolledStudents> getAllEnrolledStudents();

    EnrolledStudents getEnrolledStudentById(Integer id);
    Boolean updateEnrolledStudents(EnrolledStudents enrolledStudents);
    Boolean deleteEnrolledStudents(Integer id);

}
