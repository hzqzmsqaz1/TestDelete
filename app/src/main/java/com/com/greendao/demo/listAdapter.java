package com.com.greendao.demo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hzq.testdelete.R;

import java.util.List;

/**
 * Created by hzq on 2017/2/24.
 */

public class listAdapter extends BaseAdapter {
    Context context;
    List<Son> son;
    public listAdapter(Context context, List<Son> son){
       this.context=context;
        this.son=son;
    }
    @Override
    public int getCount() {
        return son.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder h=null;
        if (convertView==null){
            h=new viewHolder();
            convertView=View.inflate(context, R.layout.greendao_list_item,null);
            h.id= (TextView) convertView.findViewById(R.id.txt_id);
            h.name= (TextView) convertView.findViewById(R.id.txt_name);
            h.age= (TextView) convertView.findViewById(R.id.txt_age);
            convertView.setTag(h);
        }
        h= (viewHolder) convertView.getTag();
        h.id.setText(son.get(position).getId().toString());
        h.name.setText(son.get(position).getName());
        h.age.setText(""+son.get(position).getAge());
        return convertView;
    }
    public class viewHolder{
        TextView id,name,age;
    }
}
