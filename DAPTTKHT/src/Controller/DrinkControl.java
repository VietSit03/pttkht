/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Drink;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class DrinkControl {
    public static ArrayList<Drink> listDrink(){
        ArrayList<Drink> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from drink where enable = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                Drink d = new Drink(rs.getString("iddrink"), rs.getString("name"), rs.getString("type"), rs.getInt("price"), rs.getString("idpromotion"));
                list.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }
    
    public static ArrayList<String> listType(){
        ArrayList<String> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select distinct type from drink where enable = 1";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {                
                list.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }
    
    public static String ReturnNextIDDrink(String type) {//trả về mã nhân viên bán hàng tiếp theo
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(iddrink) FROM drink WHERE type = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString(1) != null) {
                String so = rs.getString(1).substring(2);
                int soID = Integer.parseInt(so);//chuyển thành số
                soID++;
                String newID = "";
                switch (type) {
                    case "Cà phê":
                        newID = "CP" + String.format("%02d", soID);
                        break;
                    case "Nước ép":
                        newID = "NE" + String.format("%02d", soID);
                        break;
                    case "Trà sữa":
                        newID = "TS" + String.format("%02d", soID);
                        break;
                    case "Topping":
                        newID = "TP" + String.format("%02d", soID);
                        break;
                    case "Sinh tố":
                        newID = "ST" + String.format("%02d", soID);
                        break;
                    case "Khác":
                        newID = "KH" + String.format("%02d", soID);
                        break;
                }
                return newID;
            } else {
                return "KH01";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static void add(String name, String type, int price) {
        if (name.equals("") || type.equals("Loại") || String.valueOf(price).equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
        } else {
            String id = ReturnNextIDDrink(type);

            java.sql.Connection conn = ConnectionProvider.getConnection();

            String insert_drink = "insert drink value (?, ?, ?, ?, 1)";
            try {
                PreparedStatement ps = conn.prepareStatement(insert_drink);
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, type);
                ps.setInt(4, price);
                int rs = ps.executeUpdate();
                if (rs != 0) {
                    JOptionPane.showMessageDialog(null, "Thêm đồ uống thành công");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Unknown");
        return;
    }
    
    public static void remove(String idDrink) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "update drink set enable = 0 where iddrink = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idDrink);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                ConnectionProvider.closeConnection(conn);
                JOptionPane.showMessageDialog(null, "Xoá thành công");
                return; //
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
    }
    
    public static void addPromotion(String idDrink, String idPromotion) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "update drink set idpromotion = ? where iddrink = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idPromotion);
            ps.setString(2, idDrink);
            int rs = ps.executeUpdate();
            if (rs != 0) {
                ConnectionProvider.closeConnection(conn);
                JOptionPane.showMessageDialog(null, "Áp dụng KM thành công");
                return; //
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
    }
    
    public static Drink findByName(String name) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from drink where name = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Drink d = new Drink();
                d.setId(rs.getString("iddrink"));
                d.setName(name);
                d.setType(rs.getString("type"));
                d.setPrice(rs.getInt("price"));
                d.setIdP(rs.getString("idpromotion"));
                d.setEnable(rs.getInt("enable"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static Drink findByID(String id) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from drink where iddrink = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Drink d = new Drink();
                d.setId(rs.getString("iddrink"));
                d.setName(rs.getString("name"));
                d.setType(rs.getString("type"));
                d.setPrice(rs.getInt("price"));
                d.setIdP(rs.getString("idpromotion"));
                d.setEnable(rs.getInt("enable"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
