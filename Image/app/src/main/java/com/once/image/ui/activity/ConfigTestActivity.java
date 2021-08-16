package com.once.image.ui.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
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
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Config{").append("\n")
                .append("    mcc").append(":").append(configuration.mcc).append("\n")
                .append("    mnc").append(":").append(configuration.mnc).append("\n")
                .append("    densityDpi").append(":").append(configuration.densityDpi).append("\n")
                .append("    colorMode").append(":").append(configuration.colorMode).append("\n")
                .append("    fontScale").append(":").append(configuration.fontScale).append("\n")
                .append("    navigation").append(":").append(configuration.navigation).append("\n")
                .append("    navigationHidden").append(":").append(configuration.navigationHidden).append("\n")
                .append("    orientation").append(":").append(configuration.orientation).append("\n")
                .append("    screenHeightDp").append(":").append(configuration.screenHeightDp).append("\n")
                .append("    screenWidthDp").append(":").append(configuration.screenWidthDp).append("\n")
                .append("    screenLayout").append(":").append(configuration.screenLayout).append("\n")
                .append("    smallestScreenWidthDp").append(":").append(configuration.smallestScreenWidthDp).append("\n")
                .append("    uiMode").append(":").append(configuration.uiMode).append("\n")
                .append("    locale").append(":").append(configuration.locale).append("\n")
                .append("    keyboard").append(":").append(configuration.keyboard).append("\n")
                .append("    keyboardHidden").append(":").append(configuration.keyboardHidden).append("\n")
                .append("    hardKeyboardHidden").append(":").append(configuration.hardKeyboardHidden).append("\n")
                .append("    isScreenRound").append(":").append(configuration.isScreenRound()).append("\n").append("}");
        textView.setText(stringBuilder.toString());
    }
}
