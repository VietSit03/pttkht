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
public class InvoiceDetailsTL extends Invoice{
    private String idIngredientDetails;
    private double quantity;
    private int price;

    public InvoiceDetailsTL(String idIngredientDetails, double quantity, int price, String id, String idEmp, LocalDateTime date) {
        super(id, idEmp, date);
        this.idIngredientDetails = idIngredientDetails;
        this.quantity = quantity;
        this.price = price;
    }

    public InvoiceDetailsTL(String id, String idEmp, LocalDateTime date) {
        super(id, idEmp, date);
    }

    public String getIdIngredientDetails() {
        return idIngredientDetails;
    }

    public void setIdIngredientDetails(String idIngredientDetails) {
        this.idIngredientDetails = idIngredientDetails;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
