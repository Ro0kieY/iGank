package com.ro0kiey.igank.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
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
import com.ro0kiey.igank.utils.FileUtils;
import com.ro0kiey.igank.utils.ShareUtils;
import com.ro0kiey.igank.utils.ToastUtils;

import java.io.File;

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
            case R.id.share:
                imageView.setDrawingCacheEnabled(true);
                Bitmap bitmap = imageView.getDrawingCache();
                File file = FileUtils.generateFile("igank", meiziUrl);
                Uri uri = FileUtils.saveBitmapToSDCard(file, bitmap, meiziUrl);
                imageView.setDrawingCacheEnabled(false);
                ShareUtils.shareImage(this, uri, R.string.share_meizi_to_friend);
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
            imageView.setDrawingCacheEnabled(true);
            Bitmap bitmap = imageView.getDrawingCache();
            File file = FileUtils.generateFile("igank", meiziUrl);
            if (FileUtils.checkFileExisted(file)){
                ToastUtils.SnackBarShort(imageView, R.string.already_saved);
            } else {
                FileUtils.saveBitmapToSDCard(file, bitmap, meiziUrl);
                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
                ToastUtils.SnackBarShort(imageView, R.string.save_success);
                imageView.setDrawingCacheEnabled(false);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    imageView.setDrawingCacheEnabled(true);
                    Bitmap bitmap = imageView.getDrawingCache();
                    File file = FileUtils.generateFile("igank", meiziUrl);
                    FileUtils.saveBitmapToSDCard(file, bitmap, meiziUrl);
                    imageView.setDrawingCacheEnabled(false);
                }
                break;
            default:
                break;
        }
    }
}
