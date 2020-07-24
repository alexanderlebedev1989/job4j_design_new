package ru.job4j_design_new.isp.service;

import ru.job4j_design_new.isp.menu.IMenu;

public class Cafe implements Service {
    private IMenu menu;
    private Order order;
    private Input input;

    public Cafe(IMenu menu, Order order, Input input) {
        this.menu = menu;
        this.order = order;
        this.input = input;
    }

    @Override
    public void execute() {
        boolean accept = true;
        while (accept) {
            menu.show();
            System.out.println();
            Boolean result = order.orderFood(menu, input);
            if (result) {
                System.out.println("Ваш заказ принят!");
                accept = false;
            } else {
                System.out.println("Ваш заказ не принят, попробуйте снова");
                System.out.println();
            }
        }
    }
}
