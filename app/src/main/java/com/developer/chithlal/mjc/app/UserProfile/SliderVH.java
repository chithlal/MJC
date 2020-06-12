package com.developer.chithlal.mjc.app.UserProfile;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderVH extends RecyclerView.ViewHolder {
    ImageView mImageView;
    CardView mCardView;
    TextView mTextView;
    public SliderVH(View itemView) {
        super(itemView);
        mCardView = itemView.findViewById(R.id.cv_card_slider);
        mImageView = itemView.findViewById(R.id.iv_card_slider);
        mTextView = itemView.findViewById(R.id.tv_slider_image_desc);
    }
}
