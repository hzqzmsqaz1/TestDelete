package com.com.greendao.demo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by hzq on 2017/2/23.
 */
@Entity
public class Father {
    private String name;
    @Id(autoincrement = true)
    private Long id;
    private int age;

    public Father(String name, int age) {
        this.name = name;
        this.age = age;
    }



    @Generated(hash = 1574469576)
    public Father(String name, Long id, int age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }



    @Generated(hash = 383274692)
    public Father() {
    }



    @Override
    public String toString() {
        return "Father{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                '}';
    }



    public String getName() {
        return this.name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public Long getId() {
        return this.id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public int getAge() {
        return this.age;
    }



    public void setAge(int age) {
        this.age = age;
    }
}
