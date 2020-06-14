package com.developer.chithlal.mjc.app.Base.ui.profile;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Login.LoginActivity;
import com.developer.chithlal.mjc.app.UserProfile.UserProfileActivity;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.databinding.FragmentProfileBinding;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;
import com.developer.chithlal.mjc.root.di.ObjectFactory;

import javax.inject.Inject;

public class ProfileFragment extends Fragment implements ProfileContract.View {

    @Inject
    ProfileContract.Presenter mPresenter;
    FragmentProfileBinding mBinding;
    private User mUser;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentProfileBinding.inflate(getLayoutInflater());
        View root = mBinding.getRoot();

        ObjectFactory.getFragmentComponent().inject(this);
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
       mPresenter.setupUI(this);
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
                    mUser.setEditable(true);
                    Intent intent = new Intent(getContext(), UserProfileActivity.class);
                    intent.putExtra("USER", mUser);
                    startActivity(intent);
                }
                else {
                    showMessages("Invalid user!");
                }
            }
        });
        mBinding.pfLogoutOrLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser!=null) {
                    AccountManager accountManager = new AccountManager(getActivity());
                    accountManager.logoutUser();
                    showMessages("Logged out..!");
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else {
                    showMessages("No user available!");
                }
            }
        });

    }

    @Override
    public void setupUserDetails(User user) {
        mUser = user;

        if (!user.isUserMode())
        {
            mBinding.tvPfUserProfileName.setText(user.getName());
            mBinding.tvPfUserProfileProfession.setText(user.getProfession());
           mBinding.rbPfUserProfileRatingStar.setRating(user.getRating());
        }
        else{
            mBinding.tvPfUserProfileName.setText((user).getName());
            mBinding.tvPfUserProfileProfession.setText(user.getProfession());

        }
        Glide.with(this)
                .load(user.getPhoto())
                .centerCrop()
                .placeholder(R.drawable.ic_user_profile)
                .into(mBinding.pfProfileImage);



    }

    @Override
    public void showMessages(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

}
