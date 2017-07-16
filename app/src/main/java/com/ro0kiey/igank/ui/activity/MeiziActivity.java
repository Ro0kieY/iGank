package com.ro0kiey.igank.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.SharedElement;
import com.ro0kiey.igank.ui.base.BaseActivity;
import com.ro0kiey.igank.utils.ToastUtils;

import java.io.File;
import java.io.FileOutputStream;

public class MeiziActivity extends BaseActivity {

    private ImageView imageView;
    private String meiziUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = (ImageView)findViewById(R.id.meizi_image);
        meiziUrl = getIntent().getStringExtra("Url");
        imageView.setImageDrawable(SharedElement.SharedDrawable);
        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        //Glide.with(this).load(meiziUrl).into(imageView);

        //imageView.setImageBitmap(SharedElement.SharedBitmap);

        Glide.with(this).load(meiziUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
            }
        });


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meizi;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meizi_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_meizi:
                saveMeiziImage(meiziUrl);
                ToastUtils.SnackBarShort(imageView, R.string.save_success);
                break;
            default:
                break;
        }
        return false;
    }

    private void saveMeiziImage(String url) {
        Bitmap bitmap = imageView.getDrawingCache();
        File appDir = new File(Environment.getExternalStorageDirectory(), "igank");
        if (!appDir.exists()){
            appDir.mkdirs();
        }
        String fileName = url.substring(8).replace("/", "-") + "girl.jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream os = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //imageView.setDrawingCacheEnabled(false);
        Log.d("MeiziActivity", "saveMeiziImage: ");
        this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }
}
