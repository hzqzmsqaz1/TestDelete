package com.com.greendao.demo;

import com.greendao.demo.DaoSession;
import com.greendao.demo.FatherDao;
import com.greendao.demo.SonDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by hzq on 2017/2/23.
 */
@Entity(

        //  实体是否激活的标志，激活的实体有更新，删除和刷新的方法
        active = true,

        // 确定数据库中表的名称
        // 表名称默认是实体类的名称
        nameInDb = "AWESOME_USERS",

        // Define indexes spanning multiple columns here.
        indexes = {
                @Index(value = "name DESC", unique = true)
        },

        // DAO是否应该创建数据库表的标志(默认为true)
        // 如果你有多对一的表，将这个字段设置为false
        // 或者你已经在GreenDAO之外创建了表，也将其置为false
        createInDb = true
)

public class Son{
    private String name;
    private int age;
    /**id必须用long类型，且必须用long的封装类型Long*/
    @Id(autoincrement = true)
    private Long id;
    private String state;
    private Long fatherId;
    @ToOne(joinProperty = "fatherId")
    private Father father;
/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;
/** Used for active entity operations. */
@Generated(hash = 1926509084)
private transient SonDao myDao;
@Generated(hash = 2100996716)
private transient Long father__resolvedKey;


    public Son(String name, int age) {
        this.name = name;
        this.age = age;

    }
    public Son() {
    }
@Generated(hash = 56382991)
public Son(String name, int age, Long id, String state, Long fatherId) {
    this.name = name;
    this.age = age;
    this.id = id;
    this.state = state;
    this.fatherId = fatherId;
}



    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", fatherId=" + fatherId +
                '}';
    }
public String getName() {
    return this.name;
}
public void setName(String name) {

    this.name = name;
}
public int getAge() {
    return this.age;
}
public void setAge(int age) {
    this.age = age;
}
public Long getId() {
    return this.id;
}
public void setId(Long id) {
    this.id = id;
}
public String getState() {
    return this.state;
}
public void setState(String state) {
    this.state = state;
}
public Long getFatherId() {
    return this.fatherId;
}
public void setFatherId(Long fatherId) {
    this.fatherId = fatherId;
}
/** To-one relationship, resolved on first access. */
@Generated(hash = 1240399403)
public Father getFather() {
    Long __key = this.fatherId;
    if (father__resolvedKey == null || !father__resolvedKey.equals(__key)) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        FatherDao targetDao = daoSession.getFatherDao();
        Father fatherNew = targetDao.load(__key);
        synchronized (this) {
            father = fatherNew;
            father__resolvedKey = __key;
        }
    }
    return father;
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 2144005895)
public void setFather(Father father) {
    synchronized (this) {
        this.father = father;
        fatherId = father == null ? null : father.getId();
        father__resolvedKey = fatherId;
    }
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 128553479)
public void delete() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.delete(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 1942392019)
public void refresh() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.refresh(this);
}
/**
 * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
 * Entity must attached to an entity context.
 */
@Generated(hash = 713229351)
public void update() {
    if (myDao == null) {
        throw new DaoException("Entity is detached from DAO context");
    }
    myDao.update(this);
}
/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 838735897)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getSonDao() : null;
}
}
