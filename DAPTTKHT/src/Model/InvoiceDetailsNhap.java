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
public class InvoiceDetailsNhap extends Invoice{
    private String idIngredientDetails;

    public InvoiceDetailsNhap(String idIngredientDetails, String id, String idEmp, LocalDateTime date) {
        super(id, idEmp, date);
        this.idIngredientDetails = idIngredientDetails;
    }

    public String getIdIngredientDetails() {
        return idIngredientDetails;
    }

    public void setIdIngredientDetails(String idIngredientDetails) {
        this.idIngredientDetails = idIngredientDetails;
    }
    
    
}
