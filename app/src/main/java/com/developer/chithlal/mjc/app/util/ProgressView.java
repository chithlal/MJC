package com.developer.chithlal.mjc.app.util;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.databinding.DialogViewBinding;

public class ProgressView extends DialogFragment {

    DialogViewBinding mBinding;
    private String mMessage;

    public ProgressView(String message) {
        super();
        mMessage = message;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
         super.onCreateView(inflater, container, savedInstanceState);

         mBinding = DialogViewBinding.inflate(getLayoutInflater());

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        mBinding.dialogAnimation.setAnimation(R.raw.simple_loading);
        mBinding.dialogMessage.setText(mMessage);
        setCancelable(false);

    }
    void showSuccess(String message){
        mBinding.dialogAnimation.setAnimation(R.raw.succes);
        mBinding.dialogMessage.setText(message);
    }

    void showFailure(String message){
        mBinding.dialogAnimation.setAnimation(R.raw.error);
        mBinding.dialogMessage.setText(message);
    }
}
