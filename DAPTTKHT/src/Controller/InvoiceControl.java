/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import Model.IngredientDetails;
import Model.InvoiceDetailsTL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class InvoiceControl {

    public static void addInvoiceNhap(ArrayList<IngredientDetails> LNhap, String idEmp) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String add = "insert into invoice values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(add);
            String id = ReturnNextIDInvoice();
            ps.setString(1, id);
            ps.setString(2, idEmp);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, 1);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                String sql = "insert invoicenhap value (?, ?)";
                PreparedStatement pss = conn.prepareStatement(sql);
                pss.setString(1, sql);
                for (IngredientDetails iD : LNhap) {
                    pss.setString(1, id);
                    pss.setString(2, iD.getIdDetails());
                    pss.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addInvoiceXuat(ArrayList<IngredientDetails> LXuat, String idEmp) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String add = "insert into invoice values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(add);
            String id = ReturnNextIDInvoice();
            ps.setString(1, id);
            ps.setString(2, idEmp);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, 2);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                String sql = "insert invoicexuathuy value (?, ?, ?)";
                PreparedStatement pss = conn.prepareStatement(sql);
                pss.setString(1, sql);
                for (IngredientDetails iD : LXuat) {
                    pss.setString(1, id);
                    pss.setString(2, iD.getIdDetails());
                    pss.setDouble(3, iD.getQuantity());
                    pss.executeUpdate();
                    IngredientControl.updateQuantity(iD.getIdDetails(), iD.getQuantity());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addInvoiceHuy(ArrayList<IngredientDetails> LHuy, String idEmp) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String add = "insert into invoice values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(add);
            String id = ReturnNextIDInvoice();
            ps.setString(1, id);
            ps.setString(2, idEmp);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, 3);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                String sql = "insert invoicexuathuy value (?, ?, ?)";
                PreparedStatement pss = conn.prepareStatement(sql);
                pss.setString(1, sql);
                for (IngredientDetails iD : LHuy) {
                    pss.setString(1, id);
                    pss.setString(2, iD.getIdDetails());
                    pss.setDouble(3, iD.getQuantity());
                    pss.executeUpdate();
                    IngredientControl.updateQuantity(iD.getIdDetails(), iD.getQuantity());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void addInvoiceTL(ArrayList<IngredientDetails> LTL, String idEmp) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String add = "insert into invoice values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(add);
            String id = ReturnNextIDInvoice();
            ps.setString(1, id);
            ps.setString(2, idEmp);
            ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(4, 4);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                String sql = "insert invoicetly value (?, ?, ?, ?)";
                PreparedStatement pss = conn.prepareStatement(sql);
                pss.setString(1, sql);
                for (IngredientDetails iD : LTL) {
                    pss.setString(1, id);
                    pss.setString(2, iD.getIdDetails());
                    pss.setDouble(3, iD.getQuantity());
                    pss.setInt(4, iD.getPrice());
                    pss.executeUpdate();
                    IngredientControl.updateQuantity(iD.getIdDetails(), iD.getQuantity());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static String ReturnNextIDInvoice() {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(idinvoice) FROM invoice";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString(1) != null) {
                String so = rs.getString(1).substring(2);
                int id = Integer.parseInt(so);//chuyển thành số
                id++;
                String newid = "ID" + String.format("%03d", id);
                return newid;
            } else {
                return "ID001";
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
}
