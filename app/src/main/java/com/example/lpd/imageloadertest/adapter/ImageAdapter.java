package com.example.lpd.imageloadertest.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.lpd.imageloadertest.ImageLoader;
import com.example.lpd.imageloadertest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LPD on 2018/4/10.
 */

public class ImageAdapter extends BaseAdapter {
    public static class A{
        public A(){

        }
    }

    private List<String> mUriList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Drawable mDefaultBitmapDrawable;
    private boolean mIsGridViewIdle;
    private boolean mCanGetBitmapFromNetWork;
    private int mImageWidth;
    private ImageLoader mImageLoader;

    public ImageAdapter(List<String> uris, Context context){
        mUriList = uris;
        mInflater = LayoutInflater.from(context);
        mDefaultBitmapDrawable = context.getDrawable(R.drawable.image_default);
        mImageLoader = ImageLoader.build(context);
    }

    @Override
    public int getCount() {
        return mUriList.size();
    }

    @Override
    public String getItem(int i) {
        return mUriList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if(view == null){
            view = mInflater.inflate(R.layout.image_gridview_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = view.findViewById(R.id.image);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        ImageView imageView = holder.imageView;
        final String tag = (String) imageView.getTag();
        final String uri = getItem(position);
        if(!uri.equals(tag)){
            imageView.setImageDrawable(mDefaultBitmapDrawable);
        }
        if(mIsGridViewIdle && mCanGetBitmapFromNetWork){
            imageView.setTag(uri);
            mImageLoader.bindBitmap(uri, imageView, mImageWidth, mImageWidth);
        }
        return view;
    }

    public void setmIsGridViewIdle(boolean mIsGridViewIdle) {
        this.mIsGridViewIdle = mIsGridViewIdle;
    }

    public void setmCanGetBitmapFromNetWork(boolean mCanGetBitmapFromNetWork) {
        this.mCanGetBitmapFromNetWork = mCanGetBitmapFromNetWork;
    }

    public void setmImageWidth(int mImageWidth) {
        this.mImageWidth = mImageWidth;
    }

    private class ViewHolder {
        public ImageView imageView;
    }
}
