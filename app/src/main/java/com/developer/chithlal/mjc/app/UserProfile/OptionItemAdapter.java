package com.developer.chithlal.mjc.app.UserProfile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OptionItemAdapter extends RecyclerView.Adapter<OptionItemAdapter.ProfessionVH> {
    private Context mContext;
    private List<String> itemList;
    private onItemSelectListener mOnItemSelectListener;
    private boolean mMultipleOptionSelection;
    private int layoutId = -1;
    private Map<Integer,String> selectedItems = new HashMap<>();

    public OptionItemAdapter(Context context, List<String> itemList,
            onItemSelectListener onItemSelectListener, int layoutId,boolean multipleOptionSelection) {
        mContext = context;
        this.itemList = itemList;
        mOnItemSelectListener = onItemSelectListener;
        this.layoutId = layoutId;
        mMultipleOptionSelection = multipleOptionSelection;
    }

    public OptionItemAdapter(Context context, List<String> itemList,
            onItemSelectListener onItemSelectListener,boolean multipleOptionSelection) {
        mContext = context;
        this.itemList = itemList;
        mOnItemSelectListener = onItemSelectListener;
        mMultipleOptionSelection = multipleOptionSelection;
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
        holder.mTextView.setText(itemList.get(position));
        if(!mMultipleOptionSelection)
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemSelectListener.onItemSelected(itemList.get(position));
            }
        });
        else {
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedItems.containsKey(position)){
                        selectedItems.remove(position);
                        holder.mTextView.setBackground(mContext.getResources().getDrawable(R.drawable.app_edit_text_bg_filled));
                        mOnItemSelectListener.onItemRemoved(itemList.get(position));
                    }

                    else{
                        selectedItems.put(position,itemList.get(position));
                        holder.mTextView.setBackground(mContext.getResources().getDrawable(R.drawable.app_edit_text_bg_filled_green));
                        mOnItemSelectListener.onItemSelected(itemList.get(position));
                    }


                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ProfessionVH extends RecyclerView.ViewHolder {
        TextView mTextView;
        public ProfessionVH(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.tv_item_card);
        }
    }
    public interface onItemSelectListener {
        void onItemSelected(String item);
        void onItemRemoved(String item);
    }


}
