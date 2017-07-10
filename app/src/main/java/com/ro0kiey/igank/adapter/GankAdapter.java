package com.ro0kiey.igank.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.ro0kiey.igank.R;
import com.ro0kiey.igank.model.Bean.GankBean;

import java.util.List;

/**
 * Created by Ro0kieY on 2017/7/5.
 */

public class GankAdapter extends RecyclerView.Adapter<GankAdapter.ViewHolder> {

    private List<GankBean> mGankBean;

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
        GankBean gankBean = mGankBean.get(position);
        holder.textView.setText(gankBean.getDesc());
        if (position == 0){
            showType(holder, true);
        } else if (mGankBean.get(position).getType().equals(mGankBean.get(position - 1).getType())){
            showType(holder, false);
        } else {
            showType(holder, true);
        }
        holder.typeView.setText(gankBean.getType());

        runEnterAnimation(holder, position);
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

    private void runEnterAnimation(ViewHolder holder, int position) {
        holder.itemView.setTranslationX(500);
        holder.itemView.setAlpha(0f);
        holder.itemView.animate()
                .setDuration(500)
                .translationX(0)
                .alpha(1f)
                .setStartDelay(200 * position)
                .setInterpolator(new DecelerateInterpolator())
                .start();

    }

}
