package ru.job4j.generic;

public class RoleStore implements Store<Role> {

    private MemStore<Role> ms = new MemStore<>();

    @Override
    public void add(Role model) {
        ms.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return ms.replace(id, model);
    }

    @Override
    public boolean delete(String id) {
        return ms.delete(id);
    }

    @Override
    public Role findById(String id) {
        return ms.findById(id);
    }

    private int indexOf(String id) {
        return indexOf(id);
    }
}
