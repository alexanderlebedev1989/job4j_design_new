package ru.job4j.collection;

import org.junit.Test;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalizeTest {

    @Test
    public void whenAddedUserShouldBeNewStatistics() {
        Analize analize = new Analize();
        List<User> previous = List.of(new User(1, "Bob"),
                new User(2, "Tom"));
        List<User> current = List.of(new User(1, "Bob"),
                new User(2, "Tom"), new User(3, "Mike"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 0, 0);
        assertThat(result, is(expected));
    }

    @Test
    public void whenDeletedUserShouldBeNewStatistics() {
        Analize analize = new Analize();
        List<User> previous = List.of(new User(1, "Bob"), new User(2, "Tom"));
        List<User> current = List.of(new User(1, "Bob"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 0, 1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenChangedUserShouldBeNewStatistics() {
        Analize analize = new Analize();
        List<User> previous = List.of(new User(1, "Bob"), new User(2, "Tom"));
        List<User> current = List.of(new User(1, "Bob"), new User(2, "Pit"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(0, 1, 0);
        assertThat(result, is(expected));
    }

    @Test
    public void whenComboUserShouldBeNewStatistics() {
        Analize analize = new Analize();
        List<User> previous = List.of(new User(1, "Bob"),
                new User(2, "Tom"),
                new User(3, "Pit"),
                new User(4, "Lora"));
        List<User> current = List.of(new User(1, "Bob"),
                new User(2, "Tom"),
                new User(4, "Anna"),
                new User(5, "Ivan"));
        Analize.Info result = analize.diff(previous, current);
        Analize.Info expected = new Analize.Info(1, 1, 1);
        assertThat(result, is(expected));
    }

}
