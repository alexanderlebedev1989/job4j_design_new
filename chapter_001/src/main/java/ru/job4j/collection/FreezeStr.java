package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        int count = 1;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < left.length(); i++) {
            char iLeft = left.charAt(i);
            if (map.containsKey(iLeft)) {
                map.put(iLeft, map.get(iLeft) + count);
            } else {
                map.put(iLeft, count);
            }
        }
        for (int i = 0; i < right.length(); i++) {
            char iRight = right.charAt(i);
            if (!map.containsKey(iRight)) {
                return false;
            }
            map.put(iRight, map.get(iRight) - 1);
            if (map.get(iRight) < 0) {
                 return false;
            }
        }
        return true;
    }
}


