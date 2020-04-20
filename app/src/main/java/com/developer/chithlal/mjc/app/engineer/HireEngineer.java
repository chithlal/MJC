package com.developer.chithlal.mjc.app.engineer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.databinding.ActivityHireEngineerBinding;

import java.util.List;

public class HireEngineer extends AppCompatActivity implements HireEngineerContract.View {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ActivityHireEngineerBinding mBinding;
    private HireEngineerPresenter mHireEngineerPresenter;
    private EngineersListAdapter mEngineersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHireEngineerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        mToolbar=mBinding.hireEngToolbar;

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);
        mHireEngineerPresenter = new HireEngineerPresenter(this,this);
        mHireEngineerPresenter.setUpUi();
        mBinding.rvHireEngineer.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void updateList(List<Engineer> engineerList) {
        mEngineersListAdapter = new EngineersListAdapter(this,engineerList);
        mBinding.rvHireEngineer.setAdapter(mEngineersListAdapter);

    }

    @Override
    public void showMessage(String message) {

    }
}
