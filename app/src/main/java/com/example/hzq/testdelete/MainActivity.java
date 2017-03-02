package com.example.hzq.testdelete;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.com.greendao.demo.GreenDaoActivity;
import com.databinding.demo.DataDindingActivity;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.recyclerview.demo.RecyclerViewActivity;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private final static String URL = "http://www.imooc.com/api/teacher?type=4&num=30";
    private final static String IMGURL = "http://p1.sinaimg.c7600/180/61131420601459";
    ListView list;
    Button mBut;
    ProgressDialog p;
    Handler h = new Handler();
    RecyclerView recy;
    String TAG = "--------mLOG--------";
    ImageView img;
    File parent = Environment.getExternalStorageDirectory();

    String filename = String.valueOf(IMGURL.substring(IMGURL.lastIndexOf('/')) + "small.jpg");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        list = (ListView) findViewById(R.id.doub_list);
        mBut = (Button) findViewById(R.id.get_button);
        img = (ImageView) findViewById(R.id.imgdo);
        System.out.println("应用所占内存：" + Runtime.getRuntime().maxMemory());
//        async asyncTask=new async();
//        asyncTask.execute(URL);
//mBut.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        p=new ProgressDialog(MainActivity.this);
//        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//        p.setMax(100);
//        p.setIndeterminate(false);
//        p.setCanceledOnTouchOutside(false);
//        p.setMessage("正在获取...");
//        p.show();
//
//        /**
//         * async-http获取数据
//         */
//        final AsyncHttpClient async = new AsyncHttpClient();
//        async.setTimeout(10000);
//
//        async.get(MainActivity.this, URL, new JsonHttpResponseHandler() {
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONObject resp) {
//                super.onSuccess(statusCode, headers, resp);
//                try {
//
//                   p.dismiss();
//
//                    JSONArray mArray = resp.getJSONArray("data");
//                    ArrayList<UserModel> userModels = new ArrayList<UserModel>();
//                    for (int i = 0; i < mArray.length(); i++) {
//                        JSONObject Userobj = mArray.getJSONObject(i);
//                        UserModel u = new UserModel();
//                        u.setName(Userobj.getString("name"));
//                        u.setDescription(Userobj.getString("description"));
//                        userModels.add(u);
//
//                    }
//                    list.setAdapter(new MyAdapter(MainActivity.this,userModels));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onProgress(long bytesWritten, long totalSize) {
//                super.onProgress(bytesWritten, totalSize);
//                final int count = (int) ((bytesWritten * 1.0 / totalSize) / 100);
//
//                try {
//                    Thread.sleep(500);
//                    System.out.println("哈哈哈哈"+count);
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                h.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        p.setProgress(count);//为什那么设置了对话框不动呢？
//                        System.out.println("主线程目前的进度"+p.getProgress());
//                    }
//                });
//                p.setProgress(count);
//               System.out.println("目前的进度"+p.getProgress());
//                if (p.getProgress()==100)
//                    System.out.println("夹在完毕");
//
//
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString,
//                                  Throwable throwable) {
//                super.onFailure(statusCode, headers, responseString, throwable);
//                System.out.println("失败");
//            }
//        });
//
//
//    }
//});
/**
 * volley获取图片，信息，直接执行在主线程
 */
        final RequestQueue mqueen = Volley.newRequestQueue(this);
        StringRequest strReq = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject mJson = new JSONObject(s);
                    JSONArray mJsonArray = mJson.getJSONArray("data");
                    JSONObject mJson1 = mJsonArray.getJSONObject(0);

                    System.out.println(mJson1.getString("picSmall"));

                    mBut.setText(mJson1.getString("name"));
                    ImageRequest imgReq = new ImageRequest(mJson1.getString("picSmall"),
                            new Response.Listener<Bitmap>() {
                                @Override
                                public void onResponse(Bitmap bitmap) {
                                    Bitmap mbit = null;
                                    try {
                                        /**
                                         * 保存图片
                                         */
                                        File file = new File(parent, filename);
                                        FileOutputStream fileOut = new FileOutputStream(file);
                                        BufferedOutputStream buf = new BufferedOutputStream(fileOut);
                                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, buf);
                                        buf.flush();
                                        buf.close();
                                        /**
                                         * 缩放方法一，利用保存成文件的bitmap进行缩放
                                         */
                                        BitmapFactory.Options op = new BitmapFactory.Options();
                                        op.inJustDecodeBounds = true;
                                        BitmapFactory.decodeFile(file.getAbsolutePath(), op);
                                        if (op.outWidth >= 50)
                                            op.inSampleSize = op.outWidth / 50;
                                        System.out.println("第一种宽" + op.outWidth + ":高" + op.outHeight);
                                        op.inJustDecodeBounds = false;
                                        op.inPurgeable = true;
                                        op.inInputShareable = true;
                                        mbit = BitmapFactory.decodeFile(file.getAbsolutePath(), op);
                                        System.out.println("第一种宽" + mbit.getWidth()
                                                + ":高" + mbit.getHeight());
                                        img.setImageBitmap(mbit);
                                        /**
                                         * 缩放方法2
                                         */
                                        int preHeight = 0, preWidth = 0;
                                        float scaleHeight = 0, scaleWidth = 0;
                                        preHeight = bitmap.getHeight();
                                        preWidth = bitmap.getWidth();
                                        //图片缩放尺寸,以宽50为例
                                        scaleWidth = (float) 50 / preWidth;
                                        scaleHeight = (float) (50 * preHeight / preWidth) / preHeight;
                                        Matrix matrix = new Matrix();
                                        matrix.postScale(scaleWidth, scaleHeight);
                                        Bitmap scalebitmap = Bitmap.createBitmap(bitmap, 0, 0
                                                , preWidth, preHeight, matrix, false);
                                        System.out.println("第二种宽" + scalebitmap.getWidth()
                                                + ":高" + scalebitmap.getHeight());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {

                        }
                    });
                    mqueen.add(imgReq);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
////        JsonRequest jsonq=new JsonObjectRequest(URL, null, new Response.Listener<JSONObject>() {
////            @Override
////            public void onResponse(JSONObject jsonObject) {
////                try {
////                    JSONArray mArray=jsonObject.getJSONArray("data");
////                    JSONObject js=mArray.getJSONObject(0);
////
////                    System.out.println(js.getString("name"));
////
////
////                } catch (JSONException e) {
////                    e.printStackTrace();
////                }
////
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError volleyError) {
////
////            }
////        });
        mqueen.add(strReq);

    }

    class async extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            p = new ProgressDialog(MainActivity.this);
            p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            p.setMax(100);
            p.setMessage("正在获取...");

            p.setCanceledOnTouchOutside(false);
            p.show();

        }

        @Override
        protected String doInBackground(String... params) {

            while (p.getProgress() < 100) {
                try {
                    Thread.sleep(150);
                    // 更新进度条的进度,可以在子线程中更新进度条进度
                    p.incrementProgressBy((int) (Math.random() * 5 + 1));
                    // dialog.incrementSecondaryProgressBy(10)//二级进度条更新方式


                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            // 在进度条走完时删除Dialog
            p.dismiss();

            return "大家好";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, s);
//p.cancel();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void next(View v) {
        Intent in = new Intent(this, DataDindingActivity.class);

        startActivity(in);
    }

    public void green(View v) {
        Intent in = new Intent(this, GreenDaoActivity.class);

        startActivity(in);
    }

    public void RecyclerView(View v) {
        startActivity(new Intent(this, RecyclerViewActivity.class));
    }

    /**
     * 版本比较方法测试
     *
     * @param context
     * @param VersionUrl
     */
    public void VersionCompare(final Context context, String VersionUrl) {
        try {
            //获取软件版本号信息
            final int OldVersion = getPackageManager().getPackageInfo(context.getPackageName(), 0)
                    .versionCode;
            AsyncHttpClient async = new AsyncHttpClient();

            async.get(VersionUrl, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject resp) {
                    super.onSuccess(statusCode, headers, resp);
                    try {
                        //获取服务器最新版本号
                        int NowVersion = resp.getInt("versionCode");
                        if (OldVersion < NowVersion)
                            Toast.makeText(context, "当前版本为旧版本", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(context, "当前版本为最新版本", Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    super.onFailure(statusCode, headers, responseString, throwable);
                    Log.d("result", "onFailure: " + responseString);
                }
            });
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

    }
}
