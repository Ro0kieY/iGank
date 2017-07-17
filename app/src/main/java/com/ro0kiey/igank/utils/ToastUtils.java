package com.ro0kiey.igank.utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Toast工具类
 * Created by Ro0kieY on 2017/7/16.
 */

public class ToastUtils {

    private ToastUtils() {}


    /**
     * 显示short message
     * @param context
     * @param toastText
     */
    public static void ToastShort(Context context, String toastText){
        Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示short message
     * @param context
     * @param resId
     */
    public static void ToastShort(Context context, int resId){
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }


    /**
     * 显示long message
     * @param context
     * @param toastText
     */
    public static void ToastLong(Context context, String toastText){
        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示long message
     * @param context
     * @param resId
     */
    public static void ToastLong(Context context, int resId){
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 显示short snackbar message
     * @param view
     * @param text
     */
    public static void SnackBarShort(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * * 显示short snackbar message
     * @param view
     * @param resId
     */
    public static void SnackBarShort(View view, int resId){
        Snackbar.make(view, resId, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * 显示带按钮的snackbar message
     * @param view
     * @param text
     * @param actionText
     * @param listener
     */
    public static void SnackBarWithAction(View view, String text, String actionText, View.OnClickListener listener){
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setAction(actionText, listener).show();
    }

    /**
     * 显示带按钮的snackbar message
     * @param view
     * @param text
     * @param actionResId
     * @param listener
     */
    public static void SnackBarWithAction(View view, String text, int actionResId, View.OnClickListener listener){
        Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE).setAction(actionResId, listener).show();
    }

    /**
     * 显示带按钮的snackbar message
     * @param view
     * @param resId
     * @param actionText
     * @param listener
     */
    public static void SnackBarWithAction(View view, int resId, String actionText, View.OnClickListener listener){
        Snackbar.make(view, resId, Snackbar.LENGTH_INDEFINITE).setAction(actionText, listener).show();
    }

    /**
     * 显示带按钮的snackbar message
     * @param view
     * @param resId
     * @param actionResId
     * @param listener
     */
    public static void SnackBarWithAction(View view, int resId, int actionResId, View.OnClickListener listener){
        Snackbar.make(view, resId, Snackbar.LENGTH_INDEFINITE).setAction(actionResId, listener).show();
    }
}
