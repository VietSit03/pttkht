/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import Model.Drink;
import Model.Promotion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class PromotionControl {
    public static String ReturnNextIDPromotion(int priority) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "SELECT MAX(idpromotion) FROM promotion WHERE priority = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, priority);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String so = rs.getString(1).substring(2);
                int soID = Integer.parseInt(so);//chuyển thành số
                soID++;
                String newID = "";
                switch (priority) {
                    case 1 -> newID = "PA" + String.format("%02d", soID);
                    case 2 -> newID = "PB" + String.format("%02d", soID);
                }
                return newID;
            }
            switch (priority) {
                    case 1 -> {
                        return "PA01";
                }
                    case 2 -> {
                        return "PB01";
                }
                }
        } catch (SQLException ex) {
            Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public static void add(Promotion p) {
        if (String.valueOf(p.getPrice()).equals("")) {
            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
        } else {
            java.sql.Connection conn = ConnectionProvider.getConnection();

            String insert_promotion = "insert promotion value (?, ?, ?, ?, ?)";
            try {
                PreparedStatement ps = conn.prepareStatement(insert_promotion);
                ps.setString(1, p.getId());
                ps.setInt(2, p.getPrice());
                ps.setInt(3, p.getPriority());
                ps.setDate(4, Date.valueOf(p.getStart()));
                ps.setDate(5, Date.valueOf(p.getEnd()));
                int rs = ps.executeUpdate();
                if (rs != 0) {
                    JOptionPane.showMessageDialog(null, "Áp dụng KM thành công");
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DrinkControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        JOptionPane.showMessageDialog(null, "Unknown");
    }
    
    public static Promotion getPromotion(String idP) {
        java.sql.Connection conn = ConnectionProvider.getConnection();
        String sql = "select * from promotion where idpromotion = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, idP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Promotion(idP, rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate(), rs.getDate(5).toLocalDate());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionControl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static int getPriceP(Drink d) {
        if (d.getIdP() != null) {
            Promotion p = getPromotion(d.getIdP());
            if (LocalDate.now().isAfter(p.getStart().minusDays(1)) && LocalDate.now().isBefore(p.getEnd().plusDays(1))) {
                return p.getPrice();
            }
        }
        return d.getPrice();
    }
}

