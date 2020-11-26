package com.once.test.mode;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableMode implements Parcelable {

    public ParcelableMode(){

    }

    public ParcelableMode(Parcel in) {

    }

    public static final Creator<ParcelableMode> CREATOR = new Creator<ParcelableMode>() {
        @Override
        public ParcelableMode createFromParcel(Parcel in) {
            return new ParcelableMode(in);
        }

        @Override
        public ParcelableMode[] newArray(int size) {
            return new ParcelableMode[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
