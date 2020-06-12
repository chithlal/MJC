package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.UserProfile.UserProfileActivity;
import com.developer.chithlal.mjc.app.util.Constants.*;

import java.util.List;

public class EngineersListAdapter extends RecyclerView.Adapter<EngineersViewHolder> {
    private final Context mContext;
    private final List<User> mEngineerList;
    private int lastPosition = 0;
    public static final String RATE_PER_SQRFT_STRING = "₹";


    private HireEngineer.ListPositionListener mListPositionListener;

    public EngineersListAdapter(Context context, List<User> engineerList) {
        mContext = context;
        mEngineerList = engineerList;
    }
    public void setListPositionListener(
            HireEngineer.ListPositionListener listPositionListener) {
        mListPositionListener = listPositionListener;
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
        if (user.getPhoto()!=null) {
            Glide.with(mContext)
                    .load(user.getPhoto())
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .error(R.drawable.ic_baseline_person_24)
                    .into(holder.profilePic);
        }
        else{
            holder.profilePic.setImageResource(R.drawable.ic_baseline_person_24);
        }
        holder.name.setText(user.getName());
        holder.profession.setText(user.getProfession());
        String expString  = user.getWorks()+" Works";
        holder.experience.setText(expString);
        String feeString = user.getFeePerHour()+RATE_PER_SQRFT_STRING;
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

        if (position==mEngineerList.size()-1){
            mListPositionListener.onListEndReached(position);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        if (mEngineerList!=null)
            return mEngineerList.size();
        else
        return 0;

    }
    public void updateList(List<User> updatedList){
        mEngineerList.addAll(updatedList);
        notifyItemRangeChanged(lastPosition,updatedList.size());
    }
}
