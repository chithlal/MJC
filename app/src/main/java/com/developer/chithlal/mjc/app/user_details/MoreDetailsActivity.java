package com.developer.chithlal.mjc.app.user_details;

import static com.developer.chithlal.mjc.app.util.Constants.USER_OBJECT;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.Base.BaseActivity;
import com.developer.chithlal.mjc.app.work.AddWorkDetailsFragment;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.util.ProgressViewUtil;
import com.developer.chithlal.mjc.databinding.ActivityMoreDetailsBinding;
import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoreDetailsActivity extends AppCompatActivity implements MoreDetailContract.View,
        ExtraDetailsFragment.OnFragmentInteractionListener,AddWorkDetailsFragment.AddWorkListener{


    private ActivityMoreDetailsBinding mBinding;
    private Toolbar mToolbar;
    private User mUser;
    private FragmentManager mFragmentManager;
    List<Work> mWorkList = new ArrayList<>();
    @Inject
     MoreDetailContract.Presenter mPresenter;

    ProgressViewUtil mProgressViewUtil;
    private Uri mUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMoreDetailsBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        ((App)getApplication()).getAppComponent().injectMoreDetails(this);

        mProgressViewUtil = new ProgressViewUtil(this);

        mToolbar=mBinding.hireEngToolbar;

        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("More details..");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null)
        mUser = (User) intent.getSerializableExtra(USER_OBJECT);

        mPresenter.startUI(this);

        mBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mUser!=null) {
                    mUser.setAllPreviousWorks(mWorkList);
                    mPresenter.onWorkAdded(mUser);

                }


            }
        });
        mBinding.btAddMoreWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWorkDetailFragment();
            }
        });
    }

    @Override
    public void showExtraDetailsFragment() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .add(mBinding.moreDetailsFragmentFrame.getId(),new ExtraDetailsFragment(mUser,this))
                .commit();


    }

    @Override
    public void showWorkDetailFragment() {
        mToolbar.setTitle("Add your works");
        mBinding.moreDetailsFragmentFrame.setVisibility(View.VISIBLE);
        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction()
                .replace(mBinding.moreDetailsFragmentFrame.getId(),new AddWorkDetailsFragment(this))
                .commit();

    }

    @Override
    public void showMessage(String message) {
        if (mProgressViewUtil!=null)
        mProgressViewUtil.showLoading(message);
    }

    @Override
    public void dismissDialog() {
        if (mProgressViewUtil!=null)
            mProgressViewUtil.cancel();
    }

    @Override
    public void onRegistrationFinished() {
        showMessage("Registration completed,Welcome!");
        Intent intent =  new Intent(this, BaseActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Uri getIdProofUri() {
        return mUri;
    }

    @Override
    public void setIDProofURLAfterUploading(String url) {
        mUser.setIDProof(url);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onContinuePressed(User user,Uri uri) {
        mUri = uri;
        if (user!=null) {
            mProgressViewUtil.showLoading("Hold on,Validating information!");
            mPresenter.onExtraDetailsCollected(user);
        }

    }

    @Override
    public void onSkipPressed() {
        showWorkDetailFragment();

    }

    @Override
    public void onWorkAdded(Work work) {
        work.setEngineerId(mUser.getUserId());
        mWorkList.add(work);
        mBinding.moreDetailsFragmentFrame.setVisibility(View.GONE);
        mBinding.layoutAddMoreWork.setVisibility(View.VISIBLE);


    }

    @Override
    public void onAddWorkSkipped() {
        showMessage("Registration completed,Login to continue!");
        Intent intent = new Intent(this,BaseActivity.class);
        startActivity(intent);
        if (mUser!=null)
        postLogin(mUser);
        else
            showMessage("No user available!");
        finish();
    }
    void postLogin(User user){
        AccountManager accountManager = new AccountManager(this); //Setting the user  for the entire app session
        accountManager.loginUser(user);}
}
