package com.example.hzq.testdelete;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hzq on 2017/2/14.
 */

public class MyAdapter extends BaseAdapter {
    ArrayList<UserModel> mlist;
    Context context;
    public MyAdapter(Context context,ArrayList<UserModel> list) {
        this.context=context;
        mlist=list;
    }

    @Override
    public int getCount() {
        return mlist.size();
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
        viewholder h=null;
        if (convertView==null){
            h=new viewholder();
            convertView=View.inflate(context,R.layout.list_item,null);
            h.title= (TextView) convertView.findViewById(R.id.title);
            h.content= (TextView) convertView.findViewById(R.id.content);
            h.title.setText(mlist.get(position).getName());
            convertView.setTag(h);
        }
        h= (viewholder) convertView.getTag();
        h.title.setText(mlist.get(position).getName());
        h.content.setText(mlist.get(position).getDescription());
        h.title.setSelected(true);
        h.content.setSelected(true);
        return convertView;
    }
    class viewholder{
        ImageView img;
        TextView title,content;
    }

}
