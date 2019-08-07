package com.ahao.myapplication.banner;

/**
 * Created by ${YangJunJin}
 * on 2019/8/7
 */
public class Entity {
    private String name;

    public Entity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Entity setName(String name) {
        this.name = name;
        return this;
    }
}
