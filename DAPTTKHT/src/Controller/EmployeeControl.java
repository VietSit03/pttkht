/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import Model.Account;
import Model.Employee;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class EmployeeControl {

    public static Employee findByUsername(String username) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from employee where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getString("idemployee"));
                e.setName(rs.getString("name"));
                e.setIdCard(rs.getString("idcard"));
                e.setGender(rs.getInt("gender"));
                e.setPosition(rs.getString("position"));
                e.setUsername(username);
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Account getAccount(String username) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from account where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString(1));
                a.setPassword(rs.getString(2));
                a.setAccessRight(rs.getInt(3));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Employee findByID(String id) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from employee where idemployee = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Employee e = new Employee();
                e.setId(rs.getString("idemployee"));
                e.setName(rs.getString("name"));
                e.setIdCard(rs.getString("idcard"));
                e.setGender(rs.getInt("gender"));
                e.setPosition(rs.getString("position"));
                e.setUsername(rs.getString("username"));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static boolean add(String name, String idCard, int gender, String position,
            String username) {
        if (name.equals("") || idCard.equals("") || gender == -1 || position.equals("Loại")
                || username.equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
        } else {
            String id;
            if (position.equals("Nhân viên bán hàng")) {
                id = ReturnNextIDNVBH();
            } else {
                id = ReturnNextIDNVK();
            }

            java.sql.Connection conn = ConnectionProvider.getConnection();

            if (checkIDCard(idCard) == false || checkUsername(username) == false) {
                return false;
            }

            String insert_user_emp = "insert account value (?, 1, ?)";
            try {
                PreparedStatement pst = conn.prepareStatement(insert_user_emp);
                pst.setString(1, username);
                if (position.equals("Nhân viên bán hàng")) {
                    pst.setInt(2, 2);
                } else {
                    pst.setInt(2, 3);
                }
                int rs = pst.executeUpdate();
                if (rs != 0) {
                    String insert_emp = "INSERT INTO employee (idemployee, name, "
                            + "idcard, gender, position, username)"
                            + " VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = conn.prepareStatement(insert_emp);
                    ps.setString(1, id);
                    ps.setString(2, name);
                    ps.setString(3, idCard);
                    ps.setInt(4, gender);
                    ps.setString(5, position);
                    ps.setString(6, username);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
                    return true;
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Unknown");
        return false;
    }

    public static boolean edit(String id, String name, String idCard, int gender, String position, String username) {
        Employee e = findByID(id);

        if (name.equals("") || idCard.equals("") || gender == -1 || position.equals("Loại")
                || username.equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
            return false;
        }

        if (!e.getIdCard().equals(idCard)) {
            if (checkIDCard(idCard) == false) {
                return false;
            }
        }

        if (e.getUsername().equals(username)) {
            java.sql.Connection conn = ConnectionProvider.getConnection();

            String insert_user_emp = "update account set accessRight = ? where username = ?";
            try {
                PreparedStatement pst = conn.prepareStatement(insert_user_emp);
                pst.setString(2, username);
                if (position.equals("Nhân viên bán hàng")) {
                    pst.setInt(1, 2);
                } else {
                    pst.setInt(1, 3);
                }
                int rst = pst.executeUpdate();
                if (rst != 0) {
                    String sql = "update employee set name = ?, idcard = ?, gender = ?, position = ? where idemployee = ?";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, name);
                    ps.setString(2, idCard);
                    ps.setInt(3, gender);
                    ps.setString(4, position);
                    ps.setString(5, id);
                    int rs = ps.executeUpdate();
                    if (rs != 0) {
                        JOptionPane.showMessageDialog(null, "Sửa thành công");
                        return true; //
                    }
                    return false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

        if (e.getUsername().equals(username) == false) {
            if (checkUsername(username) == true) {
                java.sql.Connection conn = ConnectionProvider.getConnection();
                String insert_user_emp = "insert account value (?, 1, ?)";
                try {
                    PreparedStatement pst = conn.prepareStatement(insert_user_emp);
                    pst.setString(1, username);
                    if (position.equals("Nhân viên bán hàng")) {
                        pst.setInt(2, 2);
                    } else {
                        pst.setInt(2, 3);
                    }
                    int rst = pst.executeUpdate();
                    if (rst != 0) {
                        String sql = "update employee set name = ?, idcard = ?, gender = ?, position = ?, username = ? where idemployee = ?";
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2, idCard);
                        ps.setInt(3, gender);
                        ps.setString(4, position);
                        ps.setString(5, username);
                        ps.setString(6, id);
                        int rs = ps.executeUpdate();
                        if (rs != 0) {
                            String sqlString = "delete from account where username = ?";
                            PreparedStatement pss = conn.prepareStatement(sqlString);
                            pss.setString(1, e.getUsername());
                            int rs1 = pss.executeUpdate();
                            if (rs1 != 0) {
                                JOptionPane.showMessageDialog(null, "Sửa thành công");
                                return true;
                            }
                            return false; //
                        }
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false; // Lỗi k xác định
    }

    public static ArrayList<Employee> listEmp() {
        ArrayList<Employee> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from employee join account on employee.username = account.username where accessRight != 0";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Employee e = new Employee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
                list.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }

    public static void remove(String username) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "update account set accessRight = 0 where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                ConnectionProvider.closeConnection(conn);
                JOptionPane.showMessageDialog(null, "Xoá thành công");
                return; //
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
    }

    public static String ReturnNextIDNVBH() {//trả về mã nhân viên bán hàng tiếp theo
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(idemployee) FROM employee WHERE position = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Nhân viên bán hàng");
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString(1) != null) {
                String so = rs.getString(1).substring(4);//lấy chuỗi sau NV
                int soNhanVien = Integer.parseInt(so);//chuyển thành số
                soNhanVien++;
                String maNhanVienMoi = "NVBH" + String.format("%02d", soNhanVien);
                return maNhanVienMoi;
            } else {
                return "NVBH01";
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static String ReturnNextIDNVK() {//trả về mã nhân viên bán hàng tiếp theo
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(idemployee) FROM employee WHERE position = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "Nhân viên kho");
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString(1) != null) {
                String so = rs.getString(1).substring(3);//lấy chuỗi sau NV
                int soNhanVien = Integer.parseInt(so);//chuyển thành số
                soNhanVien++;
                String maNhanVienMoi = "NVK" + String.format("%02d", soNhanVien);
                return maNhanVienMoi;
            } else {
                return "NVK01";
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static void changePass(String username, String newPass) {
        if (newPass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu mới");
            return;
        } else {
            java.sql.Connection conn = ConnectionProvider.getConnection();
            String sql = "update account set password = ? where username = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, newPass);
                ps.setString(2, username);
                int rs = ps.executeUpdate();
                if (rs != 0) {
                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                    ConnectionProvider.closeConnection(conn);
                    return;
                }
                ConnectionProvider.closeConnection(conn);
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
                ConnectionProvider.closeConnection(conn);
            }
        }
    }

    public static boolean checkIDCard(String idCard) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String checkIdCard = "select * from employee where idcard = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(checkIdCard);
            ps.setString(1, idCard);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Số CMND đã tồn tại");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            long x = Integer.parseInt(idCard.substring(0, 7));
            long z = Integer.parseInt(idCard.substring(7, idCard.length()));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Định dạng CMND không hợp lệ");
            return false;
        }
        return true;
    }

    public static boolean checkUsername(String username) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String checkUsername = "select * from employee where username = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(checkUsername);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
