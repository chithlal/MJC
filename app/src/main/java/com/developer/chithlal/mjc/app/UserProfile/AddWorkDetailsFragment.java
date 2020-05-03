package com.developer.chithlal.mjc.app.UserProfile;

import static com.developer.chithlal.mjc.app.util.Constants.GALLERY_REQUEST_CODE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.developer.chithlal.mjc.R;
import com.developer.chithlal.mjc.app.util.InputValidationhelper;
import com.developer.chithlal.mjc.databinding.FragmentAddWorkDetailsBinding;
import com.developer.chithlal.mjc.root.App;
import com.developer.chithlal.mjc.root.di.ObjectFactory;

import java.util.List;

import javax.inject.Inject;


public class AddWorkDetailsFragment extends Fragment implements ImageUploderAdapter.AddButtonClickListener, AddworkContract.View,OptionItemAdapter.onItemSelectListener{

    @Inject
    AddworkContract.Presenter mAddWorkPresenter;

    OptionItemAdapter mOptionItemAdapter;
    FragmentAddWorkDetailsBinding mFragmentAddWorkDetailsBinding;
    private ImageUploderAdapter mImageUploderAdapter;

    List<Uri> mImageList;
    private AddWorkListener mAddWorkListener;


    public AddWorkDetailsFragment(AddWorkListener addWorkListener) {
        // Required empty public constructor
        mAddWorkListener = addWorkListener;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentAddWorkDetailsBinding = FragmentAddWorkDetailsBinding.inflate(getLayoutInflater());

        ObjectFactory.getFragmentComponent().inject(this);
        return mFragmentAddWorkDetailsBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAddWorkPresenter.setUi(this,getContext());
        InputValidationhelper inputValidationhelper = new InputValidationhelper();
        mFragmentAddWorkDetailsBinding.btAddWorkSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Work work = new Work();
                int i=0;
                String worktype = mFragmentAddWorkDetailsBinding.tvAddWorkWorkType.getText().toString();
                if (validateAndShowError(worktype,mFragmentAddWorkDetailsBinding.tvAddWorkWorkType)){
                    work.setWorkType(worktype);
                    i++;
                }
                String dateOfCompletion = mFragmentAddWorkDetailsBinding.etAddWorkDateOfCompletion.getText().toString();
                if (inputValidationhelper.validate(mFragmentAddWorkDetailsBinding.etAddWorkDateOfCompletion,InputValidationhelper.TYPE_DATE)){
                    work.setFinishingDate(dateOfCompletion);
                    i++;
                }
                String workDescrip = mFragmentAddWorkDetailsBinding.etAddWorkDescription.getText().toString();
                if (validateAndShowError(workDescrip,mFragmentAddWorkDetailsBinding.etAddWorkDescription)){
                    work.setDescription(workDescrip);
                    i++;
                }
                String workArea = mFragmentAddWorkDetailsBinding.etAddWorkWorkArea.getText().toString();
                if (validateAndShowError(workArea,mFragmentAddWorkDetailsBinding.etAddWorkWorkArea)){
                    work.setConstructionArea(workArea);
                    i++;
                }

                String workName = mFragmentAddWorkDetailsBinding.etAddWorkWorkName.getText().toString();
                if (validateAndShowError(workName,mFragmentAddWorkDetailsBinding.etAddWorkWorkName)){
                    work.setWorkName(workName);
                    i++;
                }

                String ownerName = mFragmentAddWorkDetailsBinding.etAddWorkWorkOwnerName.getText().toString();
                if (validateAndShowError(ownerName,mFragmentAddWorkDetailsBinding.etAddWorkWorkOwnerName)){
                    work.setOwnerName(ownerName);
                    i++;
                }


                if (i==6)
                    mAddWorkPresenter.onSaveClicked(work);


            }
        });
        mImageUploderAdapter = new ImageUploderAdapter(getContext(),this);

        mFragmentAddWorkDetailsBinding.rvAddWorkAddImage.setLayoutManager(new GridLayoutManager(getContext(),3));
        mFragmentAddWorkDetailsBinding.rvAddWorkAddImage.setAdapter(mImageUploderAdapter);
    }

    @Override
    public void setWorkTypeOptions(List<String> workTypeOptions) {

        mOptionItemAdapter = new OptionItemAdapter(getContext(),workTypeOptions,this,R.layout.card_option_item);
        mFragmentAddWorkDetailsBinding.rvAddWorkWorkType.setLayoutManager(new LinearLayoutManager(getContext(),
                RecyclerView.HORIZONTAL,false));
        mFragmentAddWorkDetailsBinding.rvAddWorkWorkType.setAdapter(mOptionItemAdapter);


    }

    @Override
    public void setMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void setWork(Work work) {
        mAddWorkListener.onWorkAdded(work);
    }

    @Override
    public void onItemSelected(String profession) {
        mFragmentAddWorkDetailsBinding.tvAddWorkWorkType.setText(profession);

    }

    boolean validateAndShowError(String string, TextView view){
        boolean isValid = true;
        if (string.isEmpty())
        {
            isValid =false;
            view.setError("Field must not be empty!");
        }
        return isValid;


    }

    @Override
    public void onAddButtonClick() {
        selectImage();
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_REQUEST_CODE){
            if (data!=null) {
                Uri selectedImage = data.getData();
                mImageUploderAdapter.addImage(selectedImage);
            }
            else setMessage("Please select a photo");

        }
    }

    interface AddWorkListener{
        void onWorkAdded(Work work);
    }

}
