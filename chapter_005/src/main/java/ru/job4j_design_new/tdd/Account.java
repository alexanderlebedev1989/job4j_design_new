package ru.job4j_design_new.tdd;

import java.util.List;
import java.util.function.Predicate;

public interface Account {

    void add(Ticket ticket);

    void remove(Ticket ticket);

    List<Ticket> show(Predicate<Ticket> predicate);

    boolean visited(Session session);
}
