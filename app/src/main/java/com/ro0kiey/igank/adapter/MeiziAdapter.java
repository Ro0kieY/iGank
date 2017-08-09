package com.ro0kiey.igank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.MeiziBean;
import com.ro0kiey.igank.ui.activity.GankActivity;
import com.ro0kiey.igank.ui.activity.MainActivity;
import com.ro0kiey.igank.ui.activity.MeiziActivity;

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
        holder.textViewWho.setText(meizi.getWho());
        holder.textViewDesc.setText(meizi.getDesc());

        holder.textLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, GankActivity.class);
                intent.putExtra("date", meizi.getPublishedAt());
                intent.putExtra("Url", meizi.getUrl());
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, holder.imageView, Config.ACTIVITY_IMAGE_TRANS);
                ActivityCompat.startActivity((Activity)mContext, intent, options.toBundle());
            }
        });
        holder.imageView.setBackgroundColor(Color.argb(200, red, green, blue));
        if (MainActivity.canGetBitmapFromNetwork){
            Glide.with(mContext).load(meizi.getUrl()).centerCrop().crossFade().into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MeiziActivity.class);
                    intent.putExtra("Url", meizi.getUrl());
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, v, Config.ACTIVITY_IMAGE_TRANS);
                    ActivityCompat.startActivity(mContext, intent, options.toBundle());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mMeiziList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout textLayout;
        ImageView imageView;
        TextView textViewDate;
        TextView textViewWho;
        TextView textViewDesc;

        public ViewHolder(final View itemView) {
            super(itemView);
            textLayout = (LinearLayout)itemView.findViewById(R.id.text_layout);
            imageView = (ImageView) itemView.findViewById(R.id.rv_meizi_image);
            textViewDate = (TextView)itemView.findViewById(R.id.rv_meizi_text_date);
            textViewWho = (TextView)itemView.findViewById(R.id.rv_meizi_text_who);
            textViewDesc = (TextView)itemView.findViewById(R.id.rv_meizi_text_desc);
        }
    }
}
