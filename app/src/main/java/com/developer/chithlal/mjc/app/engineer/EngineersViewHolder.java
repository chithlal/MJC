package com.developer.chithlal.mjc.app.engineer;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

import de.hdodenhof.circleimageview.CircleImageView;

class EngineersViewHolder extends RecyclerView.ViewHolder {

    CircleImageView profilePic;
    TextView name,profession,fee,experience;
    RatingBar mRatingBar;
    CardView root;

    public EngineersViewHolder(@NonNull View itemView) {
        super(itemView);
        profilePic = itemView.findViewById(R.id.eng_iv_card_profile_image);
        name = itemView.findViewById(R.id.eng_tv_card_user_name);
        profession = itemView.findViewById(R.id.eng_tv_card_prof_quili);
        fee = itemView.findViewById(R.id.eng_tv_card_feeperhour);
        experience = itemView.findViewById(R.id.eng_tv_card_experience);
        mRatingBar = itemView.findViewById(R.id.eng_card_rating_bar);
        root = itemView.findViewById(R.id.eng_card_root);

    }
}
