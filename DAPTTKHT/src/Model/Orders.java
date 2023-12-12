/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Orders {
    private String id;
    private LocalDateTime date;
    private int methodPay;  // 1:tien mat   2:chuyen khoan  3:quet the
    private int status;     // 1:chua thanh toan    2:dang treo thanh toan  3:order bi huy  4:da thanh toan
    private String idEmp;

    public Orders() {
    }

    public Orders(String id, LocalDateTime date, int methodPay, int status, String idEmp) {
        this.id = id;
        this.date = date;
        this.methodPay = methodPay;
        this.status = status;
        this.idEmp = idEmp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getMethodPay() {
        return methodPay;
    }

    public void setMethodPay(int methodPay) {
        this.methodPay = methodPay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(String idEmp) {
        this.idEmp = idEmp;
    }

}
