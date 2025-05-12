package ra.edu.business.service.student;

import ra.edu.business.dao.student.StudentDAOImp;
import ra.edu.business.model.Pagination;
import ra.edu.business.model.Student;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Scanner;

public class StudentServiceImp implements StudentService {
    private StudentDAOImp studentDAOImp;

    public StudentServiceImp() {
        studentDAOImp = new StudentDAOImp();
    }

    Pagination pagination = new Pagination();

    @Override
    public Student findByEmail(String email) {
        return studentDAOImp.findByEmail(email);
    }

    @Override
    public Student findById(int id) {
        return studentDAOImp.findById(id);
    }

    public List<Student> listStudents(String search) {
        if (search == null) {
            return studentDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
        } else {
            return studentDAOImp.findByNameOrEmailOrId(search, pagination.getPagesize(), pagination.getCurrentpage());
        }
    }

    private void displayStudentListPagination(Scanner scanner, String search, String sortBy, String sortOrder) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);
        do {
            List<Student> listPagination = null;
            // Tính tổng số kết quả trước khi lấy danh sách phân trang
            int totalResults = search == null ? totalStudent() : studentDAOImp.findByNameOrEmailOrId(search, Integer.MAX_VALUE, 1).size();
            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
            pagination.setTotalpages(totalPages);

            if (sortBy == null) {
                listPagination = (search == null) ?
                        studentDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage()) :
                        studentDAOImp.findByNameOrEmailOrId(search, pagination.getPagesize(), pagination.getCurrentpage());
            } else {
                listPagination = (sortBy.equals("NAME")) ?
                        studentDAOImp.sortByName(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder) :
                        studentDAOImp.sortById(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder);
            }

            if (!listPagination.isEmpty()) {
                System.out.println("---------------------------------------------------------------------------------------------------");
                System.out.printf("%-5s | %-20s | %-25s | %-15s | %-10s | %-15s | %-15s\n",
                        "ID", "Tên", "Email", "SĐT", "Giới tính", "Ngày sinh", "Ngày tạo");
                System.out.println("---------------------------------------------------------------------------------------------------");
                listPagination.forEach(student -> {
                    System.out.printf("%-5d | %-20s | %-25s | %-15s | %-10s | %-15s | %-15s\n",
                            student.getId(), student.getName(), student.getEmail(), student.getPhone(),
                            student.isSex() ? "Nam" : "Nữ", student.getDob(), student.getCreate_at());
                });
                System.out.println("---------------------------------------------------------------------------------------------------");

                // Hiển thị phân trang
                if (totalResults <= pagination.getPagesize()) {
                    // Trường hợp ≤ pagesize kết quả: chỉ hiển thị trang 1
                    System.out.println("Trang: \u001B[33m1\u001B[0m");
                    System.out.println("1. Chọn trang");
                    System.out.println("2. Thoát");
                    System.out.print("Lựa chọn: ");
                } else {
                    // Trường hợp có nhiều trang
                    System.out.print("Trang: ");
                    if (pagination.getCurrentpage() > 1) {
                        System.out.print("Previous      ");
                        if (pagination.getCurrentpage() >= 3) System.out.print("... ");
                        System.out.print(pagination.getCurrentpage() - 1);
                    }
                    System.out.print("\u001B[33m    " + pagination.getCurrentpage() + "     \u001B[0m");
                    if (pagination.getCurrentpage() < totalPages) {
                        System.out.print(" " + (pagination.getCurrentpage() + 1));
                        if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" ...");
                        System.out.print("      Next");
                    }
                    System.out.println();
                    if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
                    if (pagination.getCurrentpage() < totalPages) System.out.println("N. Trang tiếp");
                    System.out.println("1. Chọn trang");
                    System.out.println("2. Thoát");
                    System.out.print("Lựa chọn: ");
                }

                while (true) {
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Lựa chọn không được để trống! Vui lòng nhập lại:");
                        continue;
                    }
                    char choice = Character.toUpperCase(input.charAt(0));
                    switch (choice) {
                        case '1':
                            int page = Validator.validateInt(scanner, 1, totalPages, "Nhập trang: ", "Trang");
                            pagination.setCurrentpage(page);
                            break;
                        case '2':
                            Exit = true;
                            break;
                        case 'P':
                            if (pagination.getCurrentpage() > 1)
                                pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                            break;
                        case 'N':
                            if (pagination.getCurrentpage() < totalPages)
                                pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                            continue;
                    }
                    break;
                }
            } else {
                System.out.println("Không có học viên nào.");
                break;
            }
        } while (!Exit);
    }

    @Override
    public void listStudentsPagination(Scanner scanner) {
        displayStudentListPagination(scanner, null, null, null);
    }

    @Override
    public int totalStudent() {
        return studentDAOImp.totalCount();
    }

    @Override
    public List<Student> findAll() {
        return studentDAOImp.findAll();
    }

    @Override
    public boolean save(Student student) {
        return studentDAOImp.save(student);
    }

    @Override
    public boolean update(Student student) {
        return studentDAOImp.update(student);
    }

    @Override
    public boolean delete(Student student) {
        return studentDAOImp.delete(student);
    }
    @Override
    public void findStudentByNameOrEmailOrId(Scanner scanner, String search) {
        displayStudentListPagination(scanner, search, null, null);
    }

    @Override
    public void sortByName(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayStudentListPagination(scanner, null, "NAME", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayStudentListPagination(scanner, null, "NAME", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!Exit);
    }

    @Override
    public void sortById(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayStudentListPagination(scanner, null, "ID", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayStudentListPagination(scanner, null, "ID", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!Exit);
    }

    @Override
    public boolean updatePassword(int studentId, String newPassword) {
        return studentDAOImp.updatePassword(studentId, newPassword);
    }
}