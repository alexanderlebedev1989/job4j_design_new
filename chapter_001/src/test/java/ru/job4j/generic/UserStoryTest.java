package ru.job4j.generic;

import org.junit.Test;
import java.util.List;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoryTest {

    @Test
    public void add() {
        UserStore us = new UserStore();
        List<User> users = List.of(new User("1"), new User("2"), new User("3"));
        User user = new User("4");
        us.add(user);
        User result = us.findById("4");
        assertThat(result, is(user));
    }

    @Test
    public void replace() {
        UserStore us = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        us.add(user1);
        us.add(user2);
        User user3 = new User("5");
        us.replace("1", user3);
        User result = us.findById("5");
        assertThat(result, is(user3));
    }

    @Test
    public void delete() {
        UserStore us = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        us.add(user1);
        us.add(user2);
        boolean result = us.delete("1");
        assertThat(result, is(true));
    }


    @Test
    public void findById() {
        UserStore us = new UserStore();
        User user1 = new User("1");
        User user2 = new User("2");
        us.add(user1);
        us.add(user2);
        User result = us.findById("1");
        assertThat(result, is(user1));
    }
}
