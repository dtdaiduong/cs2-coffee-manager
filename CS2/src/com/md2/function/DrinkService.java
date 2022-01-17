package com.md2.function;

import com.md2.mainItems.Drink;
import com.md2.ulits.CSVUtils;

import java.util.ArrayList;
import java.util.List;

public class DrinkService implements IDrinkService {
    List<Drink> drinks = new ArrayList<>();
    public static String path = "data/drinks.csv";

    public DrinkService() {
        getDrinks();
    }

    @Override
    public List<Drink> getDrinks() {
        List<Drink> newDrinks = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newDrinks.add(new Drink(record));
        }
        return drinks = newDrinks;
    }

    @Override
    public void add(Drink newDrink) {
        drinks.add(newDrink);
        CSVUtils.write(path, drinks);
    }

    @Override
    public void update() {
        CSVUtils.write(path, drinks);
    }

    @Override
    public Drink getDrinkById(double id) {
        for (Drink drink : drinks) {
            if (drink.getDrinkId() == id)
                return drink;
        }
        return null;
    }

    @Override
    public Drink getDrinkByName(String name) {
        for (Drink drink : drinks) {
            if (drink.getDrinkName().equalsIgnoreCase(name))
                return drink;
        }
        return null;
    }

    @Override
    public boolean exist(double id) {
        return getDrinkById(id) != null;
    }

    @Override
    public boolean checkDuplicateId(double id) {
        for (Drink drink : drinks) {
            if (drink.getDrinkId() == id)
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateName(String name) {
        for (Drink drink : drinks) {
            if (drink.getDrinkName().equals(name))
                return true;
        }
        return false;
    }

    @Override
    public void remove(Drink drink) {
        drinks.remove(drink);
        update();
    }

}
