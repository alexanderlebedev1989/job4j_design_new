package ru.job4j_design_new.lsp.food;

import java.time.LocalDate;

public class Milk extends Food {

    public Milk(String name, LocalDate createDate, LocalDate expireDate, double price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
