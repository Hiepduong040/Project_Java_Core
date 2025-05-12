//package ra.edu.business.dao.Statistic;
//
//import ra.edu.business.model.Statistic;
//
//import java.util.List;
//
//public interface StatisticDAO {
//    Statistic countCoursesAndStudents();
//    Statistic countStudentsByCourse(int courseId);
//    List<Statistic> topFiveCoursesByStudents();
//    List<Statistic> listCoursesWithMoreThanTenStudents();
//    List<Statistic> listAllCoursesWithStudentCount();
//}

package ra.edu.business.dao.statistic;

import ra.edu.business.model.Statistic;

import java.util.List;

public interface StatisticDAO {
    Statistic countCoursesAndStudents();
    Statistic countStudentsByCourse(int courseId);
    List<Statistic> topFiveCoursesByStudents();
    List<Statistic> listCoursesWithMoreThanTenStudents();
    List<Statistic> listAllCoursesWithStudentCount(int pageSize, int pageNumber);
    int countTotalCourses();
}