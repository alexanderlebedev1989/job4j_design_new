package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.storage.IStorage;

public class Office {
    Strategy strategy;

    public Office(Strategy strategy) {
        this.strategy = strategy;
    }

    public IStorage execute(Food food, int percentUsed, int percentDiscount) {
        return strategy.operation(food, percentUsed, percentDiscount);
    }
}
