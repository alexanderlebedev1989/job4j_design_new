package ru.job4j_design_new.isp;

import org.junit.Test;
import ru.job4j_design_new.isp.breakfast.Breakfast;
import ru.job4j_design_new.isp.breakfast.PancakeBreakfast;
import ru.job4j_design_new.isp.breakfast.PorridgeBreakfast;
import ru.job4j_design_new.isp.menu.CafeMenu;
import ru.job4j_design_new.isp.menu.IMenu;
import ru.job4j_design_new.isp.service.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CafeMenuTest {
    @Test
    public void whenOnlyRootMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        IMenu menu = new CafeMenu("Меню");
        menu.show();
        assertThat(out.toString(), is("Меню" + System.lineSeparator()));
    }

    @Test
    public void add() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        StringBuilder text = new StringBuilder();
        IMenu menu = new CafeMenu("Меню");
        IMenu breakfast = new Breakfast("Завтрак");
        menu.addComponents(breakfast);
        menu.show();
        assertThat(out.toString(), is(text.append("Меню")
                .append(System.lineSeparator())
                .append(" -> Завтрак")
                .append(System.lineSeparator())
                .toString()));
    }

    @Test
    public void find() {
        IMenu cafeMenu = new CafeMenu("Меню кафе");
        IMenu breakfast = new Breakfast("Завтрак");
        IMenu pancake = new PancakeBreakfast("Блины");
        IMenu porridge = new PorridgeBreakfast("Каши");
        cafeMenu.addComponents(breakfast);
        breakfast.addComponents(pancake);
        breakfast.addComponents(porridge);
        ConsoleOrder orders = new ConsoleOrder();
        IMenu result = orders.find(cafeMenu, "Блины");
        assertThat(result.toString(), is(pancake.toString()));
    }

    @Test
    public void askName() {
        IMenu cafeMenu = new CafeMenu("Меню кафе");
        IMenu breakfast = new Breakfast("Завтрак");
        IMenu pancake = new PancakeBreakfast("Блины");
        IMenu porridge = new PorridgeBreakfast("Каши");
        cafeMenu.addComponents(breakfast);
        breakfast.addComponents(pancake);
        breakfast.addComponents(porridge);

        String inputStr = pancake.toString();
        InputStream in = new ByteArrayInputStream(inputStr.getBytes());
        System.setIn(in);
        Input input = new ConsoleInput();
        String result = input.askName("");
        assertThat(result, is(pancake.toString()));
    }

    @Test
    public void orderFood() {
        IMenu cafeMenu = new CafeMenu("Меню кафе");
        IMenu breakfast = new Breakfast("Завтрак");
        IMenu pancake = new PancakeBreakfast("Блины");
        IMenu porridge = new PorridgeBreakfast("Каши");
        cafeMenu.addComponents(breakfast);
        breakfast.addComponents(pancake);
        breakfast.addComponents(porridge);

        String inputStr = pancake.toString();
        InputStream in = new ByteArrayInputStream(inputStr.getBytes());
        System.setIn(in);
        Input input = new ConsoleInput();
        Order order = new ConsoleOrder();
        boolean result = order.orderFood(cafeMenu, input);
        assertThat(result, is(true));
    }

    @Test
    public void execute() {
        StringBuilder text = new StringBuilder();
        IMenu cafeMenu = new CafeMenu("Меню кафе");
        IMenu breakfast = new Breakfast("Завтрак");
        IMenu pancake = new PancakeBreakfast("Блины");
        IMenu porridge = new PorridgeBreakfast("Каши");
        cafeMenu.addComponents(breakfast);
        breakfast.addComponents(pancake);
        breakfast.addComponents(porridge);

        String inputStr = pancake.toString();
        InputStream in = new ByteArrayInputStream(inputStr.getBytes());
        System.setIn(in);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Input input = new ConsoleInput();
        Order order = new ConsoleOrder();
        Service cafe = new Cafe(cafeMenu, order, input);
        cafe.execute();
        assertThat(out.toString(), is(text.append("Меню кафе")
                .append(System.lineSeparator())
                .append(" -> Завтрак")
                .append(System.lineSeparator())
                .append("Блины")
                .append(System.lineSeparator())
                .append("Каши")
                .append(System.lineSeparator())
                .append(System.lineSeparator())
                .append("Введите название блюда: Ваш заказ принят!")
                .append(System.lineSeparator())
                .toString()
        ));
    }
}