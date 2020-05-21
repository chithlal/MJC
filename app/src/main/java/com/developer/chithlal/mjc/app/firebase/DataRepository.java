package com.developer.chithlal.mjc.app.firebase;

import androidx.annotation.NonNull;

import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineer.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
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

    public DataRepository(workUpdateListener workUpdateListener) {
        mWorkUpdateListener = workUpdateListener;
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
                                mWorkUpdateListener.onWorksFetched(workList);

                            }

                        }
                        else mWorkUpdateListener.onWorkFetchFailed("Sorry..works not updated!");
                    }
                });



    }
    public void getAllEngineers(){

    }

    public interface workUpdateListener{
        void onWorksFetched(List<Work> workList);
        void onWorkFetchFailed(String message);
    }

    public interface EngineersListUpdateListener{
        void onEngineersListUpdated(List<User> engineersList);
        void onEngineersListUpdateFailed(String message);
    }
}
