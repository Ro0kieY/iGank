package com.ro0kiey.igank.adapter;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.GankBean;
import com.ro0kiey.igank.ui.activity.WebActivity;
import com.ro0kiey.igank.ui.activity.ListActivity;
import com.ro0kiey.igank.utils.TextUtils;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {

    private List<GankBean> mGankBean;
    private int mLastPosition = -1;

    public GankAdapter(List<GankBean> mGankBean) {
        this.mGankBean = mGankBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_gank_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final GankBean gankBean = mGankBean.get(position);
        SpannableString spannableString = TextUtils.getGankStyleText(gankBean);
        holder.textView.setText(spannableString);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), WebActivity.class);
                intent.putExtra("title", gankBean.getDesc());
                intent.putExtra("Url", gankBean.getUrl());
                v.getContext().startActivity(intent);
            }
        });
        if (position == 0){
            showType(holder, true);
        } else if (mGankBean.get(position).getType().equals(mGankBean.get(position - 1).getType())){
            showType(holder, false);
        } else {
            showType(holder, true);
        }
        holder.typeView.setText(gankBean.getType());
        holder.typeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), ListActivity.class);
                intent.putExtra("type", gankBean.getType().toUpperCase());
                v.getContext().startActivity(intent);
            }
        });
        showItemAnimation(holder, position);
    }

    @Override
    public int getItemCount() {
        return mGankBean.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView typeView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView)itemView.findViewById(R.id.rv_gank_text);
            typeView = (TextView)itemView.findViewById(R.id.rv_gank_type);
        }
    }

    private void showType(ViewHolder holder, boolean show) {
        if (show){
            holder.typeView.setVisibility(View.VISIBLE);
        } else {
            holder.typeView.setVisibility(View.GONE);
        }
    }

    private void showItemAnimation(ViewHolder holder, int position) {

        if (position > mLastPosition){
            mLastPosition = position;
            ObjectAnimator.ofFloat(holder.itemView, "translationX", 500, 0f)
                    .setDuration(500)
                    .start();
        }
    }

}
