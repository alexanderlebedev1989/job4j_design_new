package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;

public interface IStorage {

    void add(Food food);

    Food getFood(String name);
}
