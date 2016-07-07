package com.example.huo.myappgit.Entity;

/**
 * Created by huo on 06/07/16.
 */

public class CurrentUser {
    public CurrentUser() {
    }

    private static String token;
    private static User   user;

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        CurrentUser.token = token;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        CurrentUser.user = user;
    }

    public static void clear() {
        user = null;
        token = null;
    }

    public static boolean hasAccessToken() {
        return token != null;
    }

    public static boolean isEmpty() {
        return token == null || user == null;
    }
}
