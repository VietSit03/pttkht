/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import Model.Employee;
import Model.OrderDetails;
import Model.Orders;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class OrderControl {

    public static void add(Orders order) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "insert orders value (?, ?, null, 1, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, order.getId());
            ps.setTimestamp(2, Timestamp.valueOf(order.getDate()));
            ps.setString(3, order.getIdEmp());
            int rs = ps.executeUpdate();
            if (rs != 0) {
                ConnectionProvider.closeConnection(conn);
                JOptionPane.showMessageDialog(null, "Lập order thành công");
                return; //
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
    }

    public static String ReturnNextIDO() {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(idorder) FROM orders";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString(1) != null) {
                String so = rs.getString(1).substring(2);//lấy chuỗi sau NV
                int soID = Integer.parseInt(so);//chuyển thành số
                soID++;
                String maNhanVienMoi = "OD" + String.format("%04d", soID);
                return maNhanVienMoi;
            } else {
                return "OD0001";
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static void pay(String idOrder, int p) {
        java.sql.Connection conn = ConnectionProvider.getConnection();

        String sql = "update orders set methodPay = ? where idorder = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, p);
            ps.setString(2, idOrder);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                setStatusOrder(idOrder, 4);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setStatusOrder(String idOrder, int s) {
        java.sql.Connection conn = ConnectionProvider.getConnection();

        String sql = "update orders set status = ? where idorder = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, s);
            ps.setString(2, idOrder);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                switch (s) {
                    case 4 -> {
                        JOptionPane.showMessageDialog(null, "Thanh toán thành công");
                        return;
                    }
                    case 3 -> {
                        JOptionPane.showMessageDialog(null, "Huỷ order thành công");
                        return;
                    }
                    case 2 -> {
                        JOptionPane.showMessageDialog(null, "Treo order thành công");
                        return;
                    }
                    case 1 -> {
                        JOptionPane.showMessageDialog(null, "Lập order thành công");
                        return;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, "Error");
    }

    public static ArrayList<Orders> listOU(String username) {
        ArrayList<Orders> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        if (username.equals("admin")) {
            String sql = "select * from orders where status = 2";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Orders o = new Orders(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), 0, 2, rs.getString(5));
                    list.add(o);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConnectionProvider.closeConnection(conn);
            return list;
        }
        String sql = "select * from orders where status = 2 and idemp = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Controller.EmployeeControl.findByUsername(username).getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), 0, 2, rs.getString(5));
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }

    public static void addOD(OrderDetails OD) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "insert order_details value (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, OD.getIdOrder());
            ps.setString(2, OD.getIdDrink());
            ps.setInt(3, OD.getQuantity());
            ps.setInt(4, OD.getPrice());
            ps.setString(5, OD.getNote());
            int rs = ps.executeUpdate();
            if (rs != 0) {
                ConnectionProvider.closeConnection(conn);
                return; //
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
    }

    public static ArrayList<OrderDetails> listOD(String idO) {
        ArrayList<OrderDetails> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from order_details where idorder = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idO);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetails oD = new OrderDetails(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
                list.add(oD);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }
    
    public static ArrayList<Orders> listOrderDay(Employee e) {
        ArrayList<Orders> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        if (e.getUsername().equals("admin")) {
            String sql = "select * from orders where DATE(datetime) = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    Orders o = new Orders(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), rs.getInt(3), rs.getInt(4), rs.getString(5));
                    list.add(o);
                }
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
            }
            ConnectionProvider.closeConnection(conn);
            return list;
        }
        String sql = "select * from orders where DATE(datetime) = ? and idemp = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            ps.setString(2, e.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders o = new Orders(rs.getString(1), rs.getTimestamp(2).toLocalDateTime(), rs.getInt(3), rs.getInt(4), rs.getString(5));
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }
    
    public static String convertStatus(int s) {
        switch (s) {
            case 1:
                return "Đã lập";
            case 2:
                return "Đang treo";
            case 3:
                return "Đã bị huỷ";
            case 4:
                return "Đã thanh toán";
        }
        return "";
    }
    
    public static String convertMethod(int md) {
        switch (md) {
            case 1:
                return "Tiền mặt";
            case 2:
                return "Chuyển khoản";
            case 3:
                return "Quẹt thẻ";
        }
        return "";
    }
    
    public static int getPrice(Orders o) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        int price = 0;
        String sql = "SELECT * FROM order_details where idorder = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, o.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                price += rs.getInt(4);
            }
            return price;
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return price;
    }
}
