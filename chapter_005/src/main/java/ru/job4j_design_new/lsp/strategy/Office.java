package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;

public class Office {
    Operation operation;

    public Office(Operation operation) {
        this.operation = operation;
    }

    public void executeDistribute(Food food) {
        operation.distribute(food);
    }

    public void executeResort() {
        operation.resort();
    }
}
