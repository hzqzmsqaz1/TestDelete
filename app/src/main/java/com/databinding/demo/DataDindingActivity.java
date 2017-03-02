package com.databinding.demo;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.hzq.testdelete.R;
import com.example.hzq.testdelete.databinding.TextDemoBinding;


/**
 * Created by hzq on 2017/2/20.
 */

public class DataDindingActivity extends Activity {
    TextDemoBinding textbing;
    mViewModel model = new mViewModel("爸爸", "jj");
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        textbing = DataBindingUtil.setContentView(this, R.layout.text_demo);

/**
 *在xml中绑定数据需要set数据
 * 这是通用写法：textbing.setVariable(BR.viewmodel,model);
 */
        textbing.setViewmodel(model);
        textbing.setListener(new Listener());
        textbing.setIndex(45678);
    }

    public class Listener {
        /**
         * 方法引用,监听事件方法参数必须和原方法的参数一致（方法名在引用时会自动一致），否则编译报错,
         * 和从xml中写onClick方法原理一样
         *
         * @param c
         * @param start
         * @param before
         * @param count
         */
        public void onTextChangedOne(CharSequence c, int start, int before, int count) {
            flag = !flag;
            model.setFistname(c.toString());
            model.setIstrue(flag);
            model.obflag.set(!model.obflag.get());
//            如果在viewmodel中使用notifyChange则不需要在这里更新
//            textbing.setViewmodel(model);
        }

        public void onClicked(View v) {

            Toast.makeText(DataDindingActivity.this, "打死你", Toast.LENGTH_LONG).show();
        }

        /**
         * 监听器法，较方法引用而言格式要求简单，但是书写较为麻烦
         */
        public void onTextChangedTwo() {

            model.setLastname(textbing.lastname.getText().toString());
//            textbing.setViewmodel(model);
        }


    }
}
