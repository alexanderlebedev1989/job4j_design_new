package ru.job4j_design_new.lsp.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food {

    private String name;
    private LocalDate createDate;
    private LocalDate expireDate;
    private double price;
    private int percentDiscount;

    public Food(String name, LocalDate createDate, LocalDate expireDate, double price, int percentDiscount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        this.price = price;
        this.percentDiscount = percentDiscount;
    }

    public String getName() {
        return name;
    }

    public void setDiscount(int percentDiscount) {
        this.percentDiscount = percentDiscount;
    }

    public int getTimeDifference() {
        LocalDate now = LocalDate.now();
        double currentDiff = ChronoUnit.DAYS.between(createDate, now);
        double diff = ChronoUnit.DAYS.between(createDate, expireDate);
        return (int) (currentDiff / diff * 100);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (Double.compare(food.price, price) != 0) return false;
        if (percentDiscount != food.percentDiscount) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (createDate != null ? !createDate.equals(food.createDate) : food.createDate != null) return false;
        return expireDate != null ? expireDate.equals(food.expireDate) : food.expireDate == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (expireDate != null ? expireDate.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + percentDiscount;
        return result;
    }
}
