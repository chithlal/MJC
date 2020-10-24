package com.developer.chithlal.mjc.app.engineers_list;

import static com.developer.chithlal.mjc.app.util.Constants.BUILDING_TYPE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class EngineersListActivity extends AppCompatActivity implements HireEngineerContract.View {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private ActivityHireEngineerBinding mBinding;
    @Inject
    HireEngineerContract.Presenter mHireEngineerPresenter;
    private EngineersListAdapter mEngineersListAdapter;
    private ProgressViewUtil mProgressViewUtil;
    private boolean isListLoadedOnce = false;
    private List<User> listEngineers = new ArrayList<>();
    private int pageNumber = 1;
    private String mBuildingType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityHireEngineerBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ((App) getApplication()).getAppComponent().inject(this);

        mProgressViewUtil = new ProgressViewUtil(this);

        mToolbar = mBinding.hireEngToolbar;

        Intent buildingSpecIntent = getIntent();
        if (buildingSpecIntent != null) {
            mBuildingType = buildingSpecIntent.getStringExtra(BUILDING_TYPE);
        }

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("Engineers");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        // mHireEngineerPresenter = new HireEngineerPresenter(this,this);
        // mHireEngineerPresenter.setUpUi();
        mBinding.rvHireEngineer.setLayoutManager(new LinearLayoutManager(this));
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(this,
                R.anim.layout_anim_fall_down);
        mBinding.rvHireEngineer.setLayoutAnimation(animation);
        mEngineersListAdapter = new EngineersListAdapter(this, listEngineers);
        mBinding.rvHireEngineer.setAdapter(mEngineersListAdapter);
        mEngineersListAdapter.setListPositionListener(new ListPositionListener() {
            @Override
            public void onListEndReached(int position) {
                if (mEngineersListAdapter != null) {
                    pageNumber++;
                    mHireEngineerPresenter.getNextPageOfList(pageNumber);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isListLoadedOnce) {
            mProgressViewUtil.showLoading("Hold on,Searching for engineers..");

            mHireEngineerPresenter.setUpUi(this, this);
        }

    }

    @Override
    public void updateList(List<User> engineerList, int pageNumber) {


        mEngineersListAdapter.updateList(engineerList);
        if (engineerList.isEmpty()&pageNumber==1){
            mBinding.tvEmptyEngineerListText.setVisibility(View.VISIBLE);
        }
        mProgressViewUtil.cancel();
        isListLoadedOnce = true;

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        mProgressViewUtil.cancel();
    }

    @Override
    public String getBuildingType() {

        return mBuildingType;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public interface ListPositionListener {
        void onListEndReached(int position);
    }

}
