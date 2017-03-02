package com.com.greendao.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.example.hzq.testdelete.R;
import com.example.hzq.testdelete.databinding.GreendaoActivityBinding;
import com.greendao.demo.DaoMaster;
import com.greendao.demo.DaoSession;
import com.greendao.demo.FatherDao;
import com.greendao.demo.SonDao;

import java.util.List;

/**
 * Created by hzq on 2017/2/23.
 */

public class GreenDaoActivity extends Activity {
    GreendaoActivityBinding gd;
    DaoMaster master;
    DaoSession session;
    SQLiteDatabase db;
    listAdapter adapter;
    SonDao son;
    List<Son> list;
    FatherDao father;
    Son sons = new Son();
    Long fatherid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gd = DataBindingUtil.setContentView(this, R.layout.greendao_activity);
        init();
        sons.setFatherId(fatherid);
        gd.setSon(sons);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_insert:
                insert();

                break;
            case R.id.bt_delete:
                delete();
                break;
            case R.id.bt_update:
                update();
                break;
            case R.id.bt_select:
                select();
                break;


        }
    }

    private void select() {
    }

    private void update() {
    }

    private void delete() {

    }

    private void insert() {
        sons = new Son();
        sons.setName(gd.btName.getText().toString());
        sons.setAge(new Integer(gd.btAge.getText().toString()));
        sons.setFatherId(fatherid);
        son.insertOrReplace(sons);
        list.add(sons);
        adapter.notifyDataSetChanged();
        gd.setSon(sons);
    }

    private void init() {
        db = new DaoMaster.DevOpenHelper(this, "person.db", null).getWritableDatabase();
        master = new DaoMaster(db);
        session = master.newSession();
        son = session.getSonDao();
        father = session.getFatherDao();
        fatherid = father.insertOrReplace(new Father("爸爸", 45));
        list = son.queryBuilder().list();
        adapter = new listAdapter(this, list);

        gd.list.setAdapter(adapter);
        gd.list.setOnItemLongClickListener(new onLongClickListener());
    }

    private class onLongClickListener implements android.widget.AdapterView.OnItemLongClickListener {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            AlertDialog.Builder dialog=new AlertDialog.Builder(GreenDaoActivity.this);
            dialog.setMessage("是否删除？");
            dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    son.delete(son.queryBuilder().where(SonDao.Properties.Id.eq(list.get(position).getId())).unique());
                list.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            dialog.setNegativeButton("否", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.create().show();
            return false;
        }
    }
}
