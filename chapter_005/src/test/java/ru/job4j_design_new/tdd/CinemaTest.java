package ru.job4j_design_new.tdd;

import org.junit.Test;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CinemaTest {
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Test
    public void remove() {
        Cinema cinema = new Cinema3D();
        Session s = new Session3D();
        cinema.add(s);
        cinema.remove(s);
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions.size(), is(0));

    }

    @Test
    public void whenBuyTicketThenAddAccount() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        account.add(ticket);
        List<Ticket> t = account.show(ticket1 -> true);
        assertThat(t, is(Arrays.asList(new Ticket3D())));
    }

    @Test
    public void  visited() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Session s = new Session3D();
        cinema.add(s);
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        account.add(ticket);
        List<Ticket> t = account.show(ticket1 -> true);
        Boolean result = account.visited(s);
        assertThat(result, is(true));
    }

    @Test
    public void  whenRemoveTicketsThenListIsEmpty() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Session s = new Session3D();
        cinema.add(s);
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        account.add(ticket);
        List<Ticket> t = account.show(ticket1 -> true);
        account.remove(ticket);
        assertThat(t.size(), is(0));
    }

    @Test
    public void selectPlace() {
        Cinema cinema = new Cinema3D();
        Session s = new Session3D();
        cinema.add(s);
        List<Ticket> result = cinema.selectPlace(s);
        assertThat(result, is(Arrays.asList(new Ticket3D())));
    }
}
