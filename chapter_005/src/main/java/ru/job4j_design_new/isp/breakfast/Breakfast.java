package ru.job4j_design_new.isp.breakfast;

import ru.job4j_design_new.isp.menu.IMenu;
import ru.job4j_design_new.isp.menu.Menu;

import java.util.List;


public class Breakfast extends Menu implements IMenu {

    public Breakfast(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(toString());
        for (IMenu menu : getComponentsMenu()) {
            menu.show();
        }
    }

    public void addComponents(IMenu menu) {
        getComponentsMenu().add(menu);
    }

    @Override
    public List<IMenu> get() {
        return getComponentsMenu();
    }

    @Override
    public String toString() {
        return " -> " + getName();
    }
}
