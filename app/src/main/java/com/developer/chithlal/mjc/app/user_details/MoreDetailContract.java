package com.developer.chithlal.mjc.app.user_details;

import android.content.Context;
import android.net.Uri;

import com.developer.chithlal.mjc.app.engineer.User;

public interface MoreDetailContract {
    interface View{
        void showExtraDetailsFragment();
        void showWorkDetailFragment();
        void showMessage(String message);
        void dismissDialog();
        void onRegistrationFinished();
        Uri getIdProofUri();
        void setIDProofURLAfterUploading(String url);
        void showToast(String message);


    }

    interface Presenter{
        void setContext(Context context);
        void onExtraDetailsCollected(User user);
        void onWorkAdded(User user);
        void onRegistrationFinished(User user);
        void startUI(MoreDetailContract.View view);

    }
}
