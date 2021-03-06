package ru.job4j_design_new.lsp;

import org.junit.Test;
import ru.job4j_design_new.lsp.food.Fish;
import ru.job4j_design_new.lsp.food.Food;
import ru.job4j_design_new.lsp.food.Milk;
import ru.job4j_design_new.lsp.storage.IStorage;
import ru.job4j_design_new.lsp.storage.Shop;
import ru.job4j_design_new.lsp.storage.Trash;
import ru.job4j_design_new.lsp.strategy.ControlQuality;
import ru.job4j_design_new.lsp.strategy.Office;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StorageFoodTest {

    @Test
    public void getTimeDifference() {
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 30);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        int expected = 89;
        int result = milk.getTimeDifference();
        assertThat(result, is(expected));
    }

    @Test
    public void add() {
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 30);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        IStorage storage = new Shop();
        storage.add(milk);
        List<Food> result = storage.clear();
        assertThat(result, is(Arrays.asList(new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0))));
    }

    @Test
    public void accept() {
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 30);
        Milk milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        IStorage storage = new Shop();
        Boolean result = storage.accept(milk);
        assertThat(result, is(true));
    }

    @Test
    public void distribute() {
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality();
        control.add(trash);
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 20);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        control.distribute(milk);
        List<Food> result = trash.clear();
        assertThat(result, is(Arrays.asList(new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0))));
    }

    @Test
    public void executeDistribute() {
        Trash trash = new Trash();
        ControlQuality control = new ControlQuality();
        control.add(trash);
        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 20);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);
        Office office = new Office(control);
        office.executeDistribute(milk);
        List<Food> result = trash.clear();
        assertThat(result, is(Arrays.asList(new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0))));
    }

    @Test
    public void executeResort() {
        IStorage trash = new Trash();
        IStorage shop = new Shop();
        ControlQuality control = new ControlQuality();
        control.add(trash);
        control.add(shop);

        LocalDate createDate = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate = LocalDate.of(2020, 7, 20);
        Food milk = new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0);

        LocalDate createDate1 = LocalDate.of(2020, 7, 1);
        LocalDate expiredDate1 = LocalDate.of(2020, 7, 30);
        Food fish = new Fish("Желтый полосатик", createDate1, expiredDate1, 85.00, 0);

        Office office = new Office(control);
        office.executeDistribute(milk);
        office.executeDistribute(fish);
        office.executeResort();

        List<Food> result = trash.clear();
        assertThat(result, is(Arrays.asList(new Milk("Весёлый молочник", createDate, expiredDate, 55.00, 0))));
        List<Food> result1 = shop.clear();
        assertThat(result1, is(Arrays.asList(new Fish("Желтый полосатик", createDate1, expiredDate1, 85.00, 10))));
    }
}

