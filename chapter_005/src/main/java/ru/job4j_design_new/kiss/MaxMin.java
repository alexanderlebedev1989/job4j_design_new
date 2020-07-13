package ru.job4j_design_new.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public <T> T max(List<T> values, Comparator<T> comparator) {
        return getValue(values, comparator);
    }

    public <T> T min(List<T> values, Comparator<T> comparator) {
        return getValue(values, comparator);
    }

    public <T> T getValue(List<T> values, Comparator<T> comparator) {
        T newValue = null;
        for (T value : values) {
            if (newValue == null) {
                newValue = value;
            } else {
                int sort = comparator.compare(newValue, value);
                if (sort < 0) {
                    newValue = value;
                }
            }
        }
        return newValue;
    }
}

