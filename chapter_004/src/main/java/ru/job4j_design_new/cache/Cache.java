package ru.job4j_design_new.cache;

public interface Cache<T> {

     void addCache(String key);
     T getCache(String key);

}
