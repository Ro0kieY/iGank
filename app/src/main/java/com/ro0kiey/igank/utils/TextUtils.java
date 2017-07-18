package com.ro0kiey.igank.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;

import com.ro0kiey.igank.model.Bean.GankBean;

/**
 * 文本工具类
 * Created by Ro0kieY on 2017/7/18.
 */

public class TextUtils {

    private TextUtils() {}

    public static SpannableString getGankStyleText(GankBean gankBean){
        String gankStyle = gankBean.getDesc() + " @" + gankBean.getWho();
        SpannableString spannableString = new SpannableString(gankStyle);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), gankBean.getDesc().length() + 1, gankStyle.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), gankBean.getDesc().length() + 1, gankStyle.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        return spannableString;
    }
}
