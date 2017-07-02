package com.ro0kiey.igank.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ro0kiey.igank.Config;
import com.ro0kiey.igank.R;
import com.ro0kiey.igank.SharedElement;
import com.ro0kiey.igank.model.MeiziBean;
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        MeiziBean meizi = mMeiziList.get(position);
        holder.textView.setText(meizi.getCreatedAt());
        Glide.with(mContext).load(meizi.getUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return mMeiziList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.rv_meizi_image);
            textView = (TextView)itemView.findViewById(R.id.rv_meizi_text);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedElement.shareDrawable = imageView.getDrawable();
                    Intent intent = new Intent(mContext, MeiziActivity.class);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)mContext, imageView, Config.ACTIVITY_IMAGE_TRANS);
                    ActivityCompat.startActivity(mContext, intent, options.toBundle());
                }
            });
        }
    }
}
