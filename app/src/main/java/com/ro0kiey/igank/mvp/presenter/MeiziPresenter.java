package com.ro0kiey.igank.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.mvp.view.IMeiziView;
import com.ro0kiey.igank.utils.FileUtils;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * MeiziActivity的Presenter
 * Created by Ro0kieY on 2017/7/22.
 */

public class MeiziPresenter extends BasePresenter<IMeiziView> {

    public MeiziPresenter(IMeiziView iView, Context context) {
        super(iView, context);
    }

    public void saveMeiziImage(final Bitmap bitmap, final String name){

        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                if(bitmap != null){
                    e.onNext(bitmap);
                } else {
                    e.onError(new Throwable(context.getString(R.string.girl_reject)));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        File file = FileUtils.generateFilePath(name);
                        if (FileUtils.checkIsSaved(file)){
                            iView.showHasSaved();
                        } else {
                            Uri uri = FileUtils.saveBitmapToSDCard(bitmap, name);
                            iView.showSaveMeiziSuccess();
                            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showSaveMeiziFailed();
                    }
                });

    }

    public void shareMeiziImage(final Bitmap bitmap, final String name){
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Bitmap> e) throws Exception {
                if(bitmap != null){
                    e.onNext(bitmap);
                } else {
                    e.onError(new Throwable(context.getString(R.string.girl_reject)));
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(@NonNull Bitmap bitmap) throws Exception {
                        Uri uri = FileUtils.saveBitmapToSDCard(bitmap, name);
                        iView.shareMeiziImage(uri);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        iView.showErrorView();
                    }
                });
    }

}
