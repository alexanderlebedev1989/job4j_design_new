package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.storage.IStorage;
import ru.job4j_design_new.lsp.storage.Shop;
import ru.job4j_design_new.lsp.storage.Trash;
import ru.job4j_design_new.lsp.storage.Warehouse;
import java.util.ArrayList;
import java.util.List;

public class ControlQuality implements Strategy {

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
}
