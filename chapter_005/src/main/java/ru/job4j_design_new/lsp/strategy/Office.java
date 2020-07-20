package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;

public class Office {
    Strategy strategy;

    public Office(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(Food food) {
        strategy.distribute(food);
    }
}
