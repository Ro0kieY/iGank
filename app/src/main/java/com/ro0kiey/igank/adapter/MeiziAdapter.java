package com.ro0kiey.igank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.MeiziBean;
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
        holder.textView.setText(meizi.getCreatedAt());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String meiziUrl = meizi.getUrl();
                Intent intent = new Intent(mContext, MeiziActivity.class);
                intent.putExtra("Url", meiziUrl);
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, v, Config.ACTIVITY_IMAGE_TRANS);
                ActivityCompat.startActivity(mContext, intent, options.toBundle());
            }
        });
        Glide.with(mContext).load(meizi.getUrl()).centerCrop().into(holder.imageView);
        runEnterAnimation(holder.itemView, position);

        Log.d("on Debug", "position " + position + "run EnterAnimation");
    }

    @Override
    public int getItemCount() {
        return mMeiziList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rv_meizi_image);
            textView = (TextView)itemView.findViewById(R.id.rv_meizi_text);
        }
    }

    private void runEnterAnimation(View view, int position) {

        if (position % 2 == 0){
            view.setTranslationX(-500);//相对于原始位置下方500
            view.setAlpha(0.f);//完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原来的位置
            //并且根据item的位置设置延迟的时间，达到一个接着一个的效果
            view.animate()
                    .translationX(0).alpha(1.f)//设置最终效果为完全不透明，并且在原来的位置
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
            view.setAlpha(0.f);//完全透明
            //每个item项两个动画，从透明到不透明，从下方移动到原来的位置
            //并且根据item的位置设置延迟的时间，达到一个接着一个的效果
            view.animate()
                    .translationX(0).alpha(1.f)//设置最终效果为完全不透明，并且在原来的位置
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
