package ru.job4j.log;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        char c = 'k';
        byte day = 14;
        short month  = 1;
        int age = 15;
        long year = 2005;
        double weight = 115.2;
        float height = 175.4f;
        boolean result = true;

        LOG.debug("User info c : {}, day : {}, month : {}, age : {}, year : {}, weight : {}, " +
                "height : {}, result : {}", c, day, month, age, year, weight, height, result);
    }
}

