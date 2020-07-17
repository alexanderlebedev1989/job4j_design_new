package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;

import java.util.HashMap;
import java.util.Map;

public class Trash implements IStorage {

    private Map<String, Food> storageTrash = new HashMap<>();

    @Override
    public void add(Food food) {
        storageTrash.put(food.getName(), food);
    }

    @Override
    public Food getFood(String name) {
        return storageTrash.get(name);
    }
}
