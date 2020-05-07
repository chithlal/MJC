package com.developer.chithlal.mjc.app.BuildingSpec;

import static com.developer.chithlal.mjc.app.util.Constants.CONST_TYPE_DEFAULT;
import static com.developer.chithlal.mjc.app.util.Constants.CONST_TYPE_FULL_CONST;
import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.engineer.HireEngineer;
import com.developer.chithlal.mjc.app.util.Constants;
import com.developer.chithlal.mjc.databinding.ActivityBuildingSpecsBinding;

import java.util.List;


public class BuildingSpecActivity extends AppCompatActivity implements BuildingSpecContract.View{
    private Toolbar mToolbar;
    ActivityBuildingSpecsBinding mBBinding;
    MaterialAdapter mMaterialAdapter;
    BuildingSpecPresenter mBuildingSpecPresenter;
    private ConstructionType mConstructionType;
    private boolean isAddMeasurementOpen = false; // to keep track the state of measurement layout
    private boolean isBuyMaterialIsOpen = false; // to keep track of buy material state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mBBinding = ActivityBuildingSpecsBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(mBBinding.getRoot());
        mToolbar = mBBinding.specToolbar;


        //Toolbar setup
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setTitle("");
        mToolbar.setEnabled(true);
        setSupportActionBar(mToolbar);
        ActionBar mActionBar = getSupportActionBar();
        if (mActionBar!=null)
            mActionBar.setDisplayHomeAsUpEnabled(true);

        mConstructionType = new ConstructionType(CONST_TYPE_DEFAULT);

