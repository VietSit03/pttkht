/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import Model.Ingredient;
import Model.IngredientDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class IngredientControl {

    public static ArrayList<Ingredient> listIngredient() {
        ArrayList<Ingredient> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from ingredient as i join ingredient_details as d on i.idingredient = d.idingredient where quantity != 0";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Ingredient i = new Ingredient(rs.getString("idingredient"), rs.getString("name"), rs.getString("unit"));
                list.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }

    public static ArrayList<IngredientDetails> listIngredientDetails() {
        ArrayList<IngredientDetails> list = new ArrayList<>();
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from ingredient as i join ingredient_details as d on i.idingredient = d.idingredient where quantity != 0";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IngredientDetails i;
                if (rs.getString("expire") == null) {
                    i = new IngredientDetails(rs.getString("idingredient"), rs.getString("name"), rs.getString("unit"), rs.getString("id"), rs.getDouble("quantity"), rs.getInt("price"), null);
                } else {
                    i = new IngredientDetails(rs.getString("idingredient"), rs.getString("name"), rs.getString("unit"), rs.getString("id"), rs.getDouble("quantity"), rs.getInt("price"), rs.getDate("expire").toLocalDate());
                }
                list.add(i);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConnectionProvider.closeConnection(conn);
        return list;
    }

    public static String ReturnNextIDIngredient() {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(idingredient) FROM ingredient";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String so = rs.getString(1).substring(2);//lấy chuỗi sau NV
                int soid = Integer.parseInt(so);//chuyển thành số
                soid++;
                String id = "NL" + String.format("%02d", soid);
                return id;
            } else {
                return "NL01";
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static String ReturnNextIDIngredientDetails(String idingredient) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(id) FROM ingredient_details WHERE idingredient = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idingredient);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String so = rs.getString(1).substring(4);//lấy chuỗi sau NV
                int soid = Integer.parseInt(so);//chuyển thành số
                soid++;
                String id = idingredient + String.format("%03d", soid);
                return id;
            } else {
                return idingredient + String.format("%03d", 1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static void addIngredientDetails(IngredientDetails i) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String s = "SELECT * FROM ingredient WHERE name = ?";
        try {
            PreparedStatement pss = conn.prepareStatement(s);
            pss.setString(1, i.getName());
            ResultSet rss = pss.executeQuery();
            if (rss.next()) {           //In cũ
                String sql1 = "INSERT INTO ingredient_details VALUES (?, ?, ?, ?, ?)";
                PreparedStatement ps1 = conn.prepareStatement(sql1);
                ps1.setString(1, i.getIdDetails());
                ps1.setString(2, i.getId());
                ps1.setDouble(3, i.getQuantity());
                ps1.setInt(4, i.getPrice());
                if (i.getExpire() != null) {
                    ps1.setDate(5, Date.valueOf(i.getExpire()));
                } else {
                    ps1.setDate(5, null);
                }
                int rs1 = ps1.executeUpdate();
                if (rs1 != 0) {

                }
            } else {                        //In mới
                String sql = "INSERT INTO ingredient VALUES (?, ?, ?)";
                try {
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setString(1, i.getId());
                    ps.setString(2, i.getName());
                    ps.setString(3, i.getUnit());
                    int rs = ps.executeUpdate();
                    if (rs != 0) {
                        String sql1 = "INSERT INTO ingredient_details VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement ps1 = conn.prepareStatement(sql1);
                        ps1.setString(1, i.getIdDetails());
                        ps1.setString(2, i.getId());
                        ps1.setDouble(3, i.getQuantity());
                        ps1.setInt(4, i.getPrice());
                        ps1.setDate(5, Date.valueOf(i.getExpire()));
                        int rs1 = ps1.executeUpdate();
                        if (rs1 != 0) {

                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(IngredientControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static double getQuantity(String idIngredientDetails) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String s = "SELECT * FROM ingredient_details WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setString(1, idIngredientDetails);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("quantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Error");
        return 0;
    }

    public static void updateQuantity(String idIngredientDetails, double quantity) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String s = "UPDATE ingredient_details SET quantity = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(s);
            ps.setDouble(1, getQuantity(idIngredientDetails) - quantity);
            ps.setString(2, idIngredientDetails);
            int i = ps.executeUpdate();
            if (i == 0) {
                System.out.println("Error updateQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngredientControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
