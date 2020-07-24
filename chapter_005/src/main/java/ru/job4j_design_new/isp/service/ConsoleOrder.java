package ru.job4j_design_new.isp.service;

import ru.job4j_design_new.isp.menu.IMenu;

public class ConsoleOrder implements Order {

    @Override
    public boolean orderFood(IMenu menu, Input input) {
        boolean result = false;
        String foodName = input.askName("Введите название блюда: ");
        IMenu food = find(menu, foodName);
        if (food != null) {
            result = true;
        }
        return result;
    }

    public IMenu find(IMenu menu, String name) {
        IMenu order = null;
        for (IMenu mRoot: menu.get()) {
            for (IMenu mChild : mRoot.get()) {
                if (mChild.toString().equals(name)) {
                    order = mChild;
                    break;
                }
            }
        }
        return order;
    }
}
