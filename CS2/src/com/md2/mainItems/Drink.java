package com.md2.mainItems;

public class Drink {
    private int drinkId;
    private String drinkName;
    private double price;

    public Drink() {
    }

    public Drink(int drinkId, String drinkName, double price) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.price = price;
    }

    public Drink(String draw){
        String[] fields = draw.split(",");
        drinkId = Integer.parseInt(fields[0]);
        drinkName = fields[1];
        price = Double.parseDouble(fields[2]);
    }

    public int getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(int drinkId) {
        this.drinkId = drinkId;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return drinkId + "," + drinkName + "," + price ;
    }

}
