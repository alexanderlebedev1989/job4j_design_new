package ru.job4j_design_new.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {
    @Override
    public List<Session> find(Predicate<Session> filter) {
        return null;
    }

    @Override
    public Ticket buy(Account account, int row, int column, Calendar date) {
        return null;
    }

    @Override
    public List<Ticket> selectPlace(Session session) {
        return null;
    }

    @Override
    public void add(Session session) {}

    @Override
    public void remove(Session session) {}
}
