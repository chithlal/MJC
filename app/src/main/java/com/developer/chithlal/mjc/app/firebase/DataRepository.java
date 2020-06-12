package com.developer.chithlal.mjc.app.firebase;

import androidx.annotation.NonNull;

import com.developer.chithlal.mjc.app.UserProfile.UserProfilePresenter;
import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineer.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    FirebaseFirestore mFirestore;
    FirebaseStorage mFirebaseStorage;
    private workUpdateListener mWorkUpdateListener;
    private EngineersListUpdateListener mEngineersListUpdateListener;
    private DocumentSnapshot lastVisibleDocument = null;
    private boolean isEndOfList = false;


    public DataRepository(workUpdateListener workUpdateListener) {
        mWorkUpdateListener = workUpdateListener;
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
    }
    public DataRepository(EngineersListUpdateListener engineersListUpdateListener) {
        mEngineersListUpdateListener = engineersListUpdateListener;
        mFirebaseStorage = FirebaseStorage.getInstance();
        mFirestore = FirebaseFirestore.getInstance();
    }



    public void getWorks(String userId){
        List<Work> workList = new ArrayList<>();
        CollectionReference worksRef = mFirestore.collection("/App/root_app/works");

        Query query = worksRef.whereEqualTo("engineerId",userId);

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for(QueryDocumentSnapshot doc: task.getResult()){
                                workList.add(doc.toObject(Work.class));


                            }
                            mWorkUpdateListener.onWorksFetched(workList);
                        }
                        else mWorkUpdateListener.onWorkFetchFailed("Sorry..works not updated!");
                    }
                });



    }
    //This method fetches all engineers in the database No Sorting and filtering applied
    public void getAllEngineers(int pageNumber){
        int pageSize = 50;

        ParseUser parseUser = new ParseUser();
        List<User> engineerList = new ArrayList<>();
        CollectionReference engineerReference = mFirestore.collection("/App/root_app/users");



        if (lastVisibleDocument==null&&pageNumber  == 1&& !isEndOfList) {
            Query query = engineerReference.whereEqualTo("userMode",false)
                    .limit(pageSize);
            query.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()&&task.getResult()!=null) {
                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    User user = doc.toObject(User.class);
                                    engineerList.add(user);
                                }

                                mEngineersListUpdateListener.onEngineersListUpdated(engineerList,pageNumber);
                            } else mEngineersListUpdateListener.onEngineersListUpdateFailed(
                                    "Sorry..unable to fetch details!");
                        }
                    })
            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    lastVisibleDocument = queryDocumentSnapshots.getDocuments()
                            .get(queryDocumentSnapshots.size() -1);
                }
            });
        }
        else if (lastVisibleDocument!=null&& !isEndOfList){
            Query query = engineerReference.whereEqualTo("userMode",false)
                    .startAfter(lastVisibleDocument)
                    .limit(pageSize);
            query.get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()&&task.getResult()!=null) {

                                for (QueryDocumentSnapshot doc : task.getResult()) {
                                    User user = doc.toObject(User.class);
                                    engineerList.add(user);


                                }
                                mEngineersListUpdateListener.onEngineersListUpdated(engineerList,pageNumber);
                            } else mEngineersListUpdateListener.onEngineersListUpdateFailed(
                                    "Sorry..unable to fetch details!");
                        }
                    })
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            if (queryDocumentSnapshots!=null&&queryDocumentSnapshots.getDocuments().size()!=0) {
                                lastVisibleDocument = queryDocumentSnapshots.getDocuments()
                                        .get(queryDocumentSnapshots.size() - 1);
                            }
                            else {
                                isEndOfList = true;
                            }
                        }
                    });
        }

    }



    public interface workUpdateListener{
        void onWorksFetched(List<Work> workList);
        void onWorkFetchFailed(String message);
    }

    public interface EngineersListUpdateListener{
        void onEngineersListUpdated(List<User> engineersList,int pageNumber);
        void onEngineersListUpdateFailed(String message);
    }


}
