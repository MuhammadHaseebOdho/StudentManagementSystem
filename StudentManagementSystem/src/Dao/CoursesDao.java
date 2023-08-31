package Dao;

import Model.Courses;

import java.util.List;

public interface CoursesDao {
    public Boolean addCourses(Courses courses);

     List<Courses> getAllCourses();
     Boolean updateCourses(Courses courses);

     Boolean deleteCourses(Integer id);

     Courses getCoursesById(Integer id);

     Courses getCourseByName(String name);
}
