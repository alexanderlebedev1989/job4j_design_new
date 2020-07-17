package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;

import java.util.HashMap;
import java.util.Map;

public class Warehouse implements IStorage {

    private Map<String, Food> storageWarehouse = new HashMap<>();

    @Override
    public void add(Food food) {
        storageWarehouse.put(food.getName(), food);
    }

    @Override
    public Food getFood(String name) {
        return storageWarehouse.get(name);
    }
}
