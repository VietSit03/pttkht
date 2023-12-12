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
public class InvoiceDetailsXuatHuy extends Invoice{
    private String idIngredientDetails;
    private Double quantity;

    public InvoiceDetailsXuatHuy(String id, String idEmp, LocalDateTime date) {
        super(id, idEmp, date);
    }

    public String getIdIngredientDetails() {
        return idIngredientDetails;
    }

    public void setIdIngredientDetails(String idIngredientDetails) {
        this.idIngredientDetails = idIngredientDetails;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
    
}
