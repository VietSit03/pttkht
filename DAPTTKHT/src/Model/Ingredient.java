/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Ingredient {
    private String idIngredient;
    private String name;
    private String unit;

    public Ingredient() {
    }

    public Ingredient(String idIngredient, String name, String unit) {
        this.idIngredient = idIngredient;
        this.name = name;
        this.unit = unit;
    }

    public String getId() {
        return idIngredient;
    }

    public void setId(String idIngredient) {
        this.idIngredient = idIngredient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    
}
