package ru.job4j_design_new.lsp.storage;

import ru.job4j_design_new.lsp.food.Food;
import java.util.ArrayList;
import java.util.List;


public class Shop implements IStorage {

    private final List<Food> storageShop;
    private final int NEW_DISCOUNT = 10;

    public Shop() {
        this.storageShop = new ArrayList<>();
    }

    @Override
    public void add(Food food) {
        storageShop.add(food);
    }

    @Override
    public boolean accept(Food food) {
        int percentUsed = food.getTimeDifference();
        if (percentUsed >= food.getSUITABLE() && percentUsed < food.getEXPIRING()) {
            return true;
        } else if (percentUsed > food.getEXPIRING() && percentUsed < food.getUNSUITABLE()) {
            food.setDiscount(NEW_DISCOUNT);
            return true;
        }
        return false;
    }

    @Override
    public List<Food> clear() {
        List<Food> foods = new ArrayList<>(storageShop);
        storageShop.clear();
        return foods;
    }
}
