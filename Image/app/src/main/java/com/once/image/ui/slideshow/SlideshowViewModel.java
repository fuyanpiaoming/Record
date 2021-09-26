package com.once.image.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.once.image.MainActivity;
import com.once.image.R;
import com.once.image.ui.activity.AmsActivity;
import com.once.image.ui.activity.SensorActivity;
import com.once.image.ui.activity.TelephonyManActivity;

import java.util.ArrayList;
import java.util.List;

public class SlideshowViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<SlideMode>> mSlideModes;

    public SlideshowViewModel() {
        initTextView();
        initSlideList();
    }

    private void initTextView() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    private void initSlideList() {
        mSlideModes = new MutableLiveData<>();
        List<SlideMode> slideModes = new ArrayList<>();
        slideModes.add(new SlideMode("PowerManager", MainActivity.class, R.drawable.leave1));
        slideModes.add(new SlideMode("ActivityManager", AmsActivity.class, R.drawable.leave2));
        slideModes.add(new SlideMode("PackageManager", MainActivity.class, R.drawable.leave3));
        slideModes.add(new SlideMode("NotificationManager", MainActivity.class, R.drawable.leave4));
        slideModes.add(new SlideMode("AlarmManager", MainActivity.class, R.drawable.leave5));
        slideModes.add(new SlideMode("LocationManager", MainActivity.class, R.drawable.leave6));
        slideModes.add(new SlideMode("WindowManager", MainActivity.class, R.drawable.leave7));
        slideModes.add(new SlideMode("InputMethodManager", MainActivity.class, R.drawable.leave8));
        slideModes.add(new SlideMode("TelephonyManager", TelephonyManActivity.class, R.drawable.leave9));
        slideModes.add(new SlideMode("AccountManager", MainActivity.class, R.drawable.leave10));
        slideModes.add(new SlideMode("ClipboardManager", MainActivity.class, R.drawable.leave11));
        slideModes.add(new SlideMode("ConnectivityManager", MainActivity.class, R.drawable.leave12));
        slideModes.add(new SlideMode("WallpaperManager", MainActivity.class, R.drawable.leave13));
        slideModes.add(new SlideMode("AppWidgetManager", MainActivity.class, R.drawable.leave14));
        slideModes.add(new SlideMode("AudioManager", MainActivity.class, R.drawable.leave15));
        slideModes.add(new SlideMode("SensorManager", SensorActivity.class, R.drawable.leave16));
        slideModes.add(new SlideMode("StatusBarManager", MainActivity.class, R.drawable.leave17));
        slideModes.add(new SlideMode("AccessibilityManager", MainActivity.class, R.drawable.leave18));
        slideModes.add(new SlideMode("SearchManager", MainActivity.class, R.drawable.leave19));
        slideModes.add(new SlideMode("SmsManager", MainActivity.class, R.drawable.leave20));
        mSlideModes.setValue(slideModes);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<SlideMode>> getSlideList() {
        return mSlideModes;
    }
}