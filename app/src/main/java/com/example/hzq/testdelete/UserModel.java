package com.example.hzq.testdelete;

import java.io.Serializable;

/**
 * Created by hzq on 2017/2/14.
 */

public class UserModel implements Serializable {
private String name;
    private String picSmall;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicSmall() {
        return picSmall;
    }

    public void setPicSmall(String picSmall) {
        this.picSmall = picSmall;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
