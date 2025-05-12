//package ra.edu.business.service.enrollment;
//
//import ra.edu.business.model.Enrollment;
//
//import java.util.List;
//import java.util.Scanner;
//
//public interface EnrollmentService {
//    boolean registerCourse(Enrollment enrollment);
//    boolean cancelEnrollment(int enrollmentId);
//    List<Enrollment> getEnrollmentsByStudent(int studentId);
//    int countEnrollmentsByStudent(int studentId);
//    void listEnrollmentsByCourse(Scanner scanner, int courseId);
//    boolean approveEnrollment(int enrollmentId, String newStatus);
//    Enrollment findEnrollmentById(int enrollmentId);
//
//    List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage);
//
//    int countWaitingEnrollments();
//}

package ra.edu.business.service.enrollment;

import ra.edu.business.model.Enrollment;

import java.util.List;
import java.util.Scanner;

public interface EnrollmentService {
    boolean registerCourse(Enrollment enrollment);
    boolean cancelEnrollment(int enrollmentId);
    List<Enrollment> getEnrollmentsByStudent(int studentId);
    int countEnrollmentsByStudent(int studentId);
    void listEnrollmentsByCourse(Scanner scanner, int courseId);
    boolean approveEnrollment(int enrollmentId, String newStatus);
    Enrollment findEnrollmentById(int enrollmentId);
    List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage);
    int countWaitingEnrollments();
    List<Enrollment> listDeniedEnrollments(int pageSize, int currentPage);
    int countDeniedEnrollments();
}