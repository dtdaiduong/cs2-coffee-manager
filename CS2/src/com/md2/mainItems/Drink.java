package com.md2.mainItems;

public class Drink {
    private String drinkId;
    private String drinkName;
    private double price;
    private DrinkType drinkType;

    public Drink() {
    }

    public Drink(String drinkId, String drinkName, double price) {
        this.drinkId = drinkId;
        this.drinkName = drinkName;
        this.price = price;
    }

    public Drink(String draw) {
        String[] fields = draw.split(",");
        drinkId = fields[0];
        drinkName = fields[1];
        price = Double.parseDouble(fields[2]);
        drinkType = DrinkType.parseDrinkType(fields[3]);
    }

    public String getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(String drinkId) {
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

    public DrinkType getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    @Override
    public String toString() {
        return drinkId + "," + drinkName + "," + price + ","+ drinkType.getValue();
    }

}
