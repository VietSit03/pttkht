/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class IngredientDetails extends Ingredient{
    private String idDetails;
    private double quantity;
    private int price;
    private LocalDate expire;

    public IngredientDetails() {
    }

    public IngredientDetails(String idDetails, double quantity) {
        this.idDetails = idDetails;
        this.quantity = quantity;
    }

    public IngredientDetails(String idDetails, double quantity, int price) {
        this.idDetails = idDetails;
        this.quantity = quantity;
        this.price = price;
    }
    
    public IngredientDetails(String idIngredient, String name, String unit, String idDetails, double quantity, int price, LocalDate expire) {
        super(idIngredient, name, unit);
        this.idDetails = idDetails;
        this.quantity = quantity;
        this.price = price;
        this.expire = expire;
    }

    public IngredientDetails(String idDetails, double quantity, LocalDate expire) {
        this.idDetails = idDetails;
        this.quantity = quantity;
        this.expire = expire;
    }

    public String getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(String idDetails) {
        this.idDetails = idDetails;
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
    
    public LocalDate getExpire() {
        return expire;
    }

    public void setExpire(LocalDate expire) {
        this.expire = expire;
    }
    
    
}
