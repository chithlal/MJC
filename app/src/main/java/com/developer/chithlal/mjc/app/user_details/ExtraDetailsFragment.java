package com.developer.chithlal.mjc.app.user_details;

import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;
import static com.developer.chithlal.mjc.app.util.InputValidationhelper.TYPE_DIGIT;
import static com.developer.chithlal.mjc.app.util.InputValidationhelper.TYPE_TEXT;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.UserProfile.OptionItemAdapter;
import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.util.InputValidationhelper;
import com.developer.chithlal.mjc.databinding.FragmentExtraDetailsBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.shape.CornerFamily;

import java.util.ArrayList;
import java.util.List;


public class ExtraDetailsFragment extends Fragment implements OptionItemAdapter.onItemSelectListener {

    FragmentExtraDetailsBinding mBinding;
    List<String> selectedBuildingType = new ArrayList<>();
    private User mUser;
    private OnFragmentInteractionListener mOnFragmentInteractionListener;
    private Uri imageUri;
    private String itemList = "";
    private boolean isDataValid = false;
    private boolean isOptionsOpened = false;

    public ExtraDetailsFragment() {
        // Required empty public constructor
    }

    public ExtraDetailsFragment(User user,OnFragmentInteractionListener onFragmentInteractionListener) {

        mUser = user;
        mOnFragmentInteractionListener = onFragmentInteractionListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = FragmentExtraDetailsBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserData();
                if (mUser!=null && isDataValid)
                    mOnFragmentInteractionListener.onContinuePressed(mUser,imageUri);
                else showMessage("User data invalid!");
            }
        });
        mBinding.btSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipThisStep();
            }
        });
        mBinding.moreDetailsBuildingTypeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOptionsOpened) {
                    mBinding.rvMoreDetailsBuildingType.setVisibility(View.VISIBLE);
                    mBinding.tvExtraDetailsWorkTypeTitle.setVisibility(View.VISIBLE);
                    setupBuildingOptions();
                    isOptionsOpened = true;
                }
                else {
                    mBinding.rvMoreDetailsBuildingType.setVisibility(View.GONE);
                    mBinding.tvExtraDetailsWorkTypeTitle.setVisibility(View.GONE);
                    isOptionsOpened = false;
                }
            }
        });
        mBinding.tvMoreDetailsIdUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
    }

    private void skipThisStep() {
        mOnFragmentInteractionListener.onSkipPressed();

    }

    private void setupBuildingOptions() {
        mBinding.rvMoreDetailsBuildingType.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,false));
        List<String> buildingTypeOption = new ArrayList<>();
        buildingTypeOption.add("Home");
        buildingTypeOption.add("Auditorium");
        buildingTypeOption.add("Bridge");
        buildingTypeOption.add("Shopping Mall");
        buildingTypeOption.add("Tunnel");
        buildingTypeOption.add("Stadium");

        OptionItemAdapter optionItemAdapter = new OptionItemAdapter(getContext(),buildingTypeOption,this,R.layout.card_option_item,true);
        mBinding.rvMoreDetailsBuildingType.setAdapter(optionItemAdapter);


    }

    private void getUserData() {
        isDataValid = false;

        if (mUser != null) {
            InputValidationhelper inputValidationhelper = new InputValidationhelper();

            if (inputValidationhelper.validate(mBinding.tvMoredetailsConstructionType, TYPE_TEXT)) {
                mUser.setBuildingType(selectedBuildingType);
                isDataValid = true;
            }
            else isDataValid = false;

            if (inputValidationhelper.validate(mBinding.etMoreDetailsNumOfWorkers, TYPE_DIGIT)) {
                mUser.setNumberOfWorkers(
                        Integer.parseInt(mBinding.etMoreDetailsNumOfWorkers.getText().toString()));
                isDataValid = true;
            }
            else isDataValid = false;

            if (inputValidationhelper.validate(mBinding.etMoreDetailsRatePs, TYPE_DIGIT)){
                mUser.setFeePerHour(
                        Float.parseFloat(mBinding.etMoreDetailsRatePs.getText().toString()));
            isDataValid = true;
        }
        else isDataValid = false;

            /*if (imageUri==null){
                mBinding.tvMoreDetailsIdProofText.setError("Please select ID proof");

                isDataValid = false;
            }
            else isDataValid = true;*/
        }


    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE) {
            if (data != null) {
                Uri selectedImage = data.getData();
                mBinding.tvMoreDetailsIdUpload.setVisibility(View.GONE);
                customCardView(mBinding.moreDetailsIdImageCard);
                mBinding.moreDetailsIdImageCard.setVisibility(View.VISIBLE);
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .placeholder(R.drawable.ic_image_black_24dp)
                        .error(R.drawable.ic_broken_image_black_24dp)
                        .into(mBinding.ivMoreDetailsIdImage);
                /* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*/
                imageUri = selectedImage;

            } else {
                showMessage("Please select a photo!");
            }
        }
    }
    void showMessage(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(String item) {
        if (itemList.length()!=0)
        itemList = itemList+"\n"+item;
        else itemList = item;

        mBinding.tvMoredetailsConstructionType.setText(itemList);
        selectedBuildingType.add(item);
    }

    @Override
    public void onItemRemoved(String item) {
        if (itemList.length()!=0)
           itemList = itemList.replace(item+'\n',"");


        mBinding.tvMoredetailsConstructionType.setText(itemList);
        selectedBuildingType.remove(item);
    }
    public interface OnFragmentInteractionListener{
        void onContinuePressed(User user,Uri uri);
        void onSkipPressed();
    }
    void customCardView(MaterialCardView cardView){
        float radius = 28;
        cardView.setShapeAppearanceModel(
                cardView.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopLeftCorner(CornerFamily.ROUNDED,0)
                        .setTopRightCorner(CornerFamily.ROUNDED,0)
                        .setBottomRightCorner(CornerFamily.ROUNDED,radius)
                .setBottomLeftCornerSize(radius)
                .build());
    }
}
