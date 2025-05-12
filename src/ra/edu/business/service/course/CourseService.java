package ra.edu.business.service.course;

import ra.edu.business.dao.AppDAO;
import ra.edu.business.model.Course;

import java.util.Scanner;

public interface CourseService extends AppDAO<Course> {
    Course findByName(String name);
    int totalCourse();
    Course findCourseById(int id);
    void listCoursesPagination(Scanner scanner);
    void sortByName(Scanner scanner);
    void sortById(Scanner scanner);
}




