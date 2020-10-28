package com.once.test.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.once.test.R;

public class TestCollapsingToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_collapsing_toolbar);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int id = intent.getIntExtra("image_id",0);

        Toolbar toolbar = findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_toolbar_layout);
        ImageView imageView = findViewById(R.id.image_view);
        TextView textView = findViewById(R.id.tv_content);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        collapsingToolbarLayout.setTitle(name);
        Glide.with(this).load(id).into(imageView);
        String content =
                "长恨歌           \n\n" +
                "汉皇重色思倾国，御宇多年求不得。\n\n" +
                "杨家有女初长成，养在深闺人未识。\n\n" +
                "天生丽质难自弃，一朝选在君王侧。\n\n" +
                "回眸一笑百媚生，六宫粉黛无颜色。\n\n" +
                "春寒赐浴华清池，温泉水滑洗凝脂。\n\n" +
                "侍儿扶起娇无力，始是新承恩泽时。\n\n" +
                "云鬓花颜金步摇，芙蓉帐暖度春宵。\n\n" +
                "春宵苦短日高起，从此君王不早朝。\n\n" +
                "承欢侍宴无闲暇，春从春游夜专夜。";
        textView.setText(content);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return true;
    }
}