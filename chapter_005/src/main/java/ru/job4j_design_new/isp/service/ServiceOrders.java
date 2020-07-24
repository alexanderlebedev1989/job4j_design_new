package ru.job4j_design_new.isp.service;

import ru.job4j_design_new.isp.menu.IMenu;
import java.util.Scanner;

public class ServiceOrders implements IService {

    private Scanner scanner = new Scanner(System.in);
    private IMenu menu;

    public ServiceOrders(IMenu menu) {
        this.menu = menu;
    }

    @Override
    public void orderFood() {
        boolean ready = true;
        while (ready) {
            menu.show();
            System.out.println();
            System.out.print("Введите название блюда: ");
            String foodName = scanner.nextLine();
            IMenu food = find(foodName);
            if (food != null) {
                System.out.println("Ваш заказ принят");
                ready = false;
            } else {
                System.out.println("Заказ не принят, повторите снова");
                System.out.println();
            }
        }
    }

    public IMenu find(String name) {
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
