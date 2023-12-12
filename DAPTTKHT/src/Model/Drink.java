/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class Drink {
    private String id;
    private String name;
    private String type;
    private int price;
    private String idP;
    private int enable;

    public Drink() {
    }

    public Drink(String name, String type, int price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public Drink(String id, String name, String type, int price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
    public Drink(String id, String name, int price, String idP) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idP = idP;
    }

    public Drink(String id, String name, String type, int price, String idP) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.idP = idP;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }
    
    
}
