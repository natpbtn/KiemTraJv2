package dao;

import connection.MyConnection;
import model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    /**
     * Trả về array Student
     * @return
     */
    public List<Student> getAll() {
        List<Student> studentsList = new ArrayList<>();
        // Bước 1: tạo kết nối
        // Bước 2: chuẩn bị câu lệnh
        // Bước 3: thực thi
        // Bước 4: đóng kết nối

        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM student";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setFullName(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setDateOfBirth(rs.getString("date_of_birth"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getInt("phone_number"));
                s.setEmail(rs.getString("email"));
                s.setDiemGpa(rs.getDouble("diem_gpa"));
                studentsList.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }

    /**
     * Thêm 1 student vào database
     *
     * @param s
     */
    public void insert(Student s) {
        final String sql = String.format("INSERT INTO `student` (`id`,`full_name`,`gender`,`date_of_birth`,`address`,`phone_number`,`email`,`diem_gpa`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%f')",
                s.getId(),
                s.getFullName(),
                s.getGender(),
                s.getDateOfBirth(),
                s.getAddress(),
                s.getPhoneNumber(),
                s.getEmail(),
                s.getDiemGpa()
        );

        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();

            int rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Thêm thất bại!");
            }
            stmt.close();
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Lấy sinh viên theo ID
     *
     * @param id
     * @return nếu không tìm thấy trả về null
     */
    public Student getById(String id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql =  String.format("SELECT * FROM student WHERE id = '%s'", id);

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            Student s = null;
            if (rs.next()) {
                s = new Student();
                s.setId(rs.getString("id"));
                s.setFullName(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setDateOfBirth(rs.getString("date_of_birth"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getInt("phone_number"));
                s.setEmail(rs.getString("email"));
                s.setDiemGpa(rs.getDouble("diem_gpa"));
            }
            rs.close();
            stmt.close();
            conn.close();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Student s, String id) {
        Student tmp = getById(id);
        if (tmp == null) {
            System.out.println("Không tồn tại sinh viên có id = " + id);
            return;
        }
        final String sql = String.format("UPDATE `student` SET `full_name` = '%s', `gender`= '%d' ,`date_of_birth`='%s',`address`='%s',`phone_number`='%d',`email`='%s',`diem_gpa`='%s' WHERE `id`='%s' ",
                s.getFullName(),
                s.getGender(),
                s.getDateOfBirth(),
                s.getAddress(),
                s.getPhoneNumber(),
                s.getEmail(),
                s.getDiemGpa(),
                id
        );

        System.out.println(sql);
        try {
            Connection conn = MyConnection.getConnection();
            Statement stmt = conn.createStatement();
            long rs = stmt.executeUpdate(sql);

            if (rs == 0) {
                System.out.println("Cập nhật thất bại");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            Connection conn = MyConnection.getConnection();
            final String sql =  String.format("DELETE FROM student WHERE id = '%s'", id);

            Statement stmt = conn.createStatement();

            long rs = stmt.executeUpdate(sql);
            if (rs == 0) {
                System.out.println("Xoá thất bại");
            }
            stmt.close();
            conn.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Student> getAllOrderByGpa() {
        List<Student> studentsList = new ArrayList<>();
        // Bước 1: tạo kết nối
        // Bước 2: chuẩn bị câu lệnh
        // Bước 3: thực thi
        // Bước 4: đóng kết nối

        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM student order by(diem_gpa) desc";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setFullName(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setDateOfBirth(rs.getString("date_of_birth"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getInt("phone_number"));
                s.setEmail(rs.getString("email"));
                s.setDiemGpa(rs.getDouble("diem_gpa"));
                studentsList.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }


    public List<Student> getStudentInHaNoi() {
        List<Student> studentsList = new ArrayList<>();
        // Bước 1: tạo kết nối
        // Bước 2: chuẩn bị câu lệnh
        // Bước 3: thực thi
        // Bước 4: đóng kết nối

        try {
            Connection conn = MyConnection.getConnection();
            final String sql = "SELECT * FROM student where address = 'Ha Noi' and diem_gpa > 2.6";

            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student s = new Student();
                s.setId(rs.getString("id"));
                s.setFullName(rs.getString("full_name"));
                s.setGender(rs.getInt("gender"));
                s.setDateOfBirth(rs.getString("date_of_birth"));
                s.setAddress(rs.getString("address"));
                s.setPhoneNumber(rs.getInt("phone_number"));
                s.setEmail(rs.getString("email"));
                s.setDiemGpa(rs.getDouble("diem_gpa"));
                studentsList.add(s);
            }
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentsList;
    }
}
