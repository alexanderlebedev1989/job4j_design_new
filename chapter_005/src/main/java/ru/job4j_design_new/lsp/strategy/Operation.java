package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;

public interface Operation extends OperationResort {

    void distribute(Food food);
}
