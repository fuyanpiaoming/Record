package com.once.image.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.once.image.MainActivity;
import com.once.image.R;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<HomeMode>>listHomeMode;

    public HomeViewModel() {
        initHomeMode();
    }

    private void initHomeMode(){
        listHomeMode = new MutableLiveData<>();
        List<HomeMode>homeModes = new ArrayList<>();

        homeModes.add(new HomeMode("Activity" , MainActivity.class, R.drawable.leave19));
        homeModes.add(new HomeMode("BroadcastReceiver" , MainActivity.class, R.drawable.leave20));
        homeModes.add(new HomeMode("Service" , MainActivity.class, R.drawable.leave21));
        homeModes.add(new HomeMode("ContentProvider" , MainActivity.class, R.drawable.leave22));
        homeModes.add(new HomeMode("Fragment" , MainActivity.class, R.drawable.leave24));
        homeModes.add(new HomeMode("Intent" , MainActivity.class, R.drawable.leave25));
        homeModes.add(new HomeMode("Dialog" , MainActivity.class, R.drawable.leave26));

        //将数据添加到MutableLiveData中
        listHomeMode.setValue(homeModes);
    }

    public LiveData<List<HomeMode>>getHomeModeList(){
        return listHomeMode;
    }
}