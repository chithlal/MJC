package com.developer.chithlal.mjc.app.construction_history;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.databinding.ActivityBuildingSpecsBinding;
import com.developer.chithlal.mjc.databinding.ActivityConstructionHistoryBinding;

public class ConstructionHistory extends AppCompatActivity {
    Toolbar mToolbar;
    ActivityConstructionHistoryBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityConstructionHistoryBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        //Toolbar setup
        mToolbar = mBinding.chToolbar;
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        mBinding.chTv.setText("Coming soon..");
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
