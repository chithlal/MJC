package com.developer.chithlal.mjc.app.util;

import android.app.Activity;
import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

public class ProgressViewUtil {
    private Context mContext;
    private FragmentManager mFragmentManager;
    private ProgressView mProgressView;
    public ProgressViewUtil(Context context) {
        mContext = context;


    }

    public void showLoading(String message){
        mFragmentManager = ((FragmentActivity)mContext).getSupportFragmentManager();
        mProgressView = new ProgressView(message);
        mProgressView.show(mFragmentManager,"Progress");


    }
    public void showSuccess(String message){
        mProgressView.showSuccess(message);
    }

    public void showFailure(String message){
        mProgressView.showFailure(message);
    }

    public void cancel(){
        mProgressView.dismiss();
    }
}
