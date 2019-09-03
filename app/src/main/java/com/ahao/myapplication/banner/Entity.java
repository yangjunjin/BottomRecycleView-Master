package com.ahao.myapplication.banner;

/**
 * Created by ${YangJunJin}
 * on 2019/8/7
 */
public class Entity {
    private String name;
    boolean open;

    public boolean isOpen() {
        return open;
    }

    public Entity setOpen(boolean open) {
        this.open = open;
        return this;
    }

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
