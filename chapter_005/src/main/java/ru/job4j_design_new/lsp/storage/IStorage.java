package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;

import java.util.List;

public interface IStorage {

    void add(Food food);

    boolean accept(Food food);

    List<Food> clear();
}
