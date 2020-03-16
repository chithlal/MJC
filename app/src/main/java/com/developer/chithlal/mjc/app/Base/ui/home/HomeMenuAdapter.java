package com.developer.chithlal.mjc.app.Base.ui.home;

import static com.developer.chithlal.mjc.app.util.Constants.BUILDING_TYPE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.BuildingSpec.BuildingSpecActivity;

import java.util.List;

public class HomeMenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {
    private final Context mMContext;
    private final List<BuildingType> mMListBuildingType;


    public HomeMenuAdapter(Context mContext, List<BuildingType> mListBuildingType) {
        mMContext = mContext;
        mMListBuildingType = mListBuildingType;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_building_type,parent,false);
        return new MenuViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        BuildingType mBuildingType = mMListBuildingType.get(position);
        holder.menuTitle.setText(mBuildingType.getName());
        holder.menuImage.setImageResource(mBuildingType.getImageId());
        holder.menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(mMContext, BuildingSpecActivity.class);
                menuIntent.putExtra(BUILDING_TYPE,mBuildingType);
                mMContext.startActivity(menuIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mMListBuildingType.size();
    }


}
class MenuViewHolder extends RecyclerView.ViewHolder {
    ImageView menuImage;
    TextView menuTitle;
    MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        menuImage = itemView.findViewById(R.id.home_iv_menu_image);
        menuTitle = itemView.findViewById(R.id.home_tv_item_name);
    }
}