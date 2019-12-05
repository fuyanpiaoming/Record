package com.once.test.designmode;

import android.app.Activity;
import android.os.Bundle;

import com.once.test.R;

//https://www.runoob.com/design-pattern/singleton-pattern.html

public class SingleInstanceModeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance_mode);
    }


}
