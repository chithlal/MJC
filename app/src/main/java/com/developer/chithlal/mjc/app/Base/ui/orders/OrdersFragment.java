package com.developer.chithlal.mjc.app.Base.ui.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.databinding.FragmentActivitiesBinding;
import com.developer.chithlal.mjc.databinding.FragmentProfileBinding;


public class OrdersFragment extends Fragment {

   FragmentActivitiesBinding mBinding;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentActivitiesBinding.inflate(getLayoutInflater());
        View root = mBinding.getRoot();

        mBinding.textNotifications.setText("Coming soon..");

        return root;
    }
}
