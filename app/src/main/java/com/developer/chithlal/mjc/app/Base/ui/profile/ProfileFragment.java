package com.developer.chithlal.mjc.app.Base.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.UserProfile.UserProfileActivity;
import com.developer.chithlal.mjc.app.engineer.Engineer;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.databinding.FragmentProfileBinding;
import com.developer.chithlal.mjc.root.di.FragmentComponent;
import com.developer.chithlal.mjc.root.di.ObjectFactory;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    @Inject
    ProfileContract.Presenter mPresenter;
    FragmentProfileBinding mBinding;
    User mUser;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentProfileBinding.inflate(getLayoutInflater());
        View root = mBinding.getRoot();

        ObjectFactory.getFragmentComponent().inject(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.setContext(getContext()); //set the context
        mPresenter.setupUI(this);// wire presenter to the fragment


        mBinding.pfAccountSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser!=null) {
                    Intent intent = new Intent(getContext(), UserProfileActivity.class);
                    intent.putExtra("USER", mUser);
                    startActivity(intent);
                }
                else {
                    showMessages("Invalid user!");
                }
            }
        });

    }

    @Override
    public void setupUserDetails(User user) {
        mUser = user;

        if (!user.isUserMode())
        {  // Engineer engineer = user;
            mBinding.tvPfUserProfileName.setText((user).getName());
            //mBinding.tvPfUserProfileProfession.setText((user).getProfession());
           // mBinding.rbPfUserProfileRatingStar.setRating(((Engineer) user).getRating());
        }
        else{
            mBinding.tvPfUserProfileName.setText((user).getName());
        }



    }

    @Override
    public void showMessages(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }
}
