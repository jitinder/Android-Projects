package com.example.android.miwok;

/**
 * Created by Sidak on 26-08-2017.
 */

public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceID = 0;
    private int mSoundID = 0;

    public Word(String defaultTranslation, String miwokTranslation, int soundID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mSoundID = soundID;
    }

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceID, int soundID){
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mImageResourceID = imageResourceID;
        mSoundID = soundID;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }

    public int getImageResourceID(){
        return mImageResourceID;
    }

    public int getSoundID(){
        return mSoundID;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mImageResourceID=" + mImageResourceID +
                ", mSoundID=" + mSoundID +
                '}';
    }
}
