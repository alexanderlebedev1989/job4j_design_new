package ru.job4j_design_new.isp;

import ru.job4j_design_new.isp.breakfast.Breakfast;
import ru.job4j_design_new.isp.breakfast.PancakeBreakfast;
import ru.job4j_design_new.isp.breakfast.PorridgeBreakfast;
import ru.job4j_design_new.isp.dinner.Dinner;
import ru.job4j_design_new.isp.dinner.MeatDinner;
import ru.job4j_design_new.isp.dinner.SaladDinner;
import ru.job4j_design_new.isp.menu.CafeMenu;
import ru.job4j_design_new.isp.menu.IMenu;
import ru.job4j_design_new.isp.service.IService;
import ru.job4j_design_new.isp.service.ServiceOrders;

public class Program {
    public static void main(String[] args) {
        IMenu cafeMenu = new CafeMenu("Меню кафе");

        IMenu breakfast = new Breakfast("Завтрак");
        IMenu pancake = new PancakeBreakfast("Блины");
        IMenu porridge = new PorridgeBreakfast("Каши");

        IMenu dinner = new Dinner("Ужин");
        IMenu meat = new MeatDinner("Запеченное мясо");
        IMenu salad = new SaladDinner("Овощной салат");

        cafeMenu.addComponents(breakfast);
        cafeMenu.addComponents(dinner);

        breakfast.addComponents(pancake);
        breakfast.addComponents(porridge);

        dinner.addComponents(meat);
        dinner.addComponents(salad);

        IService service = new ServiceOrders(cafeMenu);
        service.orderFood();
    }
}
