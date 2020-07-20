package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;


public class Warehouse implements IStorage {

    private final List<Food> storageWarehouse;

    public Warehouse() {
        this.storageWarehouse = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        storageWarehouse.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int percentUsed = food.getTimeDifference();
        return percentUsed < food.getSUITABLE();
    }

    @Override
    public List<Food> clear() {
        List<Food> foods = new ArrayList<>(storageWarehouse);
        storageWarehouse.clear();
        return foods;
    }
}
