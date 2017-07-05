package com.ro0kiey.igank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.MeiziBean;
import com.ro0kiey.igank.ui.Activity.GankActivity;
import com.ro0kiey.igank.ui.Activity.MeiziActivity;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/1.
 */

public class MeiziAdapter extends RecyclerView.Adapter<MeiziAdapter.ViewHolder> {

    private List<MeiziBean> mMeiziList;
    private Context mContext;

    public MeiziAdapter(List<MeiziBean> mMeiziList) {
        this.mMeiziList = mMeiziList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_meizi_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final MeiziBean meizi = mMeiziList.get(position);
        int red = (int)(Math.random() * 255);
        int green = (int)(Math.random() * 255);
        int blue = (int)(Math.random() * 255);
        holder.textViewDate.setText(meizi.getCreatedAt().substring(0, 10));
        //holder.textViewWho.setText(meizi.getWho());
        holder.textViewDesc.setText(meizi.getDesc());
        holder.textLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GankActivity.class);
                intent.putExtra("Url", meizi.getUrl());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, holder.imageView, Config.ACTIVITY_IMAGE_TRANS);
                ActivityCompat.startActivity(mContext, intent, options.toBundle());
            }
        });
        holder.imageView.setBackgroundColor(Color.argb(200, red, green, blue));
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MeiziActivity.class);
                intent.putExtra("Url", meizi.getUrl());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, v, Config.ACTIVITY_IMAGE_TRANS);
                ActivityCompat.startActivity(mContext, intent, options.toBundle());
            }
        });
        Glide.with(mContext).load(meizi.getUrl()).centerCrop().crossFade().into(holder.imageView);
        runEnterAnimation(holder.itemView, position);

        Log.d("on Debug", "position " + position + "run EnterAnimation");
    }

    @Override
    public int getItemCount() {
        return mMeiziList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout textLayout;
        ImageView imageView;
        TextView textViewDate;
        TextView textViewWho;
        TextView textViewDesc;

        public ViewHolder(final View itemView) {
            super(itemView);
            textLayout = (RelativeLayout)itemView.findViewById(R.id.text_layout);
            imageView = (ImageView) itemView.findViewById(R.id.rv_meizi_image);
            textViewDate = (TextView)itemView.findViewById(R.id.rv_meizi_text_date);
            textViewWho = (TextView)itemView.findViewById(R.id.rv_meizi_text_who);
            textViewDesc = (TextView)itemView.findViewById(R.id.rv_meizi_text_desc);
        }
    }

    private void runEnterAnimation(View view, int position) {

        if (position % 2 == 0){
            view.setTranslationX(-500);//相对于原始位置左方500
            view.setAlpha(0f);//完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原来的位置
            //并且根据item的位置设置延迟的时间，达到一个接着一个的效果
            view.animate()
                    .translationX(0).alpha(1f)//设置最终效果为完全不透明，并且在原来的位置
                    //.setStartDelay(200 * (position))//根据item的位置设置延迟时间，达到依次动画一个接一个进行的效果
                    .setInterpolator(new DecelerateInterpolator(0.5f))//设置动画效果为在动画开始的地方快然后慢
                    .setDuration(500)
                    /*.setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //animationsLocked = true;
                            //firstTimeAnimate = false;//确保仅屏幕一开始能够显示的item项才开启动画，也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                        }
                    })*/
                    .start();
        } else {
            view.setTranslationX(500);//相对于原始位置下方500
            view.setAlpha(0f);//完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原来的位置
            //并且根据item的位置设置延迟的时间，达到一个接着一个的效果
            view.animate()
                    .translationX(0).alpha(1f)//设置最终效果为完全不透明，并且在原来的位置
                    //.setStartDelay(200 * (position))//根据item的位置设置延迟时间，达到依次动画一个接一个进行的效果
                    .setInterpolator(new DecelerateInterpolator(0.5f))//设置动画效果为在动画开始的地方快然后慢
                    .setDuration(500)
                    /*.setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            //animationsLocked = true;
                            //firstTimeAnimate = false;//确保仅屏幕一开始能够显示的item项才开启动画，也就是说屏幕下方还没有显示的item项滑动时是没有动画效果
                        }
                    })*/
                    .start();
        }
    }
}
