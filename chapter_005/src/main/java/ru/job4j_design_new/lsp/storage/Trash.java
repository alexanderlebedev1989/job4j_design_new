package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;

public class Trash implements IStorage {

    private final List<Food> storageTrash;

    public Trash() {
        this.storageTrash = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        storageTrash.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int percentUsed = food.getTimeDifference();
        return percentUsed >= food.getUNSUITABLE();
    }

    @Override
    public List<Food> clear() {
        List<Food> foods = new ArrayList<>(storageTrash);
        storageTrash.clear();
        return foods;
    }
}
