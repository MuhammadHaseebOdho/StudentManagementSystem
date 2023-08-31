package Dao;

import Model.Student;

import java.util.List;

public interface StudentDao {
    boolean addStudent(Student student);
    List<Student> getAllStudents();

    Student getStudentById(Integer id);
   Boolean updateStudent(Student student);
    Boolean deleteStudent(Integer id);

    Student getStudentByName(String name);

}
