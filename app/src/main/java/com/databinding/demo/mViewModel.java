package com.databinding.demo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.android.databinding.library.baseAdapters.BR;


/**Observable显著的
 * Created by hzq on 2017/2/20.
 */

public class mViewModel extends BaseObservable{
    private String fistname;
    private String lastname;
    private boolean istrue;
    /**
     * Observable定义的变量注意事项：
     * 1.必须是public修饰的否则外面会找不到
     * 2.自带get、set方法
     * 3.必须实例化才能使用
     */
public ObservableArrayMap<String,String> user=new ObservableArrayMap<>();
    public ObservableInt obint=new ObservableInt();
    public ObservableBoolean obflag =new ObservableBoolean();
    public mViewModel(String fistname, String lastname) {
        this.fistname = fistname;
        this.lastname = lastname;
        user.put("w","778");
        user.put("s","ffff");
        user.put("d","nmbb");
        obflag.set(true);
    }
@Bindable
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
        //和下面的方法效果一样notifyPropertyChanged(BR.lastname);
        notifyChange();
    }
    @Bindable
    public String getFistname() {
        return fistname;

    }
    public void setFistname(String fistname) {
        this.fistname = fistname;
notifyPropertyChanged(BR.fistname);
    }
@Bindable
    public boolean getIstrue() {
        return istrue;
    }
    public void setIstrue(boolean istrue) {
        this.istrue = istrue;
        notifyChange();

    }
}
