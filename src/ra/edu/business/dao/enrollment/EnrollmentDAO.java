//package ra.edu.business.dao.enrollment;
//
//import ra.edu.business.model.Enrollment;
//
//import java.util.List;
//
//public interface EnrollmentDAO {
//    boolean save(Enrollment enrollment);
//    boolean cancel(int enrollmentId);
//    List<Enrollment> findByStudentId(int studentId);
//    int totalEnrollmentsByStudent(int studentId);
//    List<Enrollment> listEnrollmentsByCourse(int courseId, int limit, int page);
//    int countEnrollmentsByCourse(int courseId);
//    boolean approveEnrollment(int enrollmentId, String newStatus);
//    Enrollment findById(int enrollmentId);
//
//    List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage);
//
//    int countWaitingEnrollments();
//}

package ra.edu.business.dao.enrollment;

import ra.edu.business.model.Enrollment;

import java.util.List;

public interface EnrollmentDAO {
    boolean save(Enrollment enrollment);
    boolean cancel(int enrollmentId);
    List<Enrollment> findByStudentId(int studentId);
    int totalEnrollmentsByStudent(int studentId);
    List<Enrollment> listEnrollmentsByCourse(int courseId, int limit, int page);
    int countEnrollmentsByCourse(int courseId);
    boolean approveEnrollment(int enrollmentId, String newStatus);
    Enrollment findById(int enrollmentId);
    List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage);
    int countWaitingEnrollments();
    List<Enrollment> listDeniedEnrollments(int pageSize, int currentPage);
    int countDeniedEnrollments();
}