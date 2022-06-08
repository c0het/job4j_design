package ru.job4j.question;

import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        for (User c : current) {
            if (!previous.contains(c)) {
                if (previous.stream().anyMatch(p -> c.getId() == p.getId())) {
                    info.setChanged(info.getChanged() + 1);
                } else {
                    info.setAdded(info.getAdded() + 1);
                }
            }
        }
        info.setDeleted(previous.size() - (current.size() - info.getAdded()));
        return info;
    }
}
