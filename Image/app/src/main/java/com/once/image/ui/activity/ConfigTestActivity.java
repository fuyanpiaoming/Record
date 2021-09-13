package com.once.image.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.once.image.R;


public class ConfigTestActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_test);
        TextView textView = findViewById(R.id.config_tv);
        Configuration configuration = getResources().getConfiguration();
        String strConfig = "Configuration{" + "\n" +
                "    mcc" + ":" + configuration.mcc + "\n" +
                "    mnc" + ":" + configuration.mnc + "\n" +
                "    densityDpi" + ":" + configuration.densityDpi + "\n" +
                "    colorMode" + ":" + configuration.colorMode + "\n" +
                "    fontScale" + ":" + configuration.fontScale + "\n" +
                "    navigation" + ":" + configuration.navigation + "\n" +
                "    navigationHidden" + ":" + configuration.navigationHidden + "\n" +
                "    orientation" + ":" + configuration.orientation + "\n" +
                "    screenHeightDp" + ":" + configuration.screenHeightDp + "\n" +
                "    screenWidthDp" + ":" + configuration.screenWidthDp + "\n" +
                "    screenLayout" + ":" + configuration.screenLayout + "\n" +
                "    smallestScreenWidthDp" + ":" + configuration.smallestScreenWidthDp + "\n" +
                "    uiMode" + ":" + configuration.uiMode + "\n" +
                "    locale" + ":" + configuration.locale + "\n" +
                "    keyboard" + ":" + configuration.keyboard + "\n" +
                "    keyboardHidden" + ":" + configuration.keyboardHidden + "\n" +
                "    hardKeyboardHidden" + ":" + configuration.hardKeyboardHidden + "\n" +
                "    isScreenRound" + ":" + configuration.isScreenRound() + "\n" + "}";

        StringBuilder stringBuilder1 = new StringBuilder();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        stringBuilder1.append("DisplayMetrics{").append("\n")
                .append("    density=").append(displayMetrics.density).append("\n")
                .append("    densityDpi=").append(displayMetrics.densityDpi).append("\n")
                .append("    xdpi=").append(displayMetrics.xdpi).append("\n")
                .append("    ydpi=").append(displayMetrics.ydpi).append("\n")
                .append("    widthPixels=").append(displayMetrics.widthPixels).append("\n")
                .append("    heightPixels=").append(displayMetrics.heightPixels).append("\n")
                .append("    scaledDensity=").append(displayMetrics.scaledDensity).append("\n")
                .append("}");
        textView.setText(String.format("%s\n\n%s", strConfig, stringBuilder1.toString()));
    }
}
