package com.once.test.math;

import android.app.Activity;
import android.os.Bundle;
import com.once.test.R;
import com.once.test.widget.XfmodeView1;

public class TestXfmodeActivity extends Activity {

    private XfmodeView1 xfmodeView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_xfmode);
        xfmodeView1 = findViewById(R.id.test_xfmode_view);
    }
}
