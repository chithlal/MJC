package com.developer.chithlal.mjc.app.UserProfile;

import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;
import static com.developer.chithlal.mjc.app.util.Constants.PROFILE_IMAGE_REQUEST;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.dialogs.ImageSelectorDialog;
import com.developer.chithlal.mjc.app.dialogs.ImageSelectorUtil;
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.util.InputValidationhelper;
import com.developer.chithlal.mjc.app.work.AddWorkDetailsFragment;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.databinding.ActivityUserProfileBinding;
import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.account_manager.AccountManager;

import java.util.List;

import javax.inject.Inject;


public class UserProfileActivity extends AppCompatActivity implements UserProfileContract.View,
        OptionItemAdapter.onItemSelectListener, AddWorkDetailsFragment.AddWorkListener,
        ImageSelectorUtil.PhotoSelectorInterface {
    private ActivityUserProfileBinding mBinding;
    private static final String KEY_UID = "UID";
    private Toolbar mToolbar;
    private User mUser;
    private Activity mActivity;
    private OptionItemAdapter mOptionItemAdapter;
    private boolean isEditEnabled = false;
    private boolean isDataValid = true;
    private AddWorkDetailsFragment.AddWorkListener mAddWorkListener;

    private boolean isUiLoaded = false;

    private boolean isCurrentUserProfile = false;

    private String uid;

    @Inject
    UserProfileContract.Presenter mPresenter;

    private String TAG = "Userprofile";
    private Uri selectedIdProof;
    AccountManager mAccountManager;
    AddWorkDetailsFragment mAddWorkDetailsFragment = null;
    private FragmentManager mFragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBinding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(mBinding.getRoot());
        Intent userIntent=getIntent();
        mAccountManager = new AccountManager(this);
        ((App)getApplication()).getAppComponent().injectUserProfile(this); //inject presenter object

        if (savedInstanceState!=null){
            uid = (String)savedInstanceState.get(KEY_UID);
            mBinding.userProfileLoadingView.setVisibility(View.VISIBLE);
            mPresenter.updateUserObject(uid);
        }else {


            if (userIntent != null) {
                mUser = (User) userIntent.getSerializableExtra("USER");
                User localUser = getLocalUser();
                if (localUser != null) {
                    uid = mAccountManager.getUserId();
                }
            }
            if (mUser == null) {
                User localUser = getLocalUser();
                if (localUser != null) {
                    uid = mAccountManager.getUserId();
                    mUser = localUser;
                }
                else {
                    showMessage("User not available!");
                }
            }


            if (mUser != null) {

                if (uid != null && mUser.getUserId() != null) {
                    if (uid.equals(mUser.getUserId())) {
                        enableEditButton(true);
                        isCurrentUserProfile = true;
                    }
                    else {
                        enableEditButton(false);
                        isCurrentUserProfile = false;
                    }
                }
            } else {
                showMessage("User not available!");
            }
        }

        mAddWorkListener = this;

        mToolbar=mBinding.userProfileToolbar;
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);

        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
            mBinding.actionBarTitle.setText("Profile");
        }


        mPresenter.setContext(this);
        mActivity = this;
        mBinding.tvBsUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: upload");
                selectImage(GALLERY_REQUEST_CODE);
            }
        });
        mBinding.btUserProfileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.btUserProfileEditButton.getText().toString().equals("Edit"))
                enableEditMode();
                else {

                    saveUser();

                }
            }
        });
        mBinding.btAddWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBinding.btAddWork.getText().equals("Add works")) {
                    mBinding.frameUserProfileAddWork.setVisibility(View.VISIBLE);
                    mAddWorkDetailsFragment = new AddWorkDetailsFragment(mAddWorkListener);
                    mFragmentManager = getSupportFragmentManager();
                    mFragmentManager.beginTransaction()
                            .add(R.id.frame_user_profile_add_work,mAddWorkDetailsFragment)
                            .commit();
                    mBinding.frameUserProfileAddWork.requestFocus();
                    mBinding.userProfileScrollView.scrollTo(
                            mBinding.userProfileScrollView.getScrollX(),
                            mBinding.userProfileScrollView.getBottom());
                    mBinding.btAddWork.setText("Cancel");
                }
                else {
                    mBinding.btAddWork.setText("Add works");
                    mBinding.frameUserProfileAddWork.setVisibility(View.GONE);
                    mFragmentManager.beginTransaction().
                            remove(mAddWorkDetailsFragment).commit();
                    mAddWorkDetailsFragment = null;

                }

            }
        });
        mBinding.userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEditEnabled){
                    selectImage(PROFILE_IMAGE_REQUEST);
                }
            }
        });

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(KEY_UID,uid);
        super.onSaveInstanceState(outState);
    }

    private User getLocalUser() {

      return   mAccountManager.getUser();
    }

    private void enableEditMode() {
        mBinding.btUserProfileEditButton.setText("Save");

        mBinding.tvUserProfilePhone.setVisibility(View.GONE);
        mBinding.tvUserProfileMembership.setVisibility(View.GONE);
        mBinding.tvUserProfileAge.setVisibility(View.GONE);
        mBinding.tvUserProfileAddress.setVisibility(View.GONE);
        mBinding.tvUserProfilePreivousWorkText.setVisibility(View.GONE);
        mBinding.slider.setVisibility(View.GONE);
        mBinding.cvUserProfileIdCard.setVisibility(View.GONE);
        mBinding.btUserProfileHireMe.setVisibility(View.GONE);

        if (mUser.isUserMode())
        mBinding.ivMemberSince.setVisibility(View.GONE);

       // mBinding.etUserProfilePhone.setVisibility(View.VISIBLE);
        mBinding.etUserProfileAddress.setText(mUser.getAddress());
        mBinding.etUserProfileAge.setText(mUser.getAge());

        if (mUser.getProfession() != null && (mUser.getProfession().isEmpty()))
            mBinding.tvUserProfileProfessionEdit.setText("Profession");
        else
            mBinding.tvUserProfileProfession.setText(mUser.getProfession());

        mBinding.ivUserProfilePhone.setVisibility(View.GONE);
        mBinding.tvUserProfileProfessionEdit.setVisibility(View.VISIBLE);

        mBinding.btUserProfileEditProfession.setVisibility(View.VISIBLE);
        mBinding.rvProfessionList.setVisibility(View.GONE);
        mBinding.etUserProfileAge.setVisibility(View.VISIBLE);
        mBinding.etUserProfileAddress.setVisibility(View.VISIBLE);
        mBinding.ivEditProfession.setVisibility(View.VISIBLE);
        mBinding.ivUserProfileAddress.setVisibility(View.VISIBLE);

        if (!mUser.isUserMode())
        mBinding.cvUserProfileAddwork.setVisibility(View.VISIBLE);

        mBinding.tvBsUpload.setVisibility(View.VISIBLE);

        if (!mUser.isUserMode()) {
            mBinding.etUserProfileFee.setText(String.valueOf(mUser.getFeePerHour()));
            mBinding.etUserProfileFee.setVisibility(View.VISIBLE);
            mBinding.ivMemberSince.setImageResource(R.drawable.ic_payment);
        }


        mPresenter.onEditEnabled();


        mBinding.btUserProfileEditProfession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBinding.rvProfessionList.setVisibility(View.VISIBLE);
            }
        });


 
        isEditEnabled = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isUiLoaded) {
            mBinding.userProfileLoadingView.setVisibility(View.VISIBLE);
            Log.d(TAG, "onResume: called");
            mPresenter.setUi(this);
        }
        if(isCurrentUserProfile){
            mUser = getLocalUser();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mAccountManager.updateUser();
        mBinding.userProfileLoadingView.setVisibility(View.GONE);
        mBinding.clUserProfile.setVisibility(View.GONE);
    }

    @Override
    public User getUser() {

        return mUser;
    }

    @Override
    public void setUser(User user) {
        mUser = user;
        if (!isEditEnabled)
        {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setUserDetails(mUser);
                }
            },1000);
        }


    }

    @Override
    public String getIDCard() {
        return null;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }

    @Override
    public void setPreviousWorkImages(List<SliderItem> images) {
        mBinding.slider.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL,false));
        mBinding.slider.setAdapter(new ImageSliderAdpater(this,images));



    }

    @Override
    public void setProfessionList(List<String> professionList) {
        mOptionItemAdapter = new OptionItemAdapter(this,professionList,this,false);
        mBinding.rvProfessionList.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        mBinding.rvProfessionList.setAdapter(mOptionItemAdapter);
    }



    @Override
    public void refreshIdCardImage() {
        if (selectedIdProof!=null)
        if (mBinding.ivUserProfileIdProofImage.getVisibility()==View.VISIBLE)
            Glide.with(this)
                    .load(selectedIdProof)
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(mBinding.ivUserProfileIdProofImage);
    }

    private void setUserDetails(User user){

        if (uid!=null&&user.getUserId()!=null){
            mBinding.tvUserProfileAddress.setText(user.getAddress());
            mBinding.tvUserProfileAge.setText(user.getAge());
            String since_string = " Member since "+user.getDoj();
            mBinding.tvUserProfileMembership.setText( since_string);
            mBinding.tvUserProfileName.setText(user.getName());
            mBinding.tvUserProfilePhone.setText(user.getPhone());
            mBinding.tvUserProfileProfession.setText(user.getProfession());
            mBinding.tvUserProfileProfessionEdit.setText(user.getProfession());
            mBinding.userProfileLoadingView.setVisibility(View.GONE);
            mBinding.clUserProfile.setVisibility(View.GONE);
            mBinding.userProfileScrollView.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .load(user.getIDProof())
                    .centerCrop()
                    .placeholder(R.drawable.ic_image_black_24dp)
                    .into(mBinding.ivUserProfileIdProofImage);
            Glide.with(this)
                    .load(user.getPhoto())
                    .centerCrop()
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .into(mBinding.userProfileImage);


            //if its current user show all the details
            if (uid.equals(user.getUserId())){
                mBinding.btUserProfileHireMe.setVisibility(View.GONE);
                if(user.isUserMode())
                    mBinding.slider.setVisibility(View.GONE);
                else {
                    mBinding.slider.setVisibility(View.VISIBLE);
                    mBinding.tvUserProfilePreivousWorkText.setVisibility(View.VISIBLE);
                    mBinding.llUserProfileRatingRow.setVisibility(View.VISIBLE);
                    mBinding.tvUserProfilePayment.setVisibility(View.VISIBLE);
                    mBinding.tvUserProfileRating.setVisibility(View.VISIBLE);
                    mBinding.tvUserProfileRating.setText(String.valueOf(user.getRating()));
                    mBinding.tvUserProfilePayment.setText(String.valueOf(user.getFeePerHour()));
                    mBinding.tvUserProfileWorksCount.setText(String.valueOf(user.getWorks()));
                    mBinding.rbUserProfileRatingStar.setRating(user.getRating());
                }

            }
            else{

                mBinding.tvUserProfilePayment.setText(String.valueOf((int)user.getFeePerHour()));
                mBinding.tvUserProfileRating.setText(String.valueOf(user.getRating()));
                mBinding.tvUserProfileWorksCount.setText(String.valueOf(user.getWorks()));
                mBinding.rbUserProfileRatingStar.setRating(user.getRating());
                mBinding.btUserProfileHireMe.setVisibility(View.VISIBLE);
                mBinding.llUserProfileRatingRow.setVisibility(View.VISIBLE);
                mBinding.tvUserProfilePayment.setVisibility(View.VISIBLE);
                mBinding.tvUserProfilePreivousWorkText.setVisibility(View.VISIBLE);
                mBinding.slider.setVisibility(View.VISIBLE);
                mBinding.rbUserProfileRatingStar.setVisibility(View.VISIBLE);
                mBinding.tvUserProfileAddress.setVisibility(View.GONE);
                mBinding.tvUserProfilePhone.setVisibility(View.GONE);
                mBinding.ivUserProfileAddress.setVisibility(View.GONE);
                mBinding.ivUserProfilePhone.setVisibility(View.GONE);
                mBinding.cvUserProfileIdCard.setVisibility(View.GONE);
                mBinding.tvUserProfileIdProofTitle.setVisibility(View.GONE);
                mBinding.linUserProfileIdProofSeparator.setVisibility(View.GONE);
            }
            mBinding.userProfileLoadingView.setVisibility(View.GONE);
            mBinding.clUserProfile.setVisibility(View.GONE);
            mBinding.userProfileScrollView.setVisibility(View.VISIBLE);

        }
        isUiLoaded = true;
    }
    private void selectImage(int REQ_CODE){
       ImageSelectorUtil imageSelectorUtil = new ImageSelectorUtil(this,REQ_CODE,this);
       imageSelectorUtil.openSelector();
    }
