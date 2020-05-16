package ru.job4j.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        int countChanged = 0;
        int countDelOrAdd = 0;
        Map<Integer, User> map = new HashMap<>();
        for (User pUser : previous) {
            map.put(pUser.getId(), pUser);
        }
        for (User cUser : current) {
            if (map.containsKey(cUser.getId())) {
                countDelOrAdd++;
                User user = map.get(cUser.getId());
                if (!user.getName().equals(cUser.getName())) {
                    countChanged++;
                }
            }
        }
        info.setDeleted(previous.size() - countDelOrAdd);
        info.setAdded(current.size() - countDelOrAdd);
        info.setChanged(countChanged);
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
            if (this == o) {
                return true;
            }

            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;

            if (added != info.added) {
                return false;
            }


            if (changed != info.changed) {
                return false;
            }
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
