package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.storage.IStorage;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality implements Operation {

    private final List<IStorage> storages;

    public ControlQuality() {
        this.storages = new ArrayList<>();
    }

    public void add(IStorage storage) {
        storages.add(storage);
    }

    @Override
    public void distribute(Food food) {
        for (IStorage storage : storages) {
            if (storage.accept(food)) {
                storage.add(food);
                break;
            }
        }
    }

    @Override
    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (IStorage storage : storages) {
            foods.addAll(storage.clear());
        }
        for (Food food : foods) {
            distribute(food);
        }
    }
}
