package com.developer.chithlal.mjc.app.database;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.developer.chithlal.mjc.app.engineers_list.User;
import com.developer.chithlal.mjc.app.util.Constants;
import com.developer.chithlal.mjc.app.util.SharedPreferenceManger;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class DatabaseHandler {
    Realm mRealm;

    public DatabaseHandler() {
        mRealm = Realm.getDefaultInstance();
    }
    public boolean saveUser(User user){
        final boolean[] isSuccess = {false};

        RealmUser realmUser = new RealmUser(user);
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmUser newUser = realm.copyToRealmOrUpdate(realmUser);
            }
        },new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
                isSuccess[0] = true;
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
                Log.d(TAG, "onError: Realm Error: "+error.getLocalizedMessage());
                isSuccess[0] = false;
            }});
        return isSuccess[0];
    }
    public User getAdminUser(Context context){
        User user;
        SharedPreferenceManger sharedPreferenceManger = new SharedPreferenceManger(context,
                Constants.user_details_shared_pref_USER_ID);
        sharedPreferenceManger.initPreference();
        String uid = sharedPreferenceManger.getUserID();
        if (uid!=null){
            RealmQuery<RealmUser> query = mRealm.where(RealmUser.class);
            query.equalTo("userId",uid);
            RealmResults<RealmUser> queryResult = query.findAll();
            if (queryResult.size()>0)
                user = new User(queryResult.get(0));
            else {
                user = new User("Invalid user");
            }
        }
        else {
            user = new User("Invalid user");
        }

        return user;
    }
    public User getAdminUser(String uid){

        return null;
    }

    public boolean deleteAdminUser(Context context){
        User user;
        final boolean[] isDeleted = {false};
        SharedPreferenceManger sharedPreferenceManger = new SharedPreferenceManger(context,
                Constants.user_details_shared_pref_USER_ID);
        sharedPreferenceManger.initPreference();
        String uid = sharedPreferenceManger.getUserID();
        final RealmQuery<RealmUser> adminUserQuery = mRealm.where(RealmUser.class);
        adminUserQuery.equalTo("userId",uid);
        final RealmResults<RealmUser> result = adminUserQuery.findAll();
            mRealm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    isDeleted[0] = result.deleteAllFromRealm();

                }
            });

        return isDeleted[0];
    }
    public void updateAdminUser(User user){

    }


}
