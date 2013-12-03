package com.wz.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class MainActivity extends Activity implements View.OnClickListener{

    private static final int PIC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_choose_pic:
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, PIC_REQUEST);
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == PIC_REQUEST) {
                String[] projection = {MediaStore.Images.Media.DATA};
                if (data != null && data.getData() != null) {
                    Cursor cursor = this.managedQuery(data.getData(), projection, null, null, null);
                    if (cursor != null && cursor.getCount() > 0) {
                        startManagingCursor(cursor);
                        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                        cursor.moveToFirst();
                        String imgPath = cursor.getString(column_index);
                        stopManagingCursor(cursor);
                        Intent intent = new Intent(MainActivity.this, BlurActivity.class);
                        intent.putExtra("path", imgPath);
                        startActivity(intent);
                    }
                }
            }

    }
}