/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE){
            if(data!=null) {
                Uri selectedImage = data.getData();
                selectedIdProof = selectedImage;
                mBinding.cvUserProfileIdCard.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(mBinding.ivUserProfileIdProofImage);
               *//* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*//*
                mBinding.tvBsUpload.setVisibility(View.GONE);
                mPresenter.uploadIdCard(selectedImage,mUser.getUserId());
            }
            else {
                showMessage("Please select a photo!");
            }
        }
        if (requestCode == PROFILE_IMAGE_REQUEST){
            if (data!= null) {
                Uri selectedProfileImage = data.getData();
                Glide.with(this)
                        .load(selectedProfileImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_user_profile)
                        .error(R.drawable.ic_user_profile)
                        .into(mBinding.userProfileImage);
                mPresenter.uploadProfileImage(selectedProfileImage,mUser.getUserId());
            }
            else {
                showMessage("Please select a photo!");
            }

        }
    }*/


    @Override
    public void onItemSelected(String profession) {

        mBinding.tvUserProfileProfessionEdit.setText(profession);

    }

    @Override
    public void onItemRemoved(String item) {

    }

    private void saveUser() {
        User dummyUser;
        isDataValid = true;
        InputValidationhelper inputValidationhelper = new InputValidationhelper();
        if (mUser != null) {
            dummyUser = mUser;
            if (inputValidationhelper.validate(mBinding.etUserProfileAddress,
                    InputValidationhelper.TYPE_TEXT))
                dummyUser.setAddress(mBinding.etUserProfileAddress.getText().toString());
            else isDataValid = false;
            if (inputValidationhelper.validate(mBinding.etUserProfileAge,
                    InputValidationhelper.TYPE_DIGIT))
                dummyUser.setAge(mBinding.etUserProfileAge.getText().toString());
            else isDataValid = false;
            if (!mBinding.etUserProfileAddress.getText().toString().isEmpty())
                dummyUser.setProfession(mBinding.tvUserProfileProfessionEdit.getText().toString());
            else isDataValid = false;
            if (!mUser.isUserMode()){
                if (inputValidationhelper.validate(mBinding.etUserProfileFee,
                        InputValidationhelper.TYPE_DIGIT))
                    dummyUser.setFeePerHour(
                            Float.parseFloat(mBinding.etUserProfileFee.getText().toString()));
                else isDataValid = false;
            }


        } else {
            showMessage("User not found");
            dummyUser = new User("No Name");
             isDataValid = false;
        }
        if (isDataValid){
            mUser = dummyUser;
        mPresenter.saveUserData(mUser);
        //setUserDetails(mUser);
        disableEditMode();
        mBinding.btUserProfileEditButton.setText("Edit");
    }
         else
        showMessage("Invalid data!");
    }
    void disableEditMode(){
        mBinding.tvUserProfilePhone.setVisibility(View.VISIBLE);
        mBinding.tvUserProfileMembership.setVisibility(View.VISIBLE);
        mBinding.tvUserProfileAge.setVisibility(View.VISIBLE);
        mBinding.tvUserProfileAddress.setVisibility(View.VISIBLE);
        if (!mUser.isUserMode())
        mBinding.tvUserProfilePreivousWorkText.setVisibility(View.VISIBLE);
        if (!mUser.isUserMode())
        mBinding.slider.setVisibility(View.VISIBLE);
        mBinding.cvUserProfileIdCard.setVisibility(View.VISIBLE);
        mBinding.btUserProfileHireMe.setVisibility(View.GONE);
        mBinding.ivMemberSince.setVisibility(View.VISIBLE);

        //mBinding.etUserProfilePhone.setVisibility(View.GONE);
        mBinding.ivUserProfilePhone.setVisibility(View.VISIBLE);
        mBinding.tvUserProfileProfessionEdit.setVisibility(View.GONE);
        mBinding.btUserProfileEditProfession.setVisibility(View.GONE);
        mBinding.rvProfessionList.setVisibility(View.GONE);
        mBinding.etUserProfileAge.setVisibility(View.GONE);
        mBinding.etUserProfileAddress.setVisibility(View.GONE);
        mBinding.ivEditProfession.setVisibility(View.GONE);
        mBinding.cvUserProfileAddwork.setVisibility(View.GONE);
        mBinding.tvBsUpload.setVisibility(View.GONE);
        mBinding.etUserProfileFee.setVisibility(View.GONE);
        mBinding.ivMemberSince.setImageResource(R.drawable.ic_member);
        isEditEnabled = false;


    }

    @Override
    public void onWorkAdded(Work work) {
        mUser.addWork(work);
        showMessage("Work added");
       // mAddWorkDetailsFragment = null;
        mBinding.frameUserProfileAddWork.setVisibility(View.GONE);
        mBinding.btAddWork.setText("Add works");
    }

    @Override
    public void onAddWorkSkipped() {

    }


    void enableEditButton(boolean enable){
        if (enable)
        mBinding.btUserProfileEditButton.setVisibility(View.VISIBLE);
        else mBinding.btUserProfileEditButton.setVisibility(View.GONE);
   }
    @Override
    public boolean onSupportNavigateUp() {
        if (!isEditEnabled) {
            onBackPressed();
            return true;
        }
        else {
            mBinding.btUserProfileEditButton.setText("Edit");
            disableEditMode();
            return false;
        }

    }

    @Override
    public void onPhotoSelected(Uri uri, int reqCode) {
        if (reqCode == GALLERY_REQUEST_CODE){

                Uri selectedImage = uri;
                selectedIdProof = selectedImage;
                mBinding.cvUserProfileIdCard.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(mBinding.ivUserProfileIdProofImage);
                /* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*/
                mBinding.tvBsUpload.setVisibility(View.GONE);
                mPresenter.uploadIdCard(selectedImage,mUser.getUserId());
            }
        else if (reqCode==PROFILE_IMAGE_REQUEST){
            Uri selectedProfileImage = uri;
            Glide.with(this)
                    .load(selectedProfileImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user_profile)
                    .error(R.drawable.ic_user_profile)
                    .into(mBinding.userProfileImage);
            mPresenter.uploadProfileImage(selectedProfileImage,mUser.getUserId());
        }
    }

    @Override
    public void onPhotoSelectionError(String message) {
        showMessage(message);
    }
}
