package com.md2.mainItems;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private long id;
    private String drinkName;
    private int quantity;
    private double price;
    private double totalPrice;

    private List<Order> orders = new ArrayList<>();

    public Order() {

    }

    public Order(String draw) {
        String[] field = draw.split(",");
        id = Long.parseLong(field[0]);
        drinkName = field[1];
        quantity = Integer.parseInt(field[2]);
        price = Double.parseDouble(field[3]);
        totalPrice = Double.parseDouble(field[4]);
    }

    public Order(long id, String drinkName, int quantity, double price, double totalPrice) {
        this.id = id;
        this.drinkName = drinkName;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return id + "," + drinkName + "," + quantity + "," + price + "," + totalPrice;
    }
}
