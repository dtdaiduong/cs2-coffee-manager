package com.md2.sort;

import com.md2.mainItems.Drink;

import java.util.Comparator;

public class SortIdASC implements Comparator<Drink> {
    @Override
    public int compare(Drink o1, Drink o2) {
        return (int) (o1.getDrinkId().compareTo(o2.getDrinkId()));
    }
}
