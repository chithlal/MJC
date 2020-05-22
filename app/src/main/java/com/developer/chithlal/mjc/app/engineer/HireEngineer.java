package com.developer.chithlal.mjc.app.engineer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.util.ProgressViewUtil;
import com.developer.chithlal.mjc.databinding.ActivityHireEngineerBinding;
import com.developer.chithlal.mjc.root.App;

import java.util.List;

import javax.inject.Inject;

public class HireEngineer extends AppCompatActivity implements HireEngineerContract.View {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ActivityHireEngineerBinding mBinding;
    @Inject
    HireEngineerContract.Presenter mHireEngineerPresenter;
    private EngineersListAdapter mEngineersListAdapter;
    private ProgressViewUtil mProgressViewUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHireEngineerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ((App)getApplication()).getAppComponent().inject(this);

        mProgressViewUtil = new ProgressViewUtil(this);

        mToolbar=mBinding.hireEngToolbar;

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);
       // mHireEngineerPresenter = new HireEngineerPresenter(this,this);
       // mHireEngineerPresenter.setUpUi();
        mBinding.rvHireEngineer.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mProgressViewUtil.showLoading("Hold on,Searching for engineers..");
        mHireEngineerPresenter.setUpUi(this,this);

    }

    @Override
    public void updateList(List<User> engineerList) {
        mEngineersListAdapter = new EngineersListAdapter(this,engineerList);
        mBinding.rvHireEngineer.setAdapter(mEngineersListAdapter);
        mProgressViewUtil.cancel();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
