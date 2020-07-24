package ru.job4j_design_new.isp.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private List<IMenu> componentsMenu;

    public Menu(String name) {
        this.name = name;
        this.componentsMenu = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<IMenu> getComponentsMenu() {
        return componentsMenu;
    }


    @Override
    public String toString() {
        return "==" + name + "==";
    }
}
