package ru.job4j_design_new.lsp.strategy;

import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.storage.IStorage;

public interface Strategy {

    IStorage operation(Food food, int percentUsed, int percentDiscount);
}
