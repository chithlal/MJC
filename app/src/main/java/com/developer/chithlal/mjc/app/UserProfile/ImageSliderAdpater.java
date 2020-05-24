package com.developer.chithlal.mjc.app.UserProfile;

import static com.developer.chithlal.mjc.app.UserProfile.ImageSliderAdpater.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ImageSliderAdpater extends RecyclerView.Adapter<SliderVH> {
    private Context context;

    private List<SliderItem> mSliderItems;

    public ImageSliderAdpater(Context context,
            List<SliderItem> sliderItems) {
        this.context = context;
        mSliderItems = sliderItems;
    }

    public void renewItems(List<SliderItem> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SliderItem sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public SliderVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_image_slider,parent ,false);
        return new SliderVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderVH viewHolder, int position) {
        SliderItem sliderItem = mSliderItems.get(position);

        if (mSliderItems.size()==0){
            viewHolder.mImageView.setImageResource(R.drawable.ic_image_black_24dp);
            viewHolder.mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        else
        Glide.with(viewHolder.itemView)
                .load(sliderItem.getImageUrl())
                .fitCenter()
                .placeholder(R.drawable.ic_image_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp )
                .into(viewHolder.mImageView);

    }

    @Override
    public int getItemCount() {

        return mSliderItems.size();
    }



}
