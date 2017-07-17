package com.ro0kiey.igank.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 分享相关工具类
 * Created by Ro0kieY on 2017/7/16.
 */

public class ShareUtils {

    private ShareUtils() {}

    /**
     * 分享图片
     * @param context
     * @param uri
     * @param title
     */
    public static void shareImage(Context context, Uri uri, String title) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, title));
    }

    /**
     * 分享图片
     * @param context
     * @param uri
     * @param resId
     */
    public static void shareImage(Context context, Uri uri, int resId) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpeg");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(resId)));
    }

    /**
     * 分享App
     * @param context
     * @param resId
     * @param shareResId
     */
    public static void shareApp(Context context, int resId, int shareResId) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, context.getResources().getString(resId));
        shareIntent.setType("text/plain");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(shareResId)));
    }

    /**
     * 分享App
     * @param context
     * @param resId
     * @param shareString
     */
    public static void shareApp(Context context, int resId, String shareString) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, context.getResources().getString(resId));
        shareIntent.setType("text/plain");
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(shareIntent, shareString));
    }

    /*public static void shareGank(Context context, Gank gank) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, gank.desc + gank.url + context.getString(R.string.download_gank));
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_gank_to)));
    }

    public static void shareVideo(Context context, Gank gank) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, gank.desc + gank.url + context.getString(R.string.download_gank));
        intent.setType("text/plain");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_video_to)));
    }*/

}
