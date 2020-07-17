package ru.job4j_design_new.lsp;

import org.junit.Test;
import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.food.Milk;
import ru.job4j_design_new.lsp.storage.IStorage;
import ru.job4j_design_new.lsp.storage.Shop;
import ru.job4j_design_new.lsp.strategy.ControlQuality;
import ru.job4j_design_new.lsp.strategy.Office;
import ru.job4j_design_new.lsp.strategy.Strategy;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StorageFoodTest {

    @Test
    public void getTimeDifference() {
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 20);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        int expected = 84;

        int result = milk.getTimeDifference();
        assertThat(result, is(expected));
    }

    @Test
    public void add() {
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 20);
        Milk milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);

        Shop shop = new Shop();
        shop.add(milk);

        Milk result = (Milk) shop.getFood("Весёлый молочник");
        assertThat(result, is(new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0)));
    }

    @Test
    public void execute() {
        Office office = new Office(new ControlQuality());

        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 20);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        int percentUsed = milk.getTimeDifference();

        IStorage storage = office.execute(milk, percentUsed, 10);

        Milk result = (Milk) storage.getFood("Весёлый молочник");
        assertThat(result, is(new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 10)));
    }
}

