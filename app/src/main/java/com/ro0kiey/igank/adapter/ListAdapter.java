package com.ro0kiey.igank.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.ui.activity.WebActivity;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/12.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<GankBean> mGankBeanList;
    private int mLastPosition = -1;

    public ListAdapter(List<GankBean> gankBeanList) {
        this.mGankBeanList = gankBeanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_tablayout_fragment_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GankBean gankBean = mGankBeanList.get(position);
        String gankStyle = gankBean.getDesc() + " @" + gankBean.getWho();
        SpannableString spannableString = new SpannableString(gankStyle);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), gankBean.getDesc().length() + 1, gankStyle.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(Color.GRAY), gankBean.getDesc().length() + 1, gankStyle.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        holder.textView.setText(spannableString);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("VideoUrl", gankBean.getUrl());
                v.getContext().startActivity(intent);
            }
        });
        /*if (holder.itemView.getVisibility() == View.VISIBLE){
            runEnterAnimation(holder.itemView, position);
        }*/

    }

    @Override
    public int getItemCount() {
        return mGankBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.rv_tablayout_image);
            textView = (TextView)itemView.findViewById(R.id.rv_tablayout_text);
        }
    }

    private void runEnterAnimation(final View view, int position) {
        final Context context = view.getContext();
        if (position > mLastPosition){
            view.setAlpha(0);
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(context, R.anim.bottom_enter_anim);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            view.setAlpha(1);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                        }
                    });
                    view.startAnimation(animation);
                }
            }, 200 * position);
            //holder.itemView.setTranslationX(500);
            /*holder.itemView.animate()
                    .setDuration(500)
                    .translationX(0)
                    .alpha(1f)
                    .setStartDelay(200 * position)
                    .setInterpolator(new DecelerateInterpolator())
                    .start();*/
            mLastPosition = position;
        }
    }
}
