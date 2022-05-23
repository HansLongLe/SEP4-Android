package com.example.sep4_android.model;

import java.util.HashMap;

public class UserList {

    public HashMap<String, User> users;

    public UserList() {}

    public UserList(HashMap<String, User> users) {
        this.users = users;
    }

    public boolean containsKey(String key) {
        return users.containsKey(key);
    }

    public User get(String key) {
        return users.get(key);
    }

    public int size() {
        return users.size();
    }
}
