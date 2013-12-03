package com.wz.app;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.SeekBar;
import com.wz.app.image.ImageViewTouch;
import com.wz.app.image.ImageViewTouchBase;

/**
 * Created by wuzhi on 13-12-2.
 */
public class BlurActivity extends Activity {

    private StackBlurManager stackBlurManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blur_layout);

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final ImageViewTouch imageView = (ImageViewTouch) findViewById(R.id.imageView);

        String imagePath = getIntent().getStringExtra("path");
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        stackBlurManager = new StackBlurManager(bitmap);


        imageView.setImageBitmap( bitmap, null, ImageViewTouchBase.ZOOM_INVALID, ImageViewTouchBase.ZOOM_INVALID );
//        imageView.setImageBitmap(bitmap);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                stackBlurManager.process(progress*5);
                imageView.setImageBitmap(stackBlurManager.returnBlurredImage() );
            }
        });
    }
}
