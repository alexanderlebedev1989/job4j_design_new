package ru.job4j_design_new.tdd;

import java.util.List;
import java.util.Stack;
import java.util.function.Predicate;

public class AccountCinema implements Account {

    @Override
    public void add(Ticket ticket) {}

    @Override
    public void remove(Ticket ticket) {}

    @Override
    public List<Ticket> show(Predicate<Ticket> predicate) {
        return null;
    }

    @Override
    public boolean visited(Session session) {
        return false;
    }
}
