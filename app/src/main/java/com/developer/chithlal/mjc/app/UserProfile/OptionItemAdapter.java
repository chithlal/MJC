package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

import java.util.List;

public class OptionItemAdapter extends RecyclerView.Adapter<OptionItemAdapter.ProfessionVH> {
    private Context mContext;
    private List<String> professionList;
    private onItemSelectListener mProfessionSelectListener;
    private int layoutId = -1;

    public OptionItemAdapter(Context context, List<String> professionList,
            onItemSelectListener professionSelectListener, int layoutId) {
        mContext = context;
        this.professionList = professionList;
        mProfessionSelectListener = professionSelectListener;
        this.layoutId = layoutId;
    }

    public OptionItemAdapter(Context context, List<String> professionList,
            onItemSelectListener onItemSelectListener) {
        mContext = context;
        this.professionList = professionList;
        mProfessionSelectListener = onItemSelectListener;
    }

    @NonNull
    @Override
    public ProfessionVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (layoutId==-1)
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profession_item,parent,false);
        else view = LayoutInflater.from(parent.getContext()).inflate(layoutId,parent,false);

        return new ProfessionVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessionVH holder, int position) {
        holder.mTextView.setText(professionList.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProfessionSelectListener.onItemSelected(professionList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return professionList.size();
    }

    public class ProfessionVH extends RecyclerView.ViewHolder {
        TextView mTextView;
        public ProfessionVH(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_item_card);
        }
    }
    public interface onItemSelectListener {
        void onItemSelected(String profession);
    }


}
