package org.example;

import dao.StudentDAO;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class StudentApplication {

    private static StudentDAO studentDAO = new StudentDAO();

    private static List<Student> studentsList = studentDAO.getAll();

    private static List<Student> studentsList1 = studentDAO.getAllOrderByGpa();

    private static List<Student> studentsList2 = studentDAO.getStudentInHaNoi();


    private static void mainMenu(){
        System.out.println("--------Quản Lý Danh Sách Sinh Viên-----------");
        System.out.println("1. Danh sách sinh viên theo bảng");
        System.out.println("2. Thêm một sinh viên mới");
        System.out.println("3. Xoá một sinh viên theo mã");
        System.out.println("4. Cập nhật thông tin sinh viên");
        System.out.println("5. Tìm một sinh viên theo họ tên hoặc mã");
        System.out.println("6. Sắp xếp sinh viên theo điểm số GPA tăng dần");
        System.out.println("7. In ra tất cả sinh viên nữ ở Hà Nội có GPA trên 2.5");
        System.out.println("8. Sắp xếp sinh viên theo họ tên, sắp xếp theo bảng chữ cái abc");
    }

    private static void option1() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s", "STT","Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ");
        System.out.println();
        for (int i = 0; i < studentsList.size(); i++) {
            Student stud = studentsList.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s\n", (i+1), stud.getId(), stud.getFullName(), stud.getGender(), stud.getAddress());
        };
    }

    private static void option2(Scanner in){
        Student s = new Student();
        System.out.print("Nhập ID Sinh Viên : ");
        s.setId(in.nextLine());
        System.out.print("Nhập Họ Và Tên : ");
        s.setFullName(in.nextLine());
        System.out.print("Nhập Giới Tính : ");
        s.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("Nhập Ngày Sinh : ");
        s.setDateOfBirth(in.nextLine());
        System.out.print("Nhập Địa Chỉ : ");
        s.setAddress(in.nextLine());
        System.out.print("Nhập Phone Number : ");
        s.setPhoneNumber(Integer.parseInt(in.nextLine()));
        System.out.print("Nhập Email : ");
        s.setEmail(in.nextLine());
        System.out.print("Nhập Điểm GPA : ");
        s.setDiemGpa(Double.parseDouble(in.nextLine()));
        studentDAO.insert(s);
    }

    private static void option3(Scanner in) {
        Student s = new Student();
        System.out.print("Nhập id muốn xóa: ");
        String id = in.nextLine();
        studentDAO.delete(id);
    }

    private static void option4(Scanner in){
        Student s = new Student();
        System.out.print("Nhập id sinh viên cần cập nhật : ");
        String id = in.nextLine();
        System.out.print("Nhập Họ Và Tên : ");
        s.setFullName(in.nextLine());
        System.out.print("Nhập Giới Tính : ");
        s.setGender(Integer.parseInt(in.nextLine()));
        System.out.print("Nhập Ngày Sinh : ");
        s.setDateOfBirth(in.nextLine());
        System.out.print("Nhập Địa Chỉ : ");
        s.setAddress(in.nextLine());
        System.out.print("Nhập Phone Number : ");
        s.setPhoneNumber(Integer.parseInt(in.nextLine()));
        System.out.print("Nhập Email : ");
        s.setEmail(in.nextLine());
        System.out.print("Nhập Điểm GPA : ");
        s.setDiemGpa(Double.parseDouble(in.nextLine()));
        studentDAO.update(s, id);
    }

    private static void option6() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "STT","Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ", "Điểm GPA");
        System.out.println();
        for (int i = 0; i < studentsList1.size(); i++) {
            Student st = studentsList1.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", (i+1), st.getId(), st.getFullName(), st.getGender(), st.getAddress(),st.getDiemGpa());
        };
    }

    private static void option7() {
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s", "STT","Mã sinh viên", "Họ tên", "Giới tính", "Địa chỉ", "Điểm GPA");
        System.out.println();
        for (int i = 0; i < studentsList2.size(); i++) {
            Student st = studentsList2.get(i);
            System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s\n", (i+1), st.getId(), st.getFullName(), st.getGender(), st.getAddress(),st.getDiemGpa());
        };
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int option = -1;
        do {
            mainMenu();
            System.out.println("Nhập lựa chọn:");
            try {
                option = Integer.parseInt(in.nextLine());
            } catch (Exception e) {
                System.out.println("Sai định dạng");
                continue;
            }
            if (option < 1 || option > 9) {
                System.out.println("Lựa chọn không hợp lệ");
                continue;
            }
            switch (option) {
                case 1:
                    option1();
                    break;
                case 2:
                    option2(in);
                    break;
                case 3:
                    option3(in);
                    break;
                case 4:
                    option4(in);
                    break;
                case 5:
                    break;
                case 6:
                    option6();
                    break;
                case 7:
                    option7();
                    break;
                case 8:
                    break;
            }
        }
        while (option != 9);
        in.close();
    }
}
