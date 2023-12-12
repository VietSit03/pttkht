/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Connection.ConnectionProvider;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class LoginControl {
    public static int checkLog(String username, String password) {
        if(username.equals("") || password.equals("")){
            return -1;  // role = -1 -> thieu thong tin
        }else if(username.equals("admin") && password.equals("admin")){
            return 1;   // role = 0 -> quan ly
        }else {
            java.sql.Connection conn = ConnectionProvider.getConnection();
            String sql = "select * from account where username = ? and password = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    int accR = rs.getInt("accessRight");
                    if (accR == 2) {
                        return 2;  // 1 -> nhan vien ban hang
                    } else if (accR == 3) {
                        return 3;   // 2 -> nhan vien kho
                    } else {
                        return 0;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(LoginControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 7;   // 7 -> sai thong tin
    }
}
