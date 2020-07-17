package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.storage.IStorage;
import ru.job4j_design_new.lsp.storage.Shop;
import ru.job4j_design_new.lsp.storage.Trash;
import ru.job4j_design_new.lsp.storage.Warehouse;

public class ControlQuality implements Strategy {

    private final int SUITABLE = 25;
    private final int EXPIRING= 75;
    private final int UNSUITABLE = 100;

    @Override
    public IStorage operation(Food food, int percentUsed, int percentDiscount) {
        IStorage storage;
        if (percentUsed < SUITABLE) {
            storage = new Warehouse();
        } else if (percentUsed > EXPIRING && percentUsed < UNSUITABLE) {
            food.setDiscount(percentDiscount);
            storage = new Shop();
        } else if (percentUsed >= SUITABLE && percentUsed < UNSUITABLE) {
            storage = new Shop();
        } else {
            storage =  new Trash();
        }
        storage.add(food);
        return storage;
    }
}
