package com.recyclerview.demo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hzq.testdelete.R;
import com.example.hzq.testdelete.databinding.RecyclerActivityBinding;

import java.util.ArrayList;

/**
 * Created by hzq on 2017/2/21.
 */

public class RecyclerViewActivity extends Activity {
    RecyclerActivityBinding recbing;
    ArrayList<String> data;
    HomeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recbing = DataBindingUtil.setContentView(this, R.layout.recycler_activity);
        init();
    }

    private void init() {
        data=new ArrayList<>();
        int j=0;
        for (char i='a';i<'z';i++){
            data.add(String.valueOf(i));
            System.out.println(data.get(j++));
        }
        recbing.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new HomeAdapter();
recbing.recyclerView.setAdapter(adapter);
    }
class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.myViewHolder>{


    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         * 使用View.inflate(RecyclerViewActivity.this,R.layout.recycler_item_one,null);获得view则会
         * 使item不居中，因为没有parent，但是这个方法不能把null换成parent
         *
         */

        myViewHolder holder=new myViewHolder(LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.recycler_item_one,parent,false));

        return holder;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
holder.t.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder{
TextView t;
        public myViewHolder(View itemView) {
            super(itemView);
           t= (TextView) itemView.findViewById(R.id.txt_recycler);
        }
    }
}

}
