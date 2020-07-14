package ru.job4j_design_new.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    List<Session> find(Predicate<Session> filter);

    Ticket buy(Account account, int row, int column, Calendar date);

    List<Ticket> selectPlace(Session session);

    void add(Session session);

    void remove(Session session);
}