        mBBinding.bsRvMaterialList.setLayoutManager(new LinearLayoutManager(this));
        mBuildingSpecPresenter = new BuildingSpecPresenter(this,this);
        mBuildingSpecPresenter.setupViews();
        //listener for the building type selection
        mBBinding.bsTvWorkTypeFullConstr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConstructionType.selectedType!= ConstructionType.ConstructionTypes.FULL_CONSTRUCTION) {
                    selectConstructionType(ConstructionType.ConstructionTypes.FULL_CONSTRUCTION);
                    mConstructionType.setSelectedType(ConstructionType.ConstructionTypes.FULL_CONSTRUCTION);
                }

                else  selectConstructionType(ConstructionType.ConstructionTypes.DEFAULT);


            }
        });
        mBBinding.bsTvWorkTypeMainConc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConstructionType.selectedType!= ConstructionType.ConstructionTypes.MAIN_CONCRETE) {
                    selectConstructionType(ConstructionType.ConstructionTypes.MAIN_CONCRETE);
                    mConstructionType.setSelectedType(ConstructionType.ConstructionTypes.MAIN_CONCRETE);
                }
                else  selectConstructionType(ConstructionType.ConstructionTypes.DEFAULT);


            }
        });
        mBBinding.bsTvWorkTypeFinishing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConstructionType.selectedType!= ConstructionType.ConstructionTypes.FINISHING_WORK)
                {
                    mConstructionType.setSelectedType(ConstructionType.ConstructionTypes.FINISHING_WORK);
                    selectConstructionType(ConstructionType.ConstructionTypes.FINISHING_WORK);
                }

                else  selectConstructionType(ConstructionType.ConstructionTypes.DEFAULT);


            }
        });
        mBBinding.bsCvHireEngineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HireEngineer.class);
                startActivity(intent);
            }
        });

        mBBinding.bsLlAddMeasurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAddMeasurementOpen) {
                    mBBinding.bsLlAddMeasurementExpansion.setVisibility(View.GONE);
                    isAddMeasurementOpen = false;
                }
                else {
                    mBBinding.bsLlAddMeasurementExpansion.setVisibility(View.VISIBLE);
                    isAddMeasurementOpen = true;
                }

            }
        });
        mBBinding.bsLlBuyMaterial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBuyMaterialIsOpen) {
                    mBBinding.bsLlBuyMaterialExpansion.setVisibility(View.GONE);
                    isBuyMaterialIsOpen = false;
                }
                else{
                    mBBinding.bsLlBuyMaterialExpansion.setVisibility(View.VISIBLE);
                    isBuyMaterialIsOpen = true;
                }

            }
        });
        mBBinding.tvBsUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });


    }

    @Override
    public ConstructionType getConstructionType() {
        if (mConstructionType!=null)
        return mConstructionType;
        else return new ConstructionType(CONST_TYPE_DEFAULT);
    }

    @Override
    public Measurements getMeasurements() {
        return null;
    }

    @Override
    public void setupMaterial(List<Materials> materialsList) {
        mMaterialAdapter = new MaterialAdapter(this,materialsList);
        mBBinding.bsRvMaterialList.setAdapter(mMaterialAdapter);

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    void setupConstructionTypeSelector(){

    }
    boolean selectConstructionType(ConstructionType.ConstructionTypes constructionTypes){
        if (constructionTypes==ConstructionType.ConstructionTypes.FULL_CONSTRUCTION){
            mBBinding.bsTvWorkTypeFullConstr.setBackground(getResources().getDrawable(R.drawable.worktype_shape_pressed));
            mBBinding.bsTvWorkTypeFinishing.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));
            mBBinding.bsTvWorkTypeMainConc.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));

            mBBinding.bsTvWorkTypeFullConstr.setTextColor(getResources().getColor(R.color.white));
            mBBinding.bsTvWorkTypeFinishing.setTextColor(getResources().getColor(R.color.app_black));
            mBBinding.bsTvWorkTypeMainConc.setTextColor(getResources().getColor(R.color.app_black));

        }
        else if (constructionTypes==ConstructionType.ConstructionTypes.MAIN_CONCRETE){
            mBBinding.bsTvWorkTypeFullConstr.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));
            mBBinding.bsTvWorkTypeFinishing.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));
            mBBinding.bsTvWorkTypeMainConc.setBackground(getResources().getDrawable(R.drawable.worktype_shape_pressed));

            mBBinding.bsTvWorkTypeFullConstr.setTextColor(getResources().getColor(R.color.app_black));
            mBBinding.bsTvWorkTypeFinishing.setTextColor(getResources().getColor(R.color.app_black));
            mBBinding.bsTvWorkTypeMainConc.setTextColor(getResources().getColor(R.color.white));
        }
        else if (constructionTypes==ConstructionType.ConstructionTypes.FINISHING_WORK){
            mBBinding.bsTvWorkTypeFullConstr.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));
            mBBinding.bsTvWorkTypeFinishing.setBackground(getResources().getDrawable(R.drawable.worktype_shape_pressed));
            mBBinding.bsTvWorkTypeMainConc.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));

            mBBinding.bsTvWorkTypeFullConstr.setTextColor(getResources().getColor(R.color.app_black));
            mBBinding.bsTvWorkTypeFinishing.setTextColor(getResources().getColor(R.color.white));
            mBBinding.bsTvWorkTypeMainConc.setTextColor(getResources().getColor(R.color.app_black));
        }
        else{
            mBBinding.bsTvWorkTypeFullConstr.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));
            mBBinding.bsTvWorkTypeFinishing.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));
            mBBinding.bsTvWorkTypeMainConc.setBackground(getResources().getDrawable(R.drawable.worktype_shape_normal));

            mBBinding.bsTvWorkTypeFullConstr.setTextColor(getResources().getColor(R.color.app_black));
            mBBinding.bsTvWorkTypeFinishing.setTextColor(getResources().getColor(R.color.app_black));
            mBBinding.bsTvWorkTypeMainConc.setTextColor(getResources().getColor(R.color.app_black));
        }

        return true;
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
                mBBinding.tvBsUpload.setVisibility(View.GONE);
                Glide.with(this)
                        .load(selectedImage)
                        .centerCrop()
                        .into(mBBinding.ivBsPlanPlaceHolder);
                /* mBinding.ivUserProfileIdProofImage.setImageURI(selectedImage);*/
                mBBinding.tvBsUpload.setVisibility(View.GONE);
                mBBinding.ivBsPlanPlaceHolder.setVisibility(View.VISIBLE);
            }
            else {
                showMessage("Please select a photo!");
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
