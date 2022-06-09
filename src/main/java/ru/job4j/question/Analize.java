package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info info = new Info(0, 0, 0);
        Map<Integer, String> mapUser = new HashMap<>();
        for (User user : current) {
            mapUser.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            if (!mapUser.containsKey(user.getId())) {
                info.setDeleted(info.getDeleted() + 1);
            } else if (!mapUser.get(user.getId()).equals(user.getName())) {
                info.setChanged(info.getChanged() + 1);
            }
            mapUser.put(user.getId(), user.getName());
        }
        info.setAdded(Math.max(0, mapUser.size() - previous.size()));
        return info;
    }
}

