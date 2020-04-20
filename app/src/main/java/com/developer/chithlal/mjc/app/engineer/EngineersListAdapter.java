package com.developer.chithlal.mjc.app.engineer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

import java.util.List;

public class EngineersListAdapter extends RecyclerView.Adapter<EngineersViewHolder> {
    private final Context mContext;
    private final List<Engineer> mEngineerList;

    public EngineersListAdapter(Context context, List<Engineer> engineerList) {
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
            Engineer engineer = mEngineerList.get(position);
            holder.profilePic.setImageResource(R.drawable.chithlal_photo);
        holder.name.setText(engineer.getName());
        holder.profession.setText(engineer.getProfession());
        String expString  = engineer.getWorks()+" Works";
        holder.experience.setText(expString);
        String feeString = engineer.getFeePerHour()+"$/Hr";
        holder.fee.setText(feeString);
        holder.mRatingBar.setRating(engineer.getRating());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
