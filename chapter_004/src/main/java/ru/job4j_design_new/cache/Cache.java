package ru.job4j_design_new.cache;

public interface Cache<T> {

     T getCache(String key);

}
