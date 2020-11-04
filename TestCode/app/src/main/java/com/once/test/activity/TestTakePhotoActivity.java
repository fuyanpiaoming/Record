package com.once.test.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.once.test.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestTakePhotoActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = TestTakePhotoActivity.class.getSimpleName();

    private ImageView imageView;
    private Button button;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_take_photo);

        imageView = findViewById(R.id.img_picture);
        button = findViewById(R.id.btn_take_photo);

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        File file = new File(getExternalCacheDir(),"book.jpg");
        Log.i(TAG,"onClick]file path=" + file.getAbsolutePath().toString());
        try{
            if(file.exists()){
                file.delete();
            }
            file.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        if(Build.VERSION.SDK_INT >= 24){
            uri = FileProvider.getUriForFile(this,"com.once.test.fileprovider",file);
        }else{
            uri = Uri.fromFile(file);
        }
        Log.i(TAG,"[onClick]uri=" + uri);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    try{
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}