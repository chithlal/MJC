package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ImageUploderAdapter extends RecyclerView.Adapter<ImageUploderAdapter.ImageViewHolder> {
    Context mContext;
    private AddButtonClickListener mAddButtonClickListener;
    List<Uri> imageList = new ArrayList<>();

    public ImageUploderAdapter(Context context,AddButtonClickListener addButtonClickListener) {
        mContext = context;
        mAddButtonClickListener = addButtonClickListener;
        imageList.add(Constants.ADD_IMAGE_BUTTON_URI);
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_add_photo,parent,false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        if (imageList.get(position)!=null){
            Glide.with(mContext)
                    .load(imageList.get(position))
                    .centerCrop()
                    .into(holder.mImageView);
           
        }

        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageList.get(position)==null){
                    mAddButtonClickListener.onAddButtonClick();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView ;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_add_photo);
        }
    }

    public void addImage(Uri image){
        if (imageList!=null)
        {
            imageList.add(imageList.size()-1,image);
        }
        notifyDataSetChanged();
    }public interface AddButtonClickListener{
        void onAddButtonClick();
    }
}
