package com.developer.chithlal.mjc.app.firebase;

import com.developer.chithlal.mjc.app.work.Work;
import com.developer.chithlal.mjc.app.engineers_list.User;

import java.util.List;

public class ParseUser implements DataRepository.workUpdateListener  {
    private User mUser;
    private ParseListener mParseListener;
    private User parsedUser;
    private DataRepository mDataRepository;

    public ParseUser() {

    }

    public ParseUser(User user,ParseListener parseListener) {
        mUser = user;
        mParseListener = parseListener;
        mDataRepository = new DataRepository(this);
    }
    public void setUser(User user){
        mUser = user;
    }

    public User getParsedUser() {
        return parsedUser;
    }

    public void setParsedUser(User parsedUser) {
        this.parsedUser = parsedUser;
    }
    public void parse(){
        if (mUser != null){
            if (!mUser.isUserMode()) {
                parsedUser = mUser;
                if (parsedUser.getAllPreviousWorks() == null ) {
                    mDataRepository.getWorks(mUser.getUserId());
                }
                else{
                    mUser.setAllPreviousWorks(null);
                    mParseListener.onParsedDataArrived(mUser);

                }
            }
            else {
                parsedUser = mUser;
                mParseListener.onParsedDataArrived(mUser);
            }
        }
    }

    @Override
    public void onWorksFetched(List<Work> workList) {
        mUser.setAllPreviousWorks(workList);
        mParseListener.onParsedDataArrived(mUser);
    }

    @Override
    public void onWorkFetchFailed(String message) {
        mParseListener.onParseError(message);

    }
    public interface ParseListener{
        void onParsedDataArrived(User parsedUser);
        void onParseError(String message);
    }
}
