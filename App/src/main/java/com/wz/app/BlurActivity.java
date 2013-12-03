package com.wz.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wuzhi on 13-12-2.
 */
public class BlurActivity extends Activity {

    private StackBlurManager _stackBlurManager;

    private String IMAGE_TO_ANALYZE = "android_platform_256.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        String imagePath = getIntent().getStringExtra("path");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        _stackBlurManager = new StackBlurManager(bitmap);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                _stackBlurManager.process(progress*5);
                imageView.setImageBitmap(_stackBlurManager.returnBlurredImage() );
            }
        });

    }
}
