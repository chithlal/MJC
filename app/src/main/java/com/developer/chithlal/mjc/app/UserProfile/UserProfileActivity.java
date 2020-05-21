package com.developer.chithlal.mjc.app.UserProfile;

import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import com.developer.chithlal.mjc.app.engineer.User;
import com.developer.chithlal.mjc.app.util.InputValidationhelper;
import com.developer.chithlal.mjc.app.work.AddWorkDetailsFragment;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.databinding.ActivityUserProfileBinding;
import com.developer.chithlal.mjc.root.App;

import java.util.List;

import javax.inject.Inject;


public class UserProfileActivity extends AppCompatActivity implements UserProfileContract.View,
        OptionItemAdapter.onItemSelectListener, AddWorkDetailsFragment.AddWorkListener {
    private ActivityUserProfileBinding mBinding;
    private Toolbar mToolbar;
    private User mUser;
    private Activity mActivity;
    private OptionItemAdapter mOptionItemAdapter;
    private boolean isEditEnabled = false;
    private boolean isDataValid = true;
    private AddWorkDetailsFragment.AddWorkListener mAddWorkListener;
    @Inject
    UserProfileContract.Presenter mPresenter;
    AddWorkDetailsFragment mAddWorkDetailsFragment;
    private String TAG = "Userprofile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBinding = ActivityUserProfileBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(mBinding.getRoot());
        Intent userIntent=getIntent();
        if (userIntent!=null){
            mUser = (User)userIntent.getSerializableExtra("USER");
        }
        if(mUser == null){
            User localUser = getLocalUser();
            if (localUser!=null){
                mUser = localUser;
            }
        }
        if (mUser.isEditable())
            enableEditButton(true);
        else enableEditButton(false);

        ((App)getApplication()).getAppComponent().injectUserProfile(this);
        mAddWorkListener = this;

        mToolbar=mBinding.userProfileToolbar;
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);


        mPresenter.setContext(this);
        mActivity = this;
        mBinding.tvBsUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: upload");
                selectImage();
            }
        });
        mBinding.btUserProfileEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBinding.btUserProfileEditButton.getText().toString().equals("Edit"))
                enbaleEditMode();
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
                    getSupportFragmentManager().beginTransaction()
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
                    getSupportFragmentManager().beginTransaction().
                            remove(mAddWorkDetailsFragment).commit();

                }

            }
        });

    }

    private User getLocalUser() {

      return   ((App)getApplication()).getUser();
    }

    private void enbaleEditMode() {
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
        mBinding.ivUserProfilePhone.setVisibility(View.GONE);
        mBinding.tvUserProfileProfessionEdit.setVisibility(View.VISIBLE);
        mBinding.tvUserProfileProfessionEdit.setText("Profession");
        mBinding.btUserProfileEditProfession.setVisibility(View.VISIBLE);
        mBinding.rvProfessionList.setVisibility(View.GONE);
        mBinding.etUserProfileAge.setVisibility(View.VISIBLE);
        mBinding.etUserProfileAddress.setVisibility(View.VISIBLE);
        mBinding.ivEditProfession.setVisibility(View.VISIBLE);


        if (!mUser.isUserMode())
        mBinding.cvUserProfileAddwork.setVisibility(View.VISIBLE);

        mBinding.tvBsUpload.setVisibility(View.VISIBLE);

        if (!mUser.isUserMode()) {
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
        mBinding.userProfileLoadingView.setVisibility(View.VISIBLE);
        Log.d(TAG, "onResume: called");
        mPresenter.setUi(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
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
            },5000);
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

    private void setUserDetails(User user){

        if (!user.isUserMode()){
            if (!user.isEditable())
            mBinding.btUserProfileHireMe.setVisibility(View.VISIBLE);
            mBinding.llUserProfileRatingRow.setVisibility(View.VISIBLE);
            mBinding.tvUserProfilePayment.setVisibility(View.VISIBLE);
            mBinding.tvUserProfilePreivousWorkText.setVisibility(View.VISIBLE);
            mBinding.slider.setVisibility(View.VISIBLE);
            mBinding.rbUserProfileRatingStar.setVisibility(View.VISIBLE);

            Glide.with(this)
                    .load(user.getIDProof())
                    .centerCrop()
                    .into(mBinding.ivUserProfileIdProofImage);
            mBinding.tvUserProfilePayment.setText(String.valueOf((int)user.getFeePerHour()));
            mBinding.tvUserProfileRating.setText(String.valueOf(user.getRating()));
            mBinding.tvUserProfileWorksCount.setText(String.valueOf(user.getWorks()));
            mBinding.rbUserProfileRatingStar.setRating(user.getRating());
        }
        mBinding.tvUserProfileAddress.setText(user.getAddress());
        mBinding.tvUserProfileAge.setText(user.getAge());
        String since_string = " Member since"+user.getDoj();
        mBinding.tvUserProfileMembership.setText( since_string);
        mBinding.tvUserProfileName.setText(user.getName());
        mBinding.tvUserProfilePhone.setText(user.getPhone());
        mBinding.tvUserProfileProfession.setText(user.getProfession());

        mBinding.tvUserProfileProfessionEdit.setText(user.getProfession());
        if (user.getPhoto()==null){
            mBinding.userProfileImage.setImageResource(R.drawable.user_png);
        }
        mBinding.userProfileLoadingView.setVisibility(View.GONE);
        mBinding.clUserProfile.setVisibility(View.GONE);
        mBinding.userProfileScrollView.setVisibility(View.VISIBLE);

    }
    private void selectImage(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,GALLERY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE){
            if(data!=null) {
                Uri selectedImage = data.getData();
                mBinding.cvUserProfileIdCard.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(mBinding.ivUserProfileIdProofImage);
               /* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*/
                mBinding.tvBsUpload.setVisibility(View.GONE);
            }
            else {
                showMessage("Please select a photo!");
            }
        }
    }


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
            dummyUser =  mUser;
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
            if (inputValidationhelper.validate(mBinding.etUserProfileFee,
                    InputValidationhelper.TYPE_DIGIT)&&!mUser.isUserMode())
                dummyUser.setFeePerHour(
                        Integer.parseInt(mBinding.etUserProfileFee.getText().toString()));
            else isDataValid = false;


        } else {
            showMessage("User not found");
            dummyUser = new User("No Name");
             isDataValid = false;
        }
        if (isDataValid){
            mUser = dummyUser;
        mPresenter.saveUserData(mUser);
        setUserDetails(mUser);
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
        if (!mUser.isUserMode()&&!mUser.isEditable())
        mBinding.btUserProfileHireMe.setVisibility(View.VISIBLE);

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
        onBackPressed();
        return true;
    }
}
