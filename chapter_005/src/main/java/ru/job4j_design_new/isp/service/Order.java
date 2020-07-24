package ru.job4j_design_new.isp.service;

import ru.job4j_design_new.isp.menu.IMenu;

public interface Order {
    boolean orderFood(IMenu menu, Input input);
}
