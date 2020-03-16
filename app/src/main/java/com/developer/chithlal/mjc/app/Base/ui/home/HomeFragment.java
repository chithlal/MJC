package com.developer.chithlal.mjc.app.Base.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {
    private HomeMenuAdapter mHomeMenuAdapter;
    private RecyclerView mRecyclerView;
    private GridLayoutManager mGridLayoutManager;
    private HomePresenter mHomePresenter;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
           ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_home, container, false);
        mRecyclerView = root.findViewById(R.id.home_recycler_View);
        mGridLayoutManager = new GridLayoutManager(getActivity(),2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.hasFixedSize();
        mHomePresenter = new HomePresenter(this);
        mHomePresenter.setupMenu();
        return root;
    }

    @Override
    public void bindAdapter(List<BuildingType> mBuildingType) {
        mHomeMenuAdapter = new HomeMenuAdapter(getActivity(),mBuildingType);
        mRecyclerView.setAdapter(mHomeMenuAdapter);


    }

    @Override
    public void showError(String message) {

    }
}
