package com.md2.function;

import com.md2.mainItems.Drink;

import java.util.List;

public interface IDrinkService {
    List<Drink> getDrinks();

    void add(Drink newDrink);

    void update();

    Drink getDrinkById(double id);

    Drink getDrinkByName(String name);

    boolean exist(double id);

    boolean checkDuplicateId(double id);

    boolean checkDuplicateName(String name);

    void remove(Drink drink);

}
