package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.UserProfile.UserProfileActivity;

import java.util.List;

public class EngineersListAdapter extends RecyclerView.Adapter<EngineersViewHolder> {
    private final Context mContext;
    private final List<User> mEngineerList;

    public EngineersListAdapter(Context context, List<User> engineerList) {
        mContext = context;
        mEngineerList = engineerList;
    }

    @NonNull
    @Override
    public EngineersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_engineer,parent,false);
        return new EngineersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EngineersViewHolder holder, int position) {
            User user = mEngineerList.get(position);
            /*TODO: replace static image with url and use glide instead of imageview loading*/
            holder.profilePic.setImageResource(R.drawable.chithlal_photo);
        holder.name.setText(user.getName());
        holder.profession.setText(user.getProfession());
        String expString  = user.getWorks()+" Works";
        holder.experience.setText(expString);
        String feeString = user.getFeePerHour()+"$/Hr";
        holder.fee.setText(feeString);
        holder.mRatingBar.setRating(user.getRating());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, UserProfileActivity.class);
                intent.putExtra("USER",user);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if (mEngineerList!=null)
            return mEngineerList.size();
        else
        return 0;

    }
}
