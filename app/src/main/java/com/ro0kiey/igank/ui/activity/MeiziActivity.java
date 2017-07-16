package com.ro0kiey.igank.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
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
        imageView.setDrawingCacheEnabled(true);
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
                checkPermission();
                break;
            default:
                break;
        }
        return false;
    }

    private void checkPermission(){
        if(ContextCompat.checkSelfPermission(MeiziActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MeiziActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            saveMeiziImage(meiziUrl);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    saveMeiziImage(meiziUrl);
                }
                break;
            default:
                break;
        }
    }

    private void saveMeiziImage(String url) {
        Bitmap bitmap = imageView.getDrawingCache();
        File appDir = new File(Environment.getExternalStorageDirectory().getPath(), "igank/");
        if (!appDir.exists()){
            appDir.mkdirs();
        }
        String fileName = url.substring(8, url.length() - 4).replace("/", "-") + ".jpg";
        File file = new File(appDir, fileName);
        if (!file.exists()){
            try {
                FileOutputStream os = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
            ToastUtils.SnackBarShort(imageView, R.string.save_success);
        } else {
            ToastUtils.SnackBarShort(imageView, R.string.already_saved);
        }
        imageView.setDrawingCacheEnabled(false);
        Log.d("MeiziActivity", "saveMeiziImage: ");
    }

}
