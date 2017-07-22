package com.ro0kiey.igank.mvp;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.mvp.presenter.MeiziPresenter;
import com.ro0kiey.igank.mvp.view.IMeiziView;
import com.ro0kiey.igank.utils.ShareUtils;
import com.ro0kiey.igank.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeiziActivity extends BaseActivity<MeiziPresenter> implements IMeiziView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.meizi_image)
    ImageView imageView;
    private String meiziUrl;
    private MeiziPresenter mPresenter;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        initView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_meizi;
    }

    @Override
    protected void initPresenter() {
        this.mPresenter = new MeiziPresenter(this, this);
    }

    private void initView() {

        meiziUrl = getIntent().getStringExtra("Url");
        ViewCompat.setTransitionName(imageView, Config.ACTIVITY_IMAGE_TRANS);
        //imageView.setDrawingCacheEnabled(true);
        //Glide.with(this).load(meiziUrl).override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).into(imageView);
        //imageView.setDrawingCacheEnabled(false);

        //imageView转场动画有闪烁，做了许多尝试，目前来看还没有得到完美的解决
        //imageView.setImageDrawable(SharedElement.SharedDrawable);
        //Glide.with(this).load(meiziUrl).into(imageView);
        //imageView.setImageBitmap(SharedElement.SharedBitmap);
        Glide.with(this).load(meiziUrl).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                imageView.setImageBitmap(resource);
                bitmap = resource;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meizi_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            case R.id.save_meizi:
                mPresenter.saveMeiziImage(bitmap, meiziUrl);
                break;
            case R.id.share:
                mPresenter.shareMeiziImage(bitmap, meiziUrl);
                break;
            default:
                break;
        }
        return false;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showSaveMeiziSuccess() {
        ToastUtils.SnackBarShort(imageView, R.string.save_success);
    }

    @Override
    public void showHasSaved() {
        ToastUtils.SnackBarShort(imageView, R.string.already_saved);
    }

    @Override
    public void showErrorView() {

    }

    @Override
    public void showSaveMeiziFailed() {
        ToastUtils.SnackBarShort(imageView, R.string.girl_reject);
    }

    @Override
    public void shareMeiziImage(Uri uri) {
        ShareUtils.shareImage(this, uri, R.string.share_meizi_to_friend);
    }
}
