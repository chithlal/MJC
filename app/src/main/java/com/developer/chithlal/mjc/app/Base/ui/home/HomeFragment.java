package com.developer.chithlal.mjc.app.Base.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.my_customers.MyCustomersActivity;
import com.developer.chithlal.mjc.databinding.FragmentHomeBinding;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {
    private HomeMenuAdapter mHomeMenuAdapter;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private HomePresenter mHomePresenter;
    View root;
    private User mUser;
    private FragmentHomeBinding mBinding;
    AccountManager mAccountManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
           ViewGroup container, Bundle savedInstanceState) {
        mBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        mAccountManager = new AccountManager(getActivity());
        mUser = mAccountManager.getUser();
        root = mBinding.getRoot();
        mRecyclerView = mBinding.homeRecyclerView;
        setupView();

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(mUser!=null && mUser.isUserMode()) {
            showSnackbar(mBinding.getRoot(), getResources().getString(R.string.select_building),
                    Snackbar.LENGTH_LONG);
        }else {
            mBinding.bsCvMyCustomers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MyCustomersActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void setupView() {
        if(mUser!=null && mUser.isUserMode()){
            mBinding.llEngineerHome.setVisibility(View.GONE);
            //mBinding.tvChooseBuildingType.setVisibility(View.VISIBLE);
            showSnackbar(mBinding.getRoot(),getResources().getString(R.string.select_building),Snackbar.LENGTH_LONG);
            mGridLayoutManager = new GridLayoutManager(getActivity(),2);
            mRecyclerView.setLayoutManager(mGridLayoutManager);
            mRecyclerView.hasFixedSize();
            mHomePresenter = new HomePresenter(this);
            mHomePresenter.setupMenu();
        }
        else{
            mBinding.homeRecyclerView.setVisibility(View.GONE);
            mBinding.llEngineerHome.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void bindAdapter(List<BuildingType> mBuildingType) {
        mHomeMenuAdapter = new HomeMenuAdapter(getActivity(),mBuildingType);
        mRecyclerView.setAdapter(mHomeMenuAdapter);


    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
    public void showSnackbar(View view, String message, int duration)
    {
        Snackbar.make(view, message, duration).show();
    }
}
