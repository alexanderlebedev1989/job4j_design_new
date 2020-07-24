package ru.job4j_design_new.isp.menu;


import java.util.List;

public class CafeMenu extends Menu implements IMenu{
    public CafeMenu(String name) {
        super(name);
    }

    @Override
    public void show() {
        System.out.println(toString());
        for (IMenu menu : getComponentsMenu()) {
            menu.show();
        }
    }

    @Override
    public void addComponents(IMenu menu) {
        getComponentsMenu().add(menu);
    }

    @Override
    public List<IMenu> get() {
        return getComponentsMenu();
    }

    @Override
    public String toString() {
        return getName();
    }
}
