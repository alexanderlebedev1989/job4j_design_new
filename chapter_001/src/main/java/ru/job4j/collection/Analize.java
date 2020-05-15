package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        int deleted = 0;
        int changed = 0;
        Map<Integer, User> currMap = new HashMap<>();
        for (User user : current) {
            currMap.put(user.getId(), user);
        }
        for (User u : previous) {
            deleted += (!currMap.containsKey(u.getId()) ? 1 : 0);
            changed += (currMap.containsKey(u.getId())
                    && !u.getName().equals(currMap.get(u.getId()).getName()) ? 1 : 0);
        }
        info.setDeleted(deleted);
        info.setAdded(current.size() - (previous.size() - deleted));
        info.setChanged(changed);
        return info;
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            if (added != info.added) return false;
            if (changed != info.changed) return false;
            return deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            int result = added;
            result = 31 * result + changed;
            result = 31 * result + deleted;
            return result;
        }

        @Override
        public String toString() {
            return "Info{" + "added="
                    + added + ", changed="
                    + changed + ", deleted="
                    + deleted + '}';
        }
    }
}
