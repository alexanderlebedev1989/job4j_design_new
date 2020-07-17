package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;

import java.util.HashMap;
import java.util.Map;

public class Shop implements IStorage {

    private Map<String, Food> storageShop = new HashMap<>();

    @Override
    public void add(Food food) {
        storageShop.put(food.getName(), food);
    }

    @Override
    public Food getFood(String name) {
        return storageShop.get(name);
    }
}
