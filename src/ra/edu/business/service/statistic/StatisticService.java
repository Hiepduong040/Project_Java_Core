//package ra.edu.business.service.Statistic;
//
//import java.util.Scanner;
//
//public interface StatisticService {
//    void countCoursesAndStudents();
//    void countStudentsByCourse();
//    void topFiveCoursesByStudents();
//    void listCoursesWithMoreThanTenStudents();
//}

package ra.edu.business.service.statistic;

import java.util.Scanner;

public interface StatisticService {
    void countCoursesAndStudents();
    void countStudentsByCourse(Scanner scanner);
    void topFiveCoursesByStudents();
    void listCoursesWithMoreThanTenStudents();
}