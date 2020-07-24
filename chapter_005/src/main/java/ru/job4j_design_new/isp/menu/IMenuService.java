package ru.job4j_design_new.isp.menu;

import java.util.List;

public interface IMenuService {
    void addComponents(IMenu menu);
    List<IMenu> get();
}
