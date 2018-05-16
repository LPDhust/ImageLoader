package com.example.lpd.imageloadertest;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.AbsListView;
import android.widget.GridView;

import com.example.lpd.imageloadertest.adapter.ImageAdapter;
import com.example.lpd.imageloadertest.animations.Rotate3dAnimation;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GridView mImageGridView;
    private ImageAdapter mImageAdapter;
    private boolean mIsWifi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageGridView = (GridView) findViewById(R.id.image_gridview);
        mImageGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    mImageAdapter.setmIsGridViewIdle(true);
                    mImageAdapter.notifyDataSetChanged();
                }else {
                    mImageAdapter.setmIsGridViewIdle(false);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });
        ArrayList<String> uris = new ArrayList<>();
        uris.add("");
        uris.add("");
        uris.add("");
        mImageAdapter = new ImageAdapter(uris, this);
        mImageGridView.setAdapter(mImageAdapter);
        initIsWifi();
//        Animation animation = new Rotate3dAnimation(0, 180, mImageGridView.getPivotX(), mImageGridView.getPivotY(), 200, true);
//        mImageGridView.startAnimation(animation);
    }

    @Override
    protected void onResume(){
        super.onResume();
        showNetworkDialog();
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(mImageGridView, "backgroundColor", 0xFFFF8080, 0xFF8080FF);
        valueAnimator.setDuration(3000);
        valueAnimator.setEvaluator(new ArgbEvaluator());
        valueAnimator.setRepeatMode(ValueAnimator.REVERSE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.start();
//        Animation animation = new Rotate3dAnimation(0, 180, mImageGridView.getPivotX(), mImageGridView.getPivotY(), 200, true);
//        mImageGridView.startAnimation(animation);
    }

    private void initIsWifi(){
        WifiManager wifiManager = (WifiManager)this.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(wifiManager != null){
            if(wifiManager.getWifiState() == WifiManager.WIFI_STATE_ENABLED){
                mIsWifi = true;
            }else{
                mIsWifi = false;
            }
        }
    }

    private void showNetworkDialog(){
        if (!mIsWifi){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("初次使用会从网络中下载大概5MB的图片， 确认下载吗？");
            builder.setTitle("注意");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mImageAdapter.setmCanGetBitmapFromNetWork(true);
                    mImageAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("否", null);
            builder.show();
        }
    }
}
