/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Account {
    private String username;
    private String password;
    private int accessRight;    //1:Admin   2:NVBH  3:NVK

    public Account() {
    }

    public Account(String username, String password, int accessRight) {
        this.username = username;
        this.password = password;
        this.accessRight = accessRight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessRight() {
        return accessRight;
    }

    public void setAccessRight(int accessRight) {
        this.accessRight = accessRight;
    }
    
    
}
