package ra.edu.business.model;

public class Statistic {
    private int courseId;
    private String courseName;
    private int studentCount;
    private int totalCourses;
    private int totalStudents;

    // Constructor cho thống kê tổng số
    public Statistic(int totalCourses, int totalStudents) {
        this.totalCourses = totalCourses;
        this.totalStudents = totalStudents;
    }

    // Constructor cho thống kê theo khóa học
    public Statistic(int courseId, String courseName, int studentCount) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentCount = studentCount;
    }

    // Getters and Setters
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTotalCourses() {
        return totalCourses;
    }

    public void setTotalCourses(int totalCourses) {
        this.totalCourses = totalCourses;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }
}