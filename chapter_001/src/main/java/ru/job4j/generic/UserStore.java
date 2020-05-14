package ru.job4j.generic;

public class UserStore implements Store<User> {

    private MemStore<User> ms = new MemStore<>();

    @Override
    public void add(User model) {
        ms.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
       return ms.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return ms.delete(id);
    }

    @Override
    public User findById(String id) {
        return ms.findById(id);
    }

    private int indexOf(String id) {
        return indexOf(id);
    }
}
