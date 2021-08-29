package com.once.image.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.once.image.MainActivity;
import com.once.image.R;
import com.once.image.ui.home.HomeMode;

import java.util.ArrayList;
import java.util.List;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<List<GalleryMode>>mutableLiveData;

    public GalleryViewModel() {
        mutableLiveData = new MutableLiveData<>();
        List<GalleryMode>modeList = new ArrayList<>();
        modeList.add(new GalleryMode("Activity" , MainActivity.class, R.drawable.leave19));
        modeList.add(new GalleryMode("BroadcastReceiver" , MainActivity.class, R.drawable.leave20));
        modeList.add(new GalleryMode("Service" , MainActivity.class, R.drawable.leave21));
        modeList.add(new GalleryMode("ContentProvider" , MainActivity.class, R.drawable.leave22));
        modeList.add(new GalleryMode("Fragment" , MainActivity.class, R.drawable.leave24));
        modeList.add(new GalleryMode("Intent" , MainActivity.class, R.drawable.leave25));
        modeList.add(new GalleryMode("Dialog" , MainActivity.class, R.drawable.leave26));
        mutableLiveData.setValue(modeList);
    }

    public LiveData<List<GalleryMode>> getGalleryModeList(){
        return mutableLiveData;
    }
}