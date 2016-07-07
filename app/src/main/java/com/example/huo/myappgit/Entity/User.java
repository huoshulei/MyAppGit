package com.example.huo.myappgit.Entity;

/**
 * Created by huo on 06/07/16.
 */

public class User {
    // 登录所用的账号
    private String login;
    // 用户名
    private String name;

    private int id;

    // 用户头像路径
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar_url;
    }
}
